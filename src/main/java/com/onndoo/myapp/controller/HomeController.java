package com.onndoo.myapp.controller;

import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named(value= "homeController")
@RequestScoped
public class HomeController implements Serializable{

	private static final long serialVersionUID = 1L;

	public String goProfile() {
        return "home?faces-redirect=true";
    }
	
	public String goSettings() {
        return "home?faces-redirect=true";
    }
}
