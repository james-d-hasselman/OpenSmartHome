/**
 * Listens for data from sensors and forwards it to the server socket
 */

package org.hasselman.opensmarthome;

import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.status.Status;
import org.omg.dds.core.status.DataAvailableStatus;
import org.omg.dds.core.status.LivelinessChangedStatus;
import org.omg.dds.core.event.DataAvailableEvent;
import org.omg.dds.core.event.LivelinessChangedEvent;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.sub.DataReader;
import org.omg.dds.sub.DataReaderAdapter;
import org.omg.dds.sub.Sample;
import org.omg.dds.topic.Topic;
import org.hasselman.dds.sensors.contact.ContactSensorType;
import org.hasselman.dds.sensors.contact.Type;
/*import org.hasselman.opensmarthome.serialization.TypeSerializer;
import org.hasselman.opensmarthome.serialization.StatusSerializer;*/
import org.hasselman.opensmarthome.serialization.ContactSensorTypeSerializer;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.net.URI;
import java.net.URISyntaxException;

public class SmartMonitorSensorListener implements Runnable {
    private ServiceEnvironment serviceEnvironment;
    private DomainParticipantFactory domainParticipantFactory;
    private DomainParticipant domainParticipant;
    private Subscriber subscriber;
    private DataReader dataReader;
    private Topic<ContactSensorType> topic;

    public void run() {
        System.out.println("Beginning topic creation.");
        try {
            serviceEnvironment = ServiceEnvironment.createInstance(SmartMonitorSensorListener.class.getClassLoader());
            domainParticipantFactory = DomainParticipantFactory.getInstance(serviceEnvironment);
            domainParticipant = domainParticipantFactory.createParticipant();
            subscriber = domainParticipant.createSubscriber();
            topic = domainParticipant.createTopic("ContactSensors", ContactSensorType.class);
        } catch(Exception e) {
            System.out.println("Error creating the topic!!!!!!!");
            System.out.println(e);
        }
        System.out.println("Successfully created topic!");

        SmartMonitorClient client = null;
        try {
            System.out.println("Initiating connection to the server...");
            client = new SmartMonitorClient(new URI("ws://localhost:8080/monitoring/socket/0"));
            System.out.println("Successfully connected to the server!");
        } catch (URISyntaxException e) {
            System.err.println("Error connecting to the server!!!");
            e.printStackTrace();
            return;
        }

        // create the listeners
        ContactSensorDataListener listener = new ContactSensorDataListener(client);
        Set<Class<? extends Status>> statuses = new HashSet<Class<? extends Status>>();
        statuses.add(DataAvailableStatus.class);
        statuses.add(LivelinessChangedStatus.class);
        dataReader = subscriber.createDataReader(topic, subscriber.getDefaultDataReaderQos(), listener, statuses);
        // enter the infinite keep alive loop
        while(!Thread.currentThread().isInterrupted()) {
            try {
                // keep alive
                Thread.sleep(5);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } 
        }
    }

    class ContactSensorDataListener extends DataReaderAdapter<ContactSensorType> {
        private SmartMonitorClient client;
        private ObjectMapper mapper;

        public ContactSensorDataListener(SmartMonitorClient client) {
            this.client = client;
            mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            /*module.addSerializer(Type.class, new TypeSerializer());
            module.addSerializer(org.hasselman.dds.sensors.contact.Status.class, new StatusSerializer());*/
            module.addSerializer(ContactSensorType.class, new ContactSensorTypeSerializer());
            mapper.registerModule(module);
        }

        public void onDataAvailable(DataAvailableEvent<ContactSensorType> status) {
            // send the data to the servlet
            Iterator<Sample<ContactSensorType>> contactSensorIterator = dataReader.read();
            while(contactSensorIterator.hasNext()) {
                ContactSensorType contactSensorData = contactSensorIterator.next().getData();
                if(contactSensorData != null) {
                    try {
                        System.out.println("SENDING: " + mapper.writeValueAsString(contactSensorData));
                        String contactSensorJSON = mapper.writeValueAsString(contactSensorData);
                        client.forwardMessage(contactSensorJSON);
                        System.out.println("Send successful!");
                    } catch(IOException e) {
                        System.out.println("An error has occurred forwarding the message!!!");
                        System.err.println(e);
                    } catch(Exception e) {
                        System.out.println("An error has occurred forwarding the message!!!");
                        System.err.println(e);
                    }
                }
            }
        }

        public void onLivelinessChanged(LivelinessChangedEvent<ContactSensorType> status) {
            System.out.println("SHIT!");
        }
    }
}

