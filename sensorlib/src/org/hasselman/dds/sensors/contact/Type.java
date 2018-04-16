package org.hasselman.dds.sensors.contact;

import org.opensplice.dds.dcps.Utilities;

public class Type {

    private int __value;
    private static int __size = 2;
    private static org.hasselman.dds.sensors.contact.Type[] __array = new org.hasselman.dds.sensors.contact.Type[__size];

    public static final int _DOOR = 0;
    public static final org.hasselman.dds.sensors.contact.Type DOOR = new org.hasselman.dds.sensors.contact.Type(_DOOR);

    public static final int _WINDOW = 1;
    public static final org.hasselman.dds.sensors.contact.Type WINDOW = new org.hasselman.dds.sensors.contact.Type(_WINDOW);

    public int value ()
    {
        return __value;
    }

    public static org.hasselman.dds.sensors.contact.Type from_int (int value)
    {
        if (value >= 0 && value < __size) {
            return __array[value];
        }
        throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_OPERATION, null);
    }

    protected Type (int value)
    {
        __value = value;
        __array[__value] = this;
    }
}
