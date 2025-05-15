package com.onndoo.myapp.controller;

import java.io.IOException;
import java.io.Serializable;

import com.onndoo.myapp.model.User;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("userController")
@SessionScoped
public class UserController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user = new User();
	
	public String login() {
		if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {            // Simulate a successful login			
			user.setLoggedIn(true);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userLogged", user);
			return "secured/home?faces-redirect=true";
		}
		return "login?faces-redirect=true";
	}
	
	public String logout() {
		user.setLoggedIn(false);
		user.setUsername(null);
		user.setPassword(null);
		return "pretty:index";
	}
	
	public String register() {
		 System.out.println("Usuario registrado: " + user.getUsername() + ", Email: " + user.getEmail());
		 return "login?faces-redirect=true";
	}

	public String redirectIfLoggedIn() {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    String currentPage = facesContext.getViewRoot().getViewId();
	    
	    if (user.isLoggedIn() && !isErrorPage(currentPage) && !currentPage.equals("/views/secured/home.xhtml")) {
	    	System.out.println("ENTRAMOS");
	        try {
	            String contextPath = facesContext.getExternalContext().getRequestContextPath();
	            facesContext.getExternalContext().redirect(contextPath + "/secured/home");
	            
	            //return "pretty:securedHome";
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    return null;
	}
	
	private boolean isErrorPage(String viewId) {
	    return viewId.contains("404.xhtml") || viewId.contains("500.xhtml");
	}
	
	public User getUser() { 
		return user; 
	}
}
