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
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.resourceTypes="+ "miledge/components/structure/page",
		"sling.servlet.extensions=" + "txt",
		"sling.servlet.paths=" + "/bin/storeFacebookData"
})
public class StoreFacebookData extends SlingAllMethodsServlet {

	private final Logger logger = LoggerFactory.getLogger(EndpointJSON.class);
	@Reference 
	private ResourceResolverFactory resolverFactory;
	private Session session;

	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
	{
		ResourceResolver resourceResolver;
		try {
			resourceResolver = this.resolverFactory.getAdministrativeResourceResolver(null);
			this.session = ((Session)resourceResolver.adaptTo(Session.class));
			if(request.getParameter("data")!=null){
				String data=request.getParameter("data");
				JSONObject obj=new JSONObject(data);
				if(obj.has("id")){
					String id=obj.getString("id");
					String name=obj.getString("name");
					logger.info("ID is "+ id +"NAME IS" + name);
					Node node=session.getNode("/content/MilEdge/facebookData");
					logger.info("Node Found " + node);
					NodeIterator ndItr=node.getNodes("/content/MilEdge/facebookData");
					logger.info("Child Node Found " + ndItr);
					if(ndItr.hasNext()){
						while(ndItr.hasNext())
						{
							Node existingNode=ndItr.nextNode();
							String nodeName=existingNode.getName();
							if(nodeName.equals(id)){
								logger.info("EXISTING NODE FOUND");
								response.getWriter().write("EXISTING NODE FOUND");
								return;
							}
						}
					}
					Node fbData=node.addNode(id,"nt:unstructured");
					fbData.setProperty("id", id);
					fbData.setProperty("name", name);
					if(obj.has("birthday")){
						String bdate=obj.getString("birthday");
						fbData.setProperty("birthday", bdate);
					}
					if(obj.has("hometown")){
						String htown=obj.getString("hometown");
						fbData.setProperty("hometown", htown);
					}
					if(obj.has("email")){
						String email=obj.getString("email");
						fbData.setProperty("hometown", email);
					}
					if(obj.has("gender")){
						String gender=obj.getString("gender");
						fbData.setProperty("gender", gender);
					}
					logger.info("Node Created id=" + id);
					logger.info("Node Created at=" + fbData.getPath());
					response.getWriter().write("Node Created id=" + id);
					session.save();
				}
			}
			else
				response.getWriter().write("No Data Found");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			logger.info(e.getMessage());
		}
		


	}

}
