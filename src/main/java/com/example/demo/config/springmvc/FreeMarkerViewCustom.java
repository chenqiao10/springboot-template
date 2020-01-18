package com.example.demo.config.springmvc;

import java.util.Enumeration;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.example.demo.config.Sercurty.CurrentUser;

import freemarker.template.SimpleHash;



public class FreeMarkerViewCustom extends FreeMarkerView
{
	private boolean exposeRequestParameters = true;

	// exposeHelpers 重写这个也可以
	@Override
	protected SimpleHash buildTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
	{
		UserDetails userDetails = CurrentUser.getUserDetails();
		if (userDetails != null)
		{
			model.put("userDetails", userDetails);
		}
		model.put("response", response);
		model.put("request", request);

		if (exposeRequestParameters)
		{
			for (Enumeration<String> en = request.getParameterNames(); en.hasMoreElements();)
			{
				String attribute = en.nextElement();
				if (model.containsKey(attribute))
				{
					continue;
				}
				Object attributeValue = request.getParameter(attribute);
				model.put(attribute, attributeValue);
			}
		}
		return super.buildTemplateModel(model, request, response);
	}
}
