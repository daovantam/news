package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class ModelToJsonUtil {
    private String value;

    public ModelToJsonUtil(String value) {
        this.value = value;
    }

    public static ModelToJsonUtil of(BufferedReader reader) {
        StringBuilder json = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelToJsonUtil(json.toString());
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(this.value, tClass);
        } catch (IOException e) {
            return null;
        }
    }
}
