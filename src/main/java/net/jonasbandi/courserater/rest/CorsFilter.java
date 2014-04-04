package net.jonasbandi.courserater.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "CorsFilter", urlPatterns = {"/*"})
public class CorsFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
    	final HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;

    	// For most requests the headers have to be added before further processing, afterwards they just don't get added
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.addHeader("Access-Control-Allow-Headers", "x-requested-with, accept, origin, authorization");
        httpResponse.addHeader("Access-Control-Max-Age", "10");
        
        filterChain.doFilter(servletRequest,servletResponse);
        
        // For OPTIONS requests the headers have to be added after the other processing
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.addHeader("Access-Control-Allow-Headers", "x-requested-with, accept, origin, authorization");
        httpResponse.addHeader("Access-Control-Max-Age", "10");
        
    }

    @Override
    public void destroy() {
    }

}
