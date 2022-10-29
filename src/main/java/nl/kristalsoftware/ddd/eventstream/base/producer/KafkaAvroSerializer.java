package nl.kristalsoftware.ddd.eventstream.base.producer;

import org.apache.avro.Schema;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class KafkaAvroSerializer<T extends SpecificRecord> implements Serializer<T> {

    private final EncoderFactory encoderFactory = EncoderFactory.get();

    public byte[] serialize(String topic, T record) {
        Schema schema = null;
        if (record == null) {
            return null;
        } else {
            try {
                schema = record.getSchema();

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                out.write(ByteBuffer.allocate(5).putInt(0).array());    // write first five bytes with 0 value
                BinaryEncoder encoder = this.encoderFactory.directBinaryEncoder(out, null);
                DatumWriter<T> writer = new SpecificDatumWriter<>(schema);
                writer.write(record, encoder);
                encoder.flush();

                byte[] bytes = out.toByteArray();
                out.close();
                return bytes;
            } catch (RuntimeException | IOException ioEx) {
                throw new SerializationException("Error serializing Avro message", ioEx);
            }
        }
    }
}
