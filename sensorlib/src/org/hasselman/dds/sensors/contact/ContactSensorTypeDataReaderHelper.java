package org.hasselman.dds.sensors.contact;

import org.opensplice.dds.dcps.Utilities;

public final class ContactSensorTypeDataReaderHelper
{

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeDataReader narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeDataReader) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeDataReader unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeDataReader) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
