package com.aem.community.core.servlets;

import com.aem.community.core.serviceImpl.*;
import com.day.cq.replication.ReplicationException;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Iterator;

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
import javax.jcr.*;

@Component(service=Servlet.class,
property={
		Constants.SERVICE_DESCRIPTION + "=Content to JSON",
				"sling.servlet.methods=" + HttpConstants.METHOD_POST,
				"sling.servlet.resourceTypes="+ "miledge/components/structure/page",
				"sling.servlet.extensions=" + "txt",
				"sling.servlet.paths=" + "/bin/chatbotService"
})
public class storeChatbotData extends SlingAllMethodsServlet {

	private final Logger logger = LoggerFactory.getLogger(EndpointJSON.class);
	@Reference 
	private ResourceResolverFactory resolverFactory;
	private Session session;

	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException
	{
		ResourceResolver resourceResolver;
		try {
			resourceResolver = this.resolverFactory.getAdministrativeResourceResolver(null);
			this.session = ((Session)resourceResolver.adaptTo(Session.class));
			if(request.getParameter("data")!=null){
				String data=request.getParameter("data");
				Node node=session.getNode("/content/MilEdge/chatbotData");
				logger.info("Node Found " + node);
				node.setProperty("data", data);
				logger.info("Node Created at=" + node.getPath());
				response.getWriter().write("Node Created id=" + data);
				session.save();
			}
			else
				response.getWriter().write("No Data Found");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			response.getWriter().write(e.getMessage());
		}



	}

}

