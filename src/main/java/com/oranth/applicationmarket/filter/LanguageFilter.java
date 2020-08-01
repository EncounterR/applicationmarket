package com.oranth.applicationmarket.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.oranth.applicationmarket.utils.DynamicDataSource;
import com.oranth.applicationmarket.utils.Util;

public class LanguageFilter extends AccessControlFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String language = request.getParameter("language");
		if(!Util.isEmpty(language) && language.split("_").length == 2){
			DynamicDataSource.setDataSource(language);
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return true;
	}

	

}
