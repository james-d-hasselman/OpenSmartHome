package org.hasselman.dds.sensors.temperature;

import org.opensplice.dds.dcps.Utilities;

public class TemperatureScale {

    private int __value;
    private static int __size = 3;
    private static org.hasselman.dds.sensors.temperature.TemperatureScale[] __array = new org.hasselman.dds.sensors.temperature.TemperatureScale[__size];

    public static final int _CELSIUS = 0;
    public static final org.hasselman.dds.sensors.temperature.TemperatureScale CELSIUS = new org.hasselman.dds.sensors.temperature.TemperatureScale(_CELSIUS);

    public static final int _FAHRENHEIT = 1;
    public static final org.hasselman.dds.sensors.temperature.TemperatureScale FAHRENHEIT = new org.hasselman.dds.sensors.temperature.TemperatureScale(_FAHRENHEIT);

    public static final int _KELVIN = 2;
    public static final org.hasselman.dds.sensors.temperature.TemperatureScale KELVIN = new org.hasselman.dds.sensors.temperature.TemperatureScale(_KELVIN);

    public int value ()
    {
        return __value;
    }

    public static org.hasselman.dds.sensors.temperature.TemperatureScale from_int (int value)
    {
        if (value >= 0 && value < __size) {
            return __array[value];
        }
        throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_OPERATION, null);
    }

    protected TemperatureScale (int value)
    {
        __value = value;
        __array[__value] = this;
    }
}
