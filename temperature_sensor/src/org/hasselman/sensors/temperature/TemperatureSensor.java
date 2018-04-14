package org.hasselman.sensors.temperature;

import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.topic.Topic;
import org.hasselman.dds.sensors.temperature.TemperatureSensorType;
import org.omg.dds.pub.Publisher;
import org.omg.dds.pub.DataWriter;
import org.hasselman.dds.sensors.temperature.TemperatureScale;
import java.util.concurrent.TimeoutException;

/**
 * Publishes temperature and humidity readings from a temperature sensor. 
 */
public class TemperatureSensor {
    public static void main(String[] args) {
        // create a domain participant
        ServiceEnvironment env = ServiceEnvironment.createInstance("org.opensplice.dds.core.OsplServiceEnvironment", null, TemperatureSensor.class.getClassLoader());
        DomainParticipantFactory factory = DomainParticipantFactory.getInstance(env);
        DomainParticipant domainParticipant = factory.createParticipant();

        // create the topic
        Topic<TemperatureSensorType> temperatureTopic;
        temperatureTopic = domainParticipant.createTopic("TTemperatureSensor", TemperatureSensorType.class);

        // create the publisher and data writer
        Publisher publisher = domainParticipant.createPublisher();
        DataWriter<TemperatureSensorType> dataWriter;
        dataWriter = publisher.createDataWriter(temperatureTopic);

        // write the data
        TemperatureSensorType sensorData;
        sensorData = new TemperatureSensorType((short)1, 26.0F, 70.0F, TemperatureScale.CELSIUS);
        try {
            dataWriter.write(sensorData);
        } catch (TimeoutException e) {
            System.err.println(e);
        }
    }
}
