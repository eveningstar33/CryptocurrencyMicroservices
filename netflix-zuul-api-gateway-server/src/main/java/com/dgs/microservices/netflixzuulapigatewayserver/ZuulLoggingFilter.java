package com.dgs.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	// I need a log object because I want to log some of the information of this request
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = 
				RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri -> {}", request, request.getRequestURI());
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	// Should filter be happening before the request is executed or after the request is executed,
	// or you want to filter only error requests that have caused an exception to happen.
	// We want to intercept all the requests before execution
	
	@Override
	public String filterType() {
		return "pre";
	}

}
