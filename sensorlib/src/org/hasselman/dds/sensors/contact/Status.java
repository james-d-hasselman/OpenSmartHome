package org.hasselman.dds.sensors.contact;

import org.opensplice.dds.dcps.Utilities;

public class Status {

    private int __value;
    private static int __size = 2;
    private static org.hasselman.dds.sensors.contact.Status[] __array = new org.hasselman.dds.sensors.contact.Status[__size];

    public static final int _OK = 0;
    public static final org.hasselman.dds.sensors.contact.Status OK = new org.hasselman.dds.sensors.contact.Status(_OK);

    public static final int _MAINTENANCE_REQUIRED = 1;
    public static final org.hasselman.dds.sensors.contact.Status MAINTENANCE_REQUIRED = new org.hasselman.dds.sensors.contact.Status(_MAINTENANCE_REQUIRED);

    public int value ()
    {
        return __value;
    }

    public static org.hasselman.dds.sensors.contact.Status from_int (int value)
    {
        if (value >= 0 && value < __size) {
            return __array[value];
        }
        throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_OPERATION, null);
    }

    protected Status (int value)
    {
        __value = value;
        __array[__value] = this;
    }
}
