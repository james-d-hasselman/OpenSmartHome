package org.hasselman.opensmarthome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;
import javax.websocket.OnOpen;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.Session;
import org.hasselman.dds.sensors.contact.ContactSensorType;
import org.hasselman.opensmarthome.serialization.ContactSensorTypeSerializer;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.event.DataAvailableEvent;
import org.omg.dds.core.status.DataAvailableStatus;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.sub.DataReader;
import org.omg.dds.sub.DataReaderAdapter;
import org.omg.dds.sub.InstanceState;
import org.omg.dds.sub.Sample;
import org.omg.dds.sub.Subscriber;
import org.omg.dds.topic.Topic;

/**
 * Listens for client connections and forwards sensor data to connected clients.
 */
@ServerEndpoint(value = "/socket/")
public final class SmartMonitor extends DataReaderAdapter<ContactSensorType> {
  private Session session = null;

  private DataReader<ContactSensorType> dataReader;

  private ObjectMapper mapper;

  /**
   * Subscribes to the sensors and sets up a listener to wait for new sensor data.
   */
  @OnOpen
  public void onOpen(final Session session) {
    this.session = session;
    final ServiceEnvironment environment = ServiceEnvironment
        .createInstance(SmartMonitor.class.getClassLoader());
    final DomainParticipantFactory domainParticipantFactory = 
        DomainParticipantFactory.getInstance(environment);
    final DomainParticipant domainParticipant = domainParticipantFactory.createParticipant();
    final Subscriber subscriber = domainParticipant.createSubscriber();
    final Topic<ContactSensorType> topic = domainParticipant
        .createTopic("ContactSensors", ContactSensorType.class);
    final Set<Class<? extends Status>> statuses = new HashSet<Class<? extends Status>>();
    statuses.add(DataAvailableStatus.class);
    dataReader = subscriber
        .createDataReader(topic, subscriber.getDefaultDataReaderQos(), this, statuses);
    final SimpleModule module = new SimpleModule();
    module.addSerializer(ContactSensorType.class, new ContactSensorTypeSerializer());
    mapper = new ObjectMapper();
    mapper.registerModule(module);
  }

  /**
   * Logs any errors that occur and cleans up the connection.
   */
  @OnError
  public void onError(final Session session, final Throwable t) {
    this.session = null;
    dataReader = null;
    System.err.println("Some horrible error has occurred!!!");
    t.printStackTrace();
  }

  /**
   * Cleans up the connection when either the client or server closes it.
   */
  @OnClose
  public void onClose(final Session session) {
    this.session = null;
    dataReader = null;
  }

  /**
   * Listens for new sensor data and forwards it to the client.
   */
  public void onDataAvailable(final DataAvailableEvent<ContactSensorType> event) {
    final Iterator<Sample<ContactSensorType>> contactSensorIterator = dataReader.read();
    while(contactSensorIterator.hasNext()) {
      final Sample<ContactSensorType> contactSensorSample = contactSensorIterator.next();
      final ContactSensorType contactSensorData = contactSensorSample.getData();
      if(contactSensorData != null) {
        try {
          if(contactSensorSample.getInstanceState() != InstanceState.ALIVE) {
            contactSensorData.batteryPercentage = -1;
          }
          System.out.println("SENDING: " + mapper.writeValueAsString(contactSensorData));
          final String contactSensorJSON = mapper.writeValueAsString(contactSensorData);
          session.getBasicRemote().sendText(contactSensorJSON);
          System.out.println("Send successful!");
        } catch(final IOException e) {
          System.out.println("An error has occurred sending the message!!!");
          e.printStackTrace();
        } catch(final Exception e) {
          System.out.println("An error has occurred sending the message!!!");
          e.printStackTrace();
        }
      }
    }
  }
}

