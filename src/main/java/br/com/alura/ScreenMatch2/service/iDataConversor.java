package br.com.alura.ScreenMatch2.service;

public interface iDataConversor {
    <T> T getData(String json, Class<T> tClass );
}
