package com.onndoo.myapp.controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.Application;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Named("localeBean")
@SessionScoped
public class LocaleBean implements Serializable {
    
	private static final long serialVersionUID = 1L;       
	private Locale locale;
	private List<Locale> available;
	
	@Inject
	private FacesContext context;
	
	@PostConstruct
    public void init() {
		Application app = context.getApplication();
		this.locale = app.getViewHandler().calculateLocale(context);
		available = new ArrayList<>();
		available.add(app.getDefaultLocale());
		app.getSupportedLocales().forEachRemaining(available::add);
    }

	public void reload() {
		context.getPartialViewContext().getEvalScripts().add("location.replace(location)");
	}
	
	public Locale getLocale() {
        return locale;
    }
	
    public String getLanguageTag() {
        return locale.toLanguageTag();
    }

    public void setLanguageTag(String languageTag) {  
        System.out.println("Cambiando idioma a: " + languageTag); // Depuración    	
		this.locale = Locale.forLanguageTag(languageTag);		
    }
    
	public List<Locale> getAvailable() {
		return available;
	}
    
}