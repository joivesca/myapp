package com.onndoo.myapp.config;

import java.io.IOException;
import java.util.Iterator;

import com.onndoo.myapp.controller.UserController;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;
import jakarta.faces.event.ExceptionQueuedEventContext;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    //private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private final ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
    	super(wrapped);
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws RuntimeException {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEventContext context = events.next().getContext();
            Throwable exception = context.getException();

            try {
            	System.err.println("Se ha producido un error inesperado: " + exception.getMessage());
                FacesContext facesContext = FacesContext.getCurrentInstance();

                if (facesContext != null && facesContext.getExternalContext() != null) {
                    facesContext.getExternalContext().responseReset();
                    
                    String errorPage = facesContext.getExternalContext().getRequestServletPath();
                    
                    Object userBean = facesContext.getExternalContext().getSessionMap().get("userController");
                    boolean isLoggedIn = userBean != null && ((UserController) userBean).getUser().isLoggedIn();
                    
                    //String redirectPage = isLoggedIn ? "/secured/home" : "/index";
                    
                    System.out.println("Se redirige a la página de error: " + errorPage);
                    System.out.println("El usuario está logueado: " + isLoggedIn);
                    
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/error/500");                	
                    facesContext.responseComplete();
                }
            } catch (IOException e) {
				e.printStackTrace();
			} finally {
                events.remove();
            }
        }

        getWrapped().handle();
    }
}