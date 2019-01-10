package org.jerseyspring.serial;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.node.TextNode;

import java.io.IOException;
import java.time.Instant;

/**
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com> on 10.01.2019 14:32
 */
public class InstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = (TextNode)codec.readTree(jp);
        String dateString = node.getTextValue();//textValue();
        Instant instant = Instant.parse(dateString);
        //Instant dateTime = Instant.ofInstant(instant, ZoneId.systemDefault());
        return instant;// dateTime;
    }
}
