package com.onndoo.myapp.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import com.onndoo.myapp.model.Customer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@RequestScoped
@Named(value= "indexController")
public class IndexController {

	private Customer current;
	
	public Customer getCurrent() {
		if (current == null) {
			current = new Customer();
		}
		return current;
	}
	
	public String submit() {
		
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//        Locale locale = facesContext.getViewRoot().getLocale(); // Obtiene el idioma actual
//        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
//
//        String message = bundle.getString("subscription.success").replace("{0}", getCurrent()));
//        
//		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
//        
//		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		
//		facesContext.getExternalContext().getFlash().setKeepMessages(true);
//	    facesContext.addMessage(null, facesMsg);
//	    
//		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	    
		return "null";
	}
	
	public String page1() {
		return "page1?faces-redirect=true";
	}
	
	
	public String goIndex() {
		return "index.xhtml?faces-redirect=true";
	}
	
	public String goLogin() {
		return "login.xhtml?faces-redirect=true";
	}
	
	public String goRegister() {
		return "register.xhtml?faces-redirect=true";
	}
	
}
