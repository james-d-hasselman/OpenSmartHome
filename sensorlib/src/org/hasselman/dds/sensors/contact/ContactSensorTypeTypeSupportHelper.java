package org.hasselman.dds.sensors.contact;

import org.opensplice.dds.dcps.Utilities;

public final class ContactSensorTypeTypeSupportHelper
{

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeTypeSupport narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeTypeSupport) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeTypeSupport unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeTypeSupport) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
