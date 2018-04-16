package org.hasselman.dds.sensors.contact;

public interface ContactSensorTypeDataReaderOperations extends
    DDS.DataReaderOperations
{

    int read(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_w_condition(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int take_w_condition(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            DDS.ReadCondition a_condition);

    int read_next_sample(
            org.hasselman.dds.sensors.contact.ContactSensorTypeHolder received_data, 
            DDS.SampleInfoHolder sample_info);

    int take_next_sample(
            org.hasselman.dds.sensors.contact.ContactSensorTypeHolder received_data, 
            DDS.SampleInfoHolder sample_info);

    int read_instance(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples,
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_instance(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int take_next_instance(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            int sample_states, 
            int view_states, 
            int instance_states);

    int read_next_instance_w_condition(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int take_next_instance_w_condition(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq, 
            int max_samples, 
            long a_handle, 
            DDS.ReadCondition a_condition);

    int return_loan(
            org.hasselman.dds.sensors.contact.ContactSensorTypeSeqHolder received_data, 
            DDS.SampleInfoSeqHolder info_seq);

    int get_key_value(
            org.hasselman.dds.sensors.contact.ContactSensorTypeHolder key_holder, 
            long handle);
    
    long lookup_instance(
            org.hasselman.dds.sensors.contact.ContactSensorType instance);

}
