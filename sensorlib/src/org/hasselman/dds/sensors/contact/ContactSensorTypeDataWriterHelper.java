package org.hasselman.dds.sensors.contact;

import org.opensplice.dds.dcps.Utilities;

public final class ContactSensorTypeDataWriterHelper
{

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeDataWriter narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeDataWriter) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeDataWriter unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeDataWriter) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
