package br.com.alura.ScreenMatch2.service.translation;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseData(@JsonAlias("translatedText") String textoTraduzido) {
}
