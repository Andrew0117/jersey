package org.jerseyspring.serial;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

/**
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com> on 10.01.2019 14:36
 */
public class InstantSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(Instant dateTime, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        /*Instant instant = dateTime.toInstant(ZoneOffset.UTC);*/
        jg.writeString(dateTime.toString() /*DateTimeFormatter.ISO_INSTANT.format(instant)*/);
    }
}
