package com.aem.community.core.service;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

public interface JsonBuilder {
    
	public JSONArray resourceToJSON();

}
