package br.com.alura.ScreenMatch2.service.conversor;

public interface iDataConversor {
    <T> T getData(String json, Class<T> tClass );
}
