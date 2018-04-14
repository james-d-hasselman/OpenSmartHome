package org.hasselman.dds.sensors.temperature;

public class TemperatureSensorTypeTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private long copyCache;

    public TemperatureSensorTypeTypeSupport()
    {
        super("org::hasselman::dds::sensors::temperature::TemperatureSensorType",
              "",
              "id",
              null,
              org.hasselman.dds.sensors.temperature.TemperatureSensorTypeMetaHolder.metaDescriptor);
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new TemperatureSensorTypeDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new TemperatureSensorTypeDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new TemperatureSensorTypeDataReaderViewImpl(this);
    }
}
