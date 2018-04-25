/**
 * Custom deserializer for the contact sensor Status
 */

package org.hasselman.opensmarthome.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.hasselman.dds.sensors.contact.Status;
import java.io.IOException;

public class StatusDeserializer extends StdDeserializer<Status> {
    public StatusDeserializer() {
        this(null);
    }

    public StatusDeserializer(Class<Status> status) {
        super(status);
    }

    @Override
    public Status deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        int value = (Integer) ((IntNode) node.get("value")).numberValue();

        return Status.from_int(value);
    }
}
