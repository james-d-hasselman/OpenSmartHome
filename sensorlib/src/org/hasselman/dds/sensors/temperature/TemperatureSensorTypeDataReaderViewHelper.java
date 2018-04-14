package org.hasselman.dds.sensors.temperature;

import org.opensplice.dds.dcps.Utilities;

public final class TemperatureSensorTypeDataReaderViewHelper
{

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReaderView narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReaderView) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReaderView unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReaderView) {
            return (org.hasselman.dds.sensors.temperature.TemperatureSensorTypeDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
