package org.hasselman.opensmarthome;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.omg.dds.core.ServiceEnvironment;

/**
 * Starts the background thread that monitors the sensors and publishes
 * updates to the clients.
 */
@WebListener
public final class SmartMonitorStartup implements ServletContextListener {

  /**
   * Set the environment variable needed by DDS.
   */
  public void contextInitialized(final ServletContextEvent event) {
    System.setProperty(ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY, 
      "org.opensplice.dds.core.OsplServiceEnvironment");
  }

  public void contextDestroyed(final ServletContextEvent event) {}
}
