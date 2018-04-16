package org.hasselman.dds.sensors.contact;

public class ContactSensorTypeTypeSupport extends org.opensplice.dds.dcps.TypeSupportImpl implements DDS.TypeSupportOperations
{
    private long copyCache;

    public ContactSensorTypeTypeSupport()
    {
        super("org::hasselman::dds::sensors::contact::ContactSensorType",
              "",
              "id",
              null,
              org.hasselman.dds.sensors.contact.ContactSensorTypeMetaHolder.metaDescriptor);
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
    }

    @Override
    protected DDS.DataWriter create_datawriter ()
    {
        return new ContactSensorTypeDataWriterImpl(this);
    }

    @Override
    protected DDS.DataReader create_datareader ()
    {
        return new ContactSensorTypeDataReaderImpl(this);
    }

    @Override
    protected DDS.DataReaderView create_dataview ()
    {
        return new ContactSensorTypeDataReaderViewImpl(this);
    }
}
