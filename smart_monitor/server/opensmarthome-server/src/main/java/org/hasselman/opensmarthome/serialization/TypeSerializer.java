/**
 * Custom serializer for the contact sensor Type enumeration
 */

package org.hasselman.opensmarthome.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hasselman.dds.sensors.contact.Type;
import java.io.IOException;


public class TypeSerializer extends StdSerializer<Type> {
    public TypeSerializer() {
        this(null);
    }

    public TypeSerializer(Class<Type> type) {
        super(type);
    }

    @Override
    public void serialize(Type type, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeNumberField("type", type.value());
    }
}
