package br.com.alura.ScreenMatch2.service.translation;

import br.com.alura.ScreenMatch2.service.API.APIConsumn;
import tools.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultMyMemory {

    public static String getTranslation(String originalText){

        ObjectMapper mapper = new ObjectMapper();

        APIConsumn consumn = new APIConsumn();

        String codifiedText = URLEncoder.encode(originalText, StandardCharsets.UTF_8);


        String url = "https://api.mymemory.translated.net/get?q="
                + codifiedText
                + "&langpair=en%7Cpt-br";

        var json = consumn.getData(url);

        try{
            TranslationData translation = mapper.readValue(json, TranslationData.class);
            return translation.dadosResposta().textoTraduzido();
        }catch (Exception e){
            throw new RuntimeException("Error while translating text", e);
        }
    }
}
