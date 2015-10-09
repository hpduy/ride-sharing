package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.controller.AngelGroupController;
import com.bikiegang.ridesharing.controller.PopularLocationController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.utilities.FakeGroup.FakeUser;
import com.bikiegang.ridesharing.utilities.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class StartupListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    Logger logger = LogUtil.getLogger(this.getClass());

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
            Path.setServerAddress(InetAddress.getLocalHost().getHostAddress());
            Path.buildRoot();
            Database.getInstance().restore();
            if (Database.databaseStatus == Database.TESTING) {
                System.out.println("Group size before:" + Database.getInstance().getAngelGroupHashMap().size());
                FakeUser.createAngel();
                FakeUser.createTester();
                new AngelGroupController();
                System.out.println("Group size after:" + Database.getInstance().getAngelGroupHashMap().size());
            }
            Thread base_data_run = new Thread() {
                @Override
                public void run() {
                    new PopularLocationController();
                    try {
                        new AngelGroupController();
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }
                }
            };
            base_data_run.start();

            logger.info("CLOUD BIKE WAKED UP");
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
