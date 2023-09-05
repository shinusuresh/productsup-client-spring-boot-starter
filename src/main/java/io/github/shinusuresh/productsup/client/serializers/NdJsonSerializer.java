package io.github.shinusuresh.productsup.client.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.shinusuresh.productsup.client.data.BaseStreamData;

import java.io.IOException;
import java.util.List;

/**
 * Custom serializer for {@link org.springframework.http.MediaType#APPLICATION_NDJSON_VALUE}.
 */
public class NdJsonSerializer extends StdSerializer<List<? extends BaseStreamData>> {

    private static final String NEW_LINE_DELIMITER = "\n";

    public NdJsonSerializer() {
        super(List.class, false);
    }

    @Override
    public void serialize(List<? extends BaseStreamData> values, JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        if (values.isEmpty()) {
            return;
        }

        for (Object o : values) {
            gen.writeObject(o);
            gen.writeRawValue(NEW_LINE_DELIMITER);
        }
    }
}
