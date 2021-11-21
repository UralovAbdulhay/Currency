package uz.abdulhay.currency.service;


import org.springframework.web.client.RestTemplate;
import uz.abdulhay.currency.entity.ValCurs;

import java.io.IOException;

public class CurrencyTemplate {

    public static void main(String[] args) throws IOException {
        resolveCurrency();
    }

    private static void resolveCurrency() throws IOException {

        // GZIP  decode lash uchun
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        RestTemplate template = new RestTemplate(requestFactory);

        RestTemplate template = new RestTemplate();
        ValCurs result = template.getForObject("http://www.cbr.ru/scripts/XML_daily.asp", ValCurs.class);
        System.out.println("\n******************************");
        System.out.println(result);
    }


}
