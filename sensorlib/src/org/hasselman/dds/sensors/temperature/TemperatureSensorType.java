package org.hasselman.dds.sensors.temperature;

public final class TemperatureSensorType {

    public short id;
    public float temperature;
    public float humidity;
    public org.hasselman.dds.sensors.temperature.TemperatureScale scale = org.hasselman.dds.sensors.temperature.TemperatureScale.from_int(0);

    public TemperatureSensorType() {
    }

    public TemperatureSensorType(
        short _id,
        float _temperature,
        float _humidity,
        org.hasselman.dds.sensors.temperature.TemperatureScale _scale)
    {
        id = _id;
        temperature = _temperature;
        humidity = _humidity;
        scale = _scale;
    }

}
