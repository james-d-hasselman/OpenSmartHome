/**
 * Simulates a contact sensor that is used to check whether a door or window 
 * is open or closed
 */

package org.hasselman.sensors.contact;

import org.hasselman.dds.sensors.contact.ContactSensorType;
import org.hasselman.dds.sensors.contact.Status;
import org.hasselman.dds.sensors.contact.Type;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.InstanceHandle;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.Publisher;
import org.omg.dds.topic.Topic;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

public class ContactSensorMock implements Runnable {
    private ServiceEnvironment serviceEnvironment;
    private DomainParticipantFactory domainParticipantFactory;
    private DomainParticipant domainParticipant;
    private Publisher publisher;
    private Topic<ContactSensorType> contactSensorTopic;
    private DataWriter<ContactSensorType> contactDataWriter;
    private ContactSensorType sensorData;
    private int secondsElapsed;
    private boolean offline;
    private Random coinFlipper;
    private InstanceHandle handle;

    public  ContactSensorMock(String id, Type sensorType, ServiceEnvironment serviceEnvironment){
        this.serviceEnvironment = serviceEnvironment;
        domainParticipantFactory = DomainParticipantFactory.getInstance(serviceEnvironment);
        domainParticipant = domainParticipantFactory.createParticipant();
        publisher = domainParticipant.createPublisher();
        contactSensorTopic = domainParticipant.createTopic("ContactSensors", ContactSensorType.class);
        contactDataWriter = publisher.createDataWriter(contactSensorTopic);
        sensorData = new ContactSensorType(id, sensorType, false, Status.OK, (short)100);
        try {
            handle = contactDataWriter.registerInstance(sensorData);
        } catch(Exception e) {
            e.printStackTrace();
        }

        secondsElapsed = 0;
        offline = false;
        coinFlipper = new Random();
    } 

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            if(!offline) {
                // status OK, five seconds elapsed
                if(sensorData.status == Status.OK && secondsElapsed >= 5) {
                    // flip a coin to determine if the contact sensor is open/closed
                    int result = coinFlipper.nextInt(2);
                    // record the new status
                    if(result == 1) {
                        sensorData.isOpen = true;
                    } else {
                        sensorData.isOpen = false;
                    }
                    // if the battery is at 20% or less
                    if(sensorData.batteryPercentage <= 20) {
                        // set the maintenance flag
                        sensorData.status = Status.MAINTENANCE_REQUIRED;
                    }
                    secondsElapsed = 0;
                }
                // maintenance maintenance needed, five seconds elapsed
                else if(sensorData.status == Status.MAINTENANCE_REQUIRED && secondsElapsed >= 5) {
                    // unpublish the topic (make is look offline)
                    try {
                        contactDataWriter.unregisterInstance(handle);
                        //contactDataWriter.dispose(handle);
                        //contactDataWriter = null;
                    } catch (TimeoutException ex) {
                        System.err.println(ex);
                    }
                    secondsElapsed = 0;
                    offline = true;
                } else {
                    // publish the message
                    try {
                        contactDataWriter.write(sensorData);
                    } catch(TimeoutException e) {
                        System.err.println("Error writing sensor data!!!!");
                        e.printStackTrace();
                    }
                }

                // decrease the battery percentage by 1%
                sensorData.batteryPercentage--;
            } else { 
                // offline, five seconds elapsed
                if(secondsElapsed >= 5) {
                    try {
                        Thread.sleep(10000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    contactDataWriter = publisher.createDataWriter(contactSensorTopic);
                    // set battery to 100%
                    sensorData.batteryPercentage = 100;
                    // set status to OK
                    sensorData.status = Status.OK;
                    try {
                        handle = contactDataWriter.registerInstance(sensorData);
                    } catch(TimeoutException e) {
                        e.printStackTrace();
                    }
                    offline = false;
                    secondsElapsed = 0;
                }
            }

            // sleep for one second
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        
            secondsElapsed++;
        }
    }
}
