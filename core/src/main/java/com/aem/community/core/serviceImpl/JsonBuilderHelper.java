package com.aem.community.core.serviceImpl;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.jcr.JsonItemWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.community.core.service.JsonBuilder;
import com.day.cq.wcm.api.*;


//This is a component so it can provide or consume services
public class JsonBuilderHelper implements JsonBuilder {
	private final Logger logger = LoggerFactory.getLogger(JsonBuilderHelper.class);

	@Reference 
	private ResourceResolverFactory resolverFactory;
	private Session session;
	ResourceResolver resourceResolver;

	public JSONArray resourceToJSON()
	{
		JSONObject jsonObject = null;
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
		        String title = resProdProperties.get("productTitle").toString();
		    	String desc = resDetailProperties.get("tabOneDesc").toString();
		        String id = resProdProperties.get("styleNo").toString();
		        String price = resProdProperties.get("productPrice").toString();
		         ArrayList<String> images = new ArrayList<String>();
		        images.add(resProdProperties.get("image1").toString());
		        images.add(resProdProperties.get("image2").toString());
		        images.add(resProdProperties.get("image3").toString());
		        images.add(resProdProperties.get("image4").toString());
		        images.add(resProdProperties.get("image5").toString());
		        jo.put("id",id);
		        jo.put("product_title",title);
		        jo.put("product_description",desc);
		        jo.put("product_image",images);
		        jo.put("product_price",price);
		        ja.put(jo);
		     }
		 	return ja;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}
	
	}
