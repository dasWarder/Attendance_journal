package by.itechart.web.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


@Component
public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getJsonObject(Object serializationObject) throws JsonProcessingException {

        return objectMapper.writeValueAsString(serializationObject);
    }
}
