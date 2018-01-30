package com.aem.community.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.*;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service=Servlet.class,
property={
		Constants.SERVICE_DESCRIPTION + "=Content to JSON",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.resourceTypes="+ "miledge/components/structure/page",
		"sling.servlet.extensions=" + "txt",
		"sling.servlet.paths=" + "/bin/chatbotCommunication"
})
@SuppressWarnings("deprecation")
public class ChatbotConnect extends SlingAllMethodsServlet {

	private final Logger logger = LoggerFactory.getLogger(ChatbotConnect.class);
	@Reference 
	private ResourceResolverFactory resolverFactory;
	private Session session;
	@Reference
	private QueryBuilder builder;



	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response){
		try {
			Boolean isDefault=false;
			ArrayList<String> searchResult= new ArrayList<String>();
			ArrayList<String> searchPhrase= new ArrayList<String>();
			if(request.getParameter("data")!=null){
				String data=request.getParameter("data");
				JSONObject obj=new JSONObject(data);
				if(obj.has("userData")){
					JSONObject userData=obj.getJSONObject("userData");
					if(userData.has("dressProfile")){
						JSONObject dressProfile=userData.getJSONObject("dressProfile");
						//if(dressProfile.has("dress_preference")){
							//searchPhrase.add(dressProfile.getString("dress_preference"));
						//}
						if(dressProfile.has("dress_type")){
							searchPhrase.add(dressProfile.getString("dress_type"));
						}
					}
					if(userData.has("preferenceProfile")){
						JSONObject preferenceProfile=userData.getJSONObject("preferenceProfile");
						//if(preferenceProfile.has("shade")){
							//searchPhrase.add(preferenceProfile.getString("shade"));
						//}
						//if(preferenceProfile.has("neck_design")){
							//searchPhrase.add(preferenceProfile.getString("neck_design"));
						//}
						if(preferenceProfile.has("color")){
							searchPhrase.add(preferenceProfile.getString("color"));
						}
					}
					logger.info("SearchPhrase ArrayLisy:" +searchPhrase.toString());
					searchResult=process(searchPhrase);
					if(searchResult.isEmpty())
					{
						isDefault=true;
						searchResult.add("/content/MilEdge/miledge/en/product/dark-knight-");
						searchResult.add("/content/MilEdge/miledge/en/product/PrintedFitandFlareDress");
						searchResult.add("/content/MilEdge/miledge/en/product/Lace_maxi_dress");		
					}
					JSONArray resultJSON=resourceToJSON(searchResult,isDefault);
					response.getWriter().write(resultJSON.toString());
				}
			}
			else
				response.getWriter().write("No JSON Data recieved");
		} catch (IOException | JSONException |RepositoryException e) {
			logger.info("ERROR IS" + e);
		}





	}
	private ArrayList<String> process(ArrayList<String> searchPhrase) throws IOException, RepositoryException  {
		ResourceResolver resourceResolver;
		try {
			resourceResolver = this.resolverFactory.getAdministrativeResourceResolver(null);
			this.session = (Session)resourceResolver.adaptTo(Session.class);
			ArrayList<String> path=new ArrayList<String>();
			Map<String, String> map = getQueryBuilderPredicates(searchPhrase);
			Query query = builder.createQuery(PredicateGroup.create(map), session);                  
			SearchResult result = query.getResult();		
			for (Hit hit : result.getHits()) {
				path.add(hit.getPath().toString());
			}
			return path;

		} catch (org.apache.sling.api.resource.LoginException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, String> getQueryBuilderPredicates(ArrayList<String> textSearched) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("path", "/content/MilEdge/miledge/en/product");
		map.put("type", "cq:Page");
		map.put("group.p.add","true");
		int i=0;
		for(String searchTxt:textSearched){
			map.put("group."+i+"_fulltext", searchTxt);
			map.put("group."+i+"_fulltext.relPath", "jcr:content");
			i++;
		}
		map.put("p.limit", "5");
		logger.debug("MAP generated is "+map.toString());
		return map;
	}

	public JSONArray resourceToJSON(ArrayList<String> allPath,Boolean isDefault) 
	{
		JSONArray finalObject = new JSONArray();
		try {
			ResourceResolver resourceResolver = this.resolverFactory.getAdministrativeResourceResolver(null);
			this.session = ((Session)resourceResolver.adaptTo(Session.class));
			Iterator<String> paths = allPath.iterator();
			JSONObject defaultFlag = new JSONObject();
			finalObject.put(defaultFlag.put("isDefault", isDefault));
			while (paths.hasNext()) 
			{ 
				JSONObject jo = new JSONObject();
				String resourcePath = paths.next(); 
				Resource resProd = resourceResolver.getResource(resourcePath + "/jcr:content/Parsys1/productdetails");
				Resource resDetail = resourceResolver.getResource(resourcePath + "/jcr:content/Parsys1/accordion");
				ValueMap resProdProperties = resProd.adaptTo(ValueMap.class);
				ValueMap resDetailProperties = resDetail.adaptTo(ValueMap.class);
				String ID = (String) resProdProperties.getOrDefault("id", "NA");
				String title = (String) resProdProperties.getOrDefault("productTitle", "NA");
				String desc = (String) resDetailProperties.getOrDefault("tabOneDesc", "NA");
				String styleCode = (String) resProdProperties.getOrDefault("stylecode", "NA");
				String price = (String) resProdProperties.getOrDefault("productPrice", "NA");
				String gender = (String) resProdProperties.getOrDefault("Gender1", "NA");
				String ARCheck = (String) resProdProperties.getOrDefault("ARCheck", "NA");
				ArrayList<String> images = new ArrayList<String>();
				if(ID.equals("0")||ID.equals("1")||ID.equals("4")){
					images.add("http://34.207.125.192:4510"+resProdProperties.getOrDefault("cbImage","NA").toString());
					images.add("http://34.207.125.192:4510"+resProdProperties.getOrDefault("image1", "NA").toString());}
				else{
					images.add("http://34.207.125.192:4510"+resProdProperties.getOrDefault("image1", "NA").toString());
					images.add("http://34.207.125.192:4510"+resProdProperties.getOrDefault("cbImage","NA").toString());	}
				jo.put("id",ID);
				jo.put("product_title",title);
				jo.put("product_description",desc);
				jo.put("product_image",images);
				jo.put("product_price",price);
				jo.put("style_code",styleCode);
				jo.put("gender",gender);
				jo.put("ar_compatible",ARCheck);
				jo.put("page_path", "http://34.207.125.192:4510"+ resourcePath +".html");
				if(ID.equals("0")){
		        	finalObject.put(1,jo);}
		        else{
		        	finalObject.put(jo);}
				logger.info("JSON data is" + jo);
			}
			return finalObject;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("try block failed" + e);
		}


		return null;
	}

}
