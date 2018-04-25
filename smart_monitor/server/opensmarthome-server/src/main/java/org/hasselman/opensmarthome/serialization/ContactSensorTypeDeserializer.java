/** 
 * Custom deserializer for the contact sensor Type
 */

package org.hasselman.opensmarthome.serialization;

import org.hasselman.dds.sensors.contact.ContactSensorType;
import org.hasselman.dds.sensors.contact.Status;
import org.hasselman.dds.sensors.contact.Type;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public final class ContactSensorTypeDeserializer extends StdDeserializer<ContactSensorType> {
    public ContactSensorTypeDeserializer() {
        this(null);
    }

    public ContactSensorTypeDeserializer(final Class<ContactSensorType> contactSensorType) {
        super(contactSensorType);
    }

    @Override
    public ContactSensorType deserialize(final JsonParser parser, final DeserializationContext context) throws IOException { 
        final JsonNode node = parser.getCodec().readTree(parser);
        final String id = (String) ((TextNode) node.get("id")).textValue();
        final int typeValue = (Integer) ((IntNode) node.get("type")).numberValue();
        final boolean isOpen = (Boolean) ((BooleanNode) node.get("isOpen")).booleanValue();
        final int statusValue = (Integer) ((IntNode) node.get("status")).numberValue();
        final short batteryPercentage = (Short) ((IntNode) node.get("batteryPercentage")).numberValue();
        final Type type = Type.from_int(typeValue);
        final Status status = Status.from_int(statusValue);

        final ContactSensorType contactSensorData = new ContactSensorType(id, type, isOpen, status, batteryPercentage);


        return contactSensorData;
    }
}
