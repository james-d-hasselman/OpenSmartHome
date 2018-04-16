package org.hasselman.dds.sensors.contact;

import org.opensplice.dds.dcps.Utilities;

public final class ContactSensorTypeDataReaderViewHelper
{

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeDataReaderView narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeDataReaderView) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static org.hasselman.dds.sensors.contact.ContactSensorTypeDataReaderView unchecked_narrow(java.lang.Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof org.hasselman.dds.sensors.contact.ContactSensorTypeDataReaderView) {
            return (org.hasselman.dds.sensors.contact.ContactSensorTypeDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
