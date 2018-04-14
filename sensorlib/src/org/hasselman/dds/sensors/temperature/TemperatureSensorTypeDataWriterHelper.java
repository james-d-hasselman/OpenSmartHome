package org.hasselman.dds.sensors.temperature;

import org.opensplice.dds.dcps.Utilities;

public final class TemperatureSensorTypeDataWriterHelper
{

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataWriter) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataWriter) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
