/**
 * Starts the background thread that monitors the sensors and publishes
 * updates to the clients.
 */

package org.hasselman.opensmarthome;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.omg.dds.core.ServiceEnvironment;

@WebListener
public class SmartMonitorStartup implements ServletContextListener {
    private ExecutorService executor;

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Stuff is happening.");
        System.setProperty(ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY, "org.opensplice.dds.core.OsplServiceEnvironment");
        SmartMonitorSensorListener sensorListener = new SmartMonitorSensorListener();
        executor = Executors.newSingleThreadExecutor();
        executor.submit(sensorListener);
    }

    public void contextDestroyed(ServletContextEvent event) {
        executor.shutdownNow();
    }
}
