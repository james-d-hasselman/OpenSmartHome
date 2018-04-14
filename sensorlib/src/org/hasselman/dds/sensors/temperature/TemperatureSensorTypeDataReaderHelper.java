package org.hasselman.dds.sensors.temperature;

import org.opensplice.dds.dcps.Utilities;

public final class TemperatureSensorTypeDataReaderHelper
{

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReader) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReader) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
