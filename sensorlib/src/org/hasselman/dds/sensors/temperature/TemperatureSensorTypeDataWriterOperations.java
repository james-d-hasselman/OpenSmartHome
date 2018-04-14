package org.hasselman.dds.sensors.temperature;

public interface TemperatureSensorTypeDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data);

    long register_instance_w_timestamp(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            DDS.Time_t source_timestamp);

    int unregister_instance(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long handle);

    int unregister_instance_w_timestamp(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long handle);

    int write_w_timestamp(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long instance_handle);

    int dispose_w_timestamp(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long instance_handle);

    int writedispose_w_timestamp(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data, 
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            org.hasselman.dds.sensors.temperature.TemperatureSensorTypeHolder key_holder, 
            long handle);
    
    long lookup_instance(
            org.hasselman.dds.sensors.temperature.TemperatureSensorType instance_data);

}
