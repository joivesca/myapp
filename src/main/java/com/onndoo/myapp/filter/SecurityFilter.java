package com.onndoo.myapp.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.onndoo.myapp.model.User;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
    	 HttpServletRequest req = (HttpServletRequest) request;
         HttpServletResponse res = (HttpServletResponse) response;

         HttpSession session = req.getSession(false);
         User user = (session != null) ? (User) session.getAttribute("userLogged") : null;

         if (user != null && user.isLoggedIn()) {
             chain.doFilter(request, response);
         } else {
             res.sendRedirect(req.getContextPath() + "/login");
         }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}