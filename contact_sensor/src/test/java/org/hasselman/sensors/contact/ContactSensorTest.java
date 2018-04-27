package org.hasselman.sensors.contact;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.hasselman.dds.sensors.contact.Type;
import org.junit.Test;
import org.omg.dds.core.ServiceEnvironment;

/**
 * Test methods for contact sensors.
 */
public class ContactSensorTest {

  /**
   * Creates a series of fake contacts sensors which will indefinitely publish status upates 
   * for testing purposes.
   */
  @Test
  public void testContactSensors() {
    System.out.println("Running test...");
    System.setProperty(ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY, 
      "org.opensplice.dds.core.OsplServiceEnvironment");
    ServiceEnvironment serviceEnvironment;
    serviceEnvironment = ServiceEnvironment
      .createInstance(ContactSensorTest.class.getClassLoader());
    // create a thread for each sensor in the system
    ExecutorService executor = Executors.newFixedThreadPool(17);
    ContactSensorMock kitchenWindowSensor = 
      new ContactSensorMock("kitchen-window", Type.WINDOW, serviceEnvironment);
    executor.submit(kitchenWindowSensor);
    ContactSensorMock masterBedroomWindow1 = 
      new ContactSensorMock("master-bedroom-window-1", Type.WINDOW, serviceEnvironment);
    executor.submit(masterBedroomWindow1);
    ContactSensorMock masterBedroomWindow2 = 
      new ContactSensorMock("master-bedroom-window-2", Type.WINDOW, serviceEnvironment);
    executor.submit(masterBedroomWindow2);
    ContactSensorMock secondBedroomWindow1 = 
      new ContactSensorMock("second-bedroom-window-1", Type.WINDOW, serviceEnvironment);
    executor.submit(secondBedroomWindow1);
    ContactSensorMock secondBedroomWindow2 = 
      new ContactSensorMock("second-bedroom-window-2", Type.WINDOW, serviceEnvironment);
    executor.submit(secondBedroomWindow2);
    ContactSensorMock livingRoomWindow = 
      new ContactSensorMock("living-room-window", Type.WINDOW, serviceEnvironment);
    executor.submit(livingRoomWindow);
    ContactSensorMock frontDoor = 
      new ContactSensorMock("front-door", Type.DOOR, serviceEnvironment);
    executor.submit(frontDoor);
    ContactSensorMock bathroomDoor = 
      new ContactSensorMock("bathroom-door", Type.DOOR, serviceEnvironment);
    executor.submit(bathroomDoor);
    ContactSensorMock utilityRoomDoor = 
      new ContactSensorMock("utility-room-door", Type.DOOR, serviceEnvironment);
    executor.submit(utilityRoomDoor);
    ContactSensorMock masterBedroomDoor = 
      new ContactSensorMock("master-bedroom-door", Type.DOOR, serviceEnvironment);
    executor.submit(masterBedroomDoor);
    ContactSensorMock masterBedroomClosetDoor1 = 
      new ContactSensorMock("master-bedroom-closet-door-1", Type.DOOR, serviceEnvironment);
    executor.submit(masterBedroomClosetDoor1);
    ContactSensorMock masterBedroomClosetDoor2 = 
      new ContactSensorMock("master-bedroom-closet-door-2", Type.DOOR, serviceEnvironment);
    executor.submit(masterBedroomClosetDoor2);
    ContactSensorMock coatClosetDoor = 
      new ContactSensorMock("coat-closet-door", Type.DOOR, serviceEnvironment);
    executor.submit(coatClosetDoor);
    ContactSensorMock supplyClosetDoor = 
      new ContactSensorMock("supply-closet-door", Type.DOOR, serviceEnvironment);
    executor.submit(supplyClosetDoor);
    ContactSensorMock linenClosetDoor = 
      new ContactSensorMock("linen-closet-door", Type.DOOR, serviceEnvironment);
    executor.submit(linenClosetDoor);
    ContactSensorMock secondBedroomDoor = 
      new ContactSensorMock("second-bedroom-door", Type.DOOR, serviceEnvironment);
    executor.submit(secondBedroomDoor);
    ContactSensorMock secondBedroomClosetDoor = 
      new ContactSensorMock("second-bedroom-closet-door", Type.DOOR, serviceEnvironment);
    executor.submit(secondBedroomClosetDoor);
    executor.shutdown();
    
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
      System.err.println(e);
    }
  }
}
