package org.hasselman.dds.sensors.contact;

public final class ContactSensorType {

    public java.lang.String id = "";
    public org.hasselman.dds.sensors.contact.Type type = org.hasselman.dds.sensors.contact.Type.from_int(0);
    public boolean isOpen;
    public org.hasselman.dds.sensors.contact.Status status = org.hasselman.dds.sensors.contact.Status.from_int(0);
    public short batteryPercentage;

    public ContactSensorType() {
    }

    public ContactSensorType(
        java.lang.String _id,
        org.hasselman.dds.sensors.contact.Type _type,
        boolean _isOpen,
        org.hasselman.dds.sensors.contact.Status _status,
        short _batteryPercentage)
    {
        id = _id;
        type = _type;
        isOpen = _isOpen;
        status = _status;
        batteryPercentage = _batteryPercentage;
    }

}
