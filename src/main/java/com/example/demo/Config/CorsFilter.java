package com.example.demo.Config;

import org.springframework.core.annotation.Order;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CORSFilter
 */
@Order(1)
@WebFilter(filterName = "MSecurity",urlPatterns = {"/*"})
public class CorsFilter implements Filter
{

    /**
     * Default constructor.
     */
    public void CorsFilterr() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here

        // pass the request along the filter chain
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
        httpResponse.addHeader("Access-Control-Max-Age", "1800");//30 min
        System.out.println("444444444444444");
        chain.doFilter(request, response);

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}