package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.utilities.ApiDocumentGenerator;
import com.bikiegang.ridesharing.utilities.Path;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.nio.charset.Charset;

public class StartupListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public StartupListener() {
        Charset charset = Charset.defaultCharset();
        if (!charset.equals(Charset.forName("UTF-8"))) {
//            org.apache.commons.logging.LogFactory.getLog(getClass()).error("You should use UTF-8 but are using: " + charset);
        }
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
         */
        try {
            Path.buildRoot();
            ApiDocumentGenerator.generate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
         */

    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute 
         is added to a session.
         */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute
         is removed from a session.
         */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is invoked when an attibute
         is replaced in a session.
         */
    }
}
