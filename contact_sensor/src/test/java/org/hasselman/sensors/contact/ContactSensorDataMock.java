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

public class ContactSensorDataMock implements Runnable {
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

    public  ContactSensorDataMock(String id, Type sensorType, ServiceEnvironment serviceEnvironment){
        this.serviceEnvironment = serviceEnvironment;
        domainParticipantFactory = DomainParticipantFactory.getInstance(serviceEnvironment);
        domainParticipant = domainParticipantFactory.createParticipant();
        publisher = domainParticipant.createPublisher();
        contactSensorTopic = domainParticipant.createTopic("ContactSensors", ContactSensorType.class);
        contactDataWriter = publisher.createDataWriter(contactSensorTopic);

        sensorData = new ContactSensorType(id, sensorType, false, Status.OK, (short)100);
        secondsElapsed = 0;
        offline = false;
        coinFlipper = new Random();
    } 

    @Override
    public void run(){
        try {
            contactDataWriter.write(sensorData);
        } catch(TimeoutException e) {
            e.printStackTrace();
        }

        while(!Thread.currentThread().isInterrupted()) {
            // sleep for one second
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
