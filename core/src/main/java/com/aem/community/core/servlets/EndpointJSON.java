package com.aem.community.core.servlets;

import com.aem.community.core.serviceImpl.*;
import com.day.cq.replication.ReplicationException;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Servlet.class,
property={
		Constants.SERVICE_DESCRIPTION + "=Content to JSON",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.resourceTypes="+ "miledge/components/structure/page",
		"sling.servlet.extensions=" + "txt",
		"sling.servlet.paths=" + "/bin/productList"
})
public class EndpointJSON extends SlingAllMethodsServlet {

	private final Logger logger = LoggerFactory.getLogger(EndpointJSON.class);
	@Reference 
	private ResourceResolverFactory resolverFactory;
	private Session session;

	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
	{
		
		try {
			JSONArray ja=new JSONArray();
			ja=resourceToJSON();
			logger.info(ja.toString());
			response.setContentType("json");
			response.getWriter().write(null!=ja? ja.toString():"NULL");
		} catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	@SuppressWarnings("deprecation")
	public JSONArray resourceToJSON() 
	{
		try {
			ResourceResolver resourceResolver = this.resolverFactory.getAdministrativeResourceResolver(null);
			this.session = ((Session)resourceResolver.adaptTo(Session.class));
			PageManager pageManager=((PageManager)resourceResolver.adaptTo(PageManager.class));
			Page rootPage = pageManager.getPage("/content/MilEdge/miledge/en/product");
			Iterator<Page> children = rootPage.listChildren(); 
			JSONArray ja =new JSONArray();
		 	while (children.hasNext()) 
			{ 
		        JSONObject jo = new JSONObject();

		    	Page child = children.next().adaptTo(Page.class); 
		   		String pagePath=child.getPath();
		        Resource resProd = resourceResolver.getResource(pagePath + "/jcr:content/Parsys1/productdetails");
		        Resource resDetail = resourceResolver.getResource(pagePath + "/jcr:content/Parsys1/accordion");
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
		        images.add("http://34.207.125.192:4510"+resProdProperties.get("image1").toString());
		        images.add("http://34.207.125.192:4510"+resProdProperties.get("image2").toString());
		        images.add("http://34.207.125.192:4510"+resProdProperties.get("image3").toString());
		        images.add("http://34.207.125.192:4510"+resProdProperties.get("image4").toString());
		        images.add("http://34.207.125.192:4510"+resProdProperties.get("image5").toString());
		        jo.put("id",ID);
		        jo.put("product_title",title);
		        jo.put("product_description",desc);
		        jo.put("product_image",images);
		        jo.put("product_price",price);
		        jo.put("style_code",styleCode);
		        jo.put("gender",gender);
		        jo.put("ar_compatible",ARCheck);
		        if(ID.equals("0")){
		        	jo.put("discount_price", "693");
		        	ja.put(0,jo);}
		        else{
		        	jo.put("discount_price", "null");
		        	ja.put(jo);}
		     }
		 	logger.info("JSON data is" + ja);
		 	return ja;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("try block failed" + e);
		}


		return null;
	}
}
