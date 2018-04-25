/**
 * Custom serializer for the contact sensor Status field
 */

package org.hasselman.opensmarthome.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hasselman.dds.sensors.contact.Status;
import java.io.IOException;

public class StatusSerializer extends StdSerializer<Status> {
    public StatusSerializer() {
        this(null);
    }

    public StatusSerializer(Class<Status> status) {
        super(status);
    }

    @Override
    public void serialize(Status status, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("value", status.value());
        generator.writeEndObject();
    }
}
