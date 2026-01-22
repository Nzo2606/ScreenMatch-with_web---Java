package br.com.alura.ScreenMatch2.service;

import br.com.alura.ScreenMatch2.model.SeriesData;
import tools.jackson.databind.ObjectMapper;

public class DataConversor implements iDataConversor{

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> tClass) {
        return mapper.readValue(json, tClass);
    }
}
