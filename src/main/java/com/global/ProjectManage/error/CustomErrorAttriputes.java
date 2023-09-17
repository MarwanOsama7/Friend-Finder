package com.global.ProjectManage.error;

import java.util.Arrays;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CustomErrorAttriputes extends DefaultErrorAttributes{

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest , ErrorAttributeOptions options){
		Map<String, Object> error =  super.getErrorAttributes(webRequest, options);
		
		error.put("locale", webRequest.getLocale().toString());
		error.put("success", Boolean.FALSE);
		error.put("status", error.get("error"));
		error.put("exception", error.get("message"));
		error.put("details", Arrays.asList(error.get("message")));
		error.remove("error");
		error.remove("path");
		return error;
	}
}
