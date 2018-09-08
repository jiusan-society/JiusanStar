package gov.jiusan.star.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
public class JacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Optional<String> toString(Object obj) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(json);
    }

    public static <T> Optional<T> toObj(String jsonString, Class<T> clz) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonString, clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }

    public static <T> Optional<T> toObj(String jsonString, TypeReference<T> cls) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonString, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }

}
