package org.hasselman.dds.sensors.contact;

public final class ContactSensorTypeMetaHolder
{

    public static java.lang.String metaDescriptor[] = { "<MetaData version=\"1.0.0\"><Module name=\"org\"><Module name=\"hasselman\"><Module name=\"dds\"><Module name=\"sensors\"><Module name=\"contact\"><Enum name=\"Type\"><Element name=\"DOOR\" value=\"0\"/><Element name=\"WINDOW\" value=\"1\"/></Enum><Enum name=\"Status\"><Element name=\"OK\" value=\"0\"/><Element name=\"MAINTENANCE_REQUIRED\" value=\"1\"/></Enum><Struct name=\"ContactSensorType\"><Member name=\"id\"><String/></Member><Member name=\"type\"><Type name=\"Type\"/></Member><Member name=\"isOpen\"><Boolean/></Member><Member name=\"status\"><Type name=\"Status\"/></Member><Member name=\"batteryPercentage\"><Short/></Member></Struct></Module></Module></Module></Module></Module></MetaData>" };

    public ContactSensorTypeMetaHolder()
    {
    }

}
