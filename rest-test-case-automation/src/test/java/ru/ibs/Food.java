package ru.ibs;

import com.fasterxml.jackson.annotation.JsonProperty;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Food {

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("exotic")
    private boolean exotic;


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Boolean getExotic() {
        return exotic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExotic(boolean exotic) {
        this.exotic = exotic;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public static List<Food> deserializeJsonResponseToFoodList(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, new TypeReference<List<Food>>() {});
    }

}

