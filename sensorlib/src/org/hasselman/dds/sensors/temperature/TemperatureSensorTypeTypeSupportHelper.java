package org.hasselman.dds.sensors.temperature;

import org.opensplice.dds.dcps.Utilities;

public final class TemperatureSensorTypeTypeSupportHelper
{

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeTypeSupport narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeTypeSupport) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeTypeSupport unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeTypeSupport) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
