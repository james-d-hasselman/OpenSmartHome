/** 
 * Custom deserializer for the contact sensor Type
 */

package org.hasselman.opensmarthome.serialization;

import org.hasselman.dds.sensors.contact.Type;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class TypeDeserializer extends StdDeserializer<Type> {
    public TypeDeserializer() {
        this(null);
    }

    public TypeDeserializer(Class<Type> type) {
        super(type);
    }

    @Override
    public Type deserialize(JsonParser parser, DeserializationContext context) throws IOException { 
        JsonNode node = parser.getCodec().readTree(parser);
        int value = (Integer) ((IntNode) node.get("type")).numberValue();

        return Type.from_int(value);
    }
}
