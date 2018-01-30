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
		"sling.servlet.paths=" + "/bin/facbookName"
})
public class FetchFacebookName extends SlingAllMethodsServlet {

	private final Logger logger = LoggerFactory.getLogger(EndpointJSON.class);
	@Reference 
	private ResourceResolverFactory resolverFactory;
	private Session session;

	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
	{
		
		try {
			JSONObject jo=new JSONObject();
			jo=resourceToJSON();
			logger.info(jo.toString());
			response.setContentType("json");
			response.getWriter().write(null!=jo? jo.toString():"NULL");
		} catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	@SuppressWarnings("deprecation")
	public JSONObject resourceToJSON() 
	{
		        JSONObject jo = new JSONObject();
		        try {
					jo.put("facebook_userName","Patricia Jones");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	logger.info("JSON data is" + jo);
		 	return jo;
	}
}
