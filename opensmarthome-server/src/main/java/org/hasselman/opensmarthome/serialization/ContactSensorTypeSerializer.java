package org.hasselman.opensmarthome.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hasselman.dds.sensors.contact.ContactSensorType;
import org.hasselman.dds.sensors.contact.Status;
import org.hasselman.dds.sensors.contact.Type;
import java.io.IOException;

/**
 * Custom serializer for the contact sensor Type enumeration
 */
public final class ContactSensorTypeSerializer extends StdSerializer<ContactSensorType> {
  
  public ContactSensorTypeSerializer() {
    this(null);
  }

  public ContactSensorTypeSerializer(final Class<ContactSensorType> contactSensorData) {
    super(contactSensorData);
  }

  /**
   * Converts the sensor data to JSON for transmission to the client application.
   */
  @Override
  public void serialize(final ContactSensorType contactSensorData, final JsonGenerator generator, 
    final SerializerProvider provider) throws IOException {
    generator.writeStartObject();
    generator.writeStringField("id", contactSensorData.id);
    generator.writeNumberField("type", contactSensorData.type.value());
    generator.writeBooleanField("isOpen", contactSensorData.isOpen);
    generator.writeNumberField("status", contactSensorData.status.value());
    generator.writeNumberField("batteryPercentage", contactSensorData.batteryPercentage);
    generator.writeEndObject();
  }
}
