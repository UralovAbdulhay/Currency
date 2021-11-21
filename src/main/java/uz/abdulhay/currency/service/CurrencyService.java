package uz.abdulhay.currency.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.abdulhay.currency.entity.ValCurs;
import uz.abdulhay.currency.entity.Valute;
import uz.abdulhay.currency.exceptions.ResourceNotFound;
import uz.abdulhay.currency.payload.Result;
import uz.abdulhay.currency.repository.CurrencyRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    //    @Value("${app.currency.url}")
    private final String URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    public Result getAll(Integer pageNum, Integer size) {
        Pageable pageable = PageRequest.of(pageNum, size);
        Page<Valute> page = currencyRepository.findAll(pageable);
        return Result.ok(page);
    }

    public Result getByCode(String code) {
        Valute page = currencyRepository.findByCharCodeOrNumCode(code, code)
                .orElseThrow(() -> new ResourceNotFound("Currency", "charCode", code));
        return Result.ok(page);
    }


    private List<Valute> resolveCurrency() {

        // GZIP  decode lash uchun
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        RestTemplate template = new RestTemplate(requestFactory);

        RestTemplate template = new RestTemplate();
        ValCurs result = template.getForObject(URL, ValCurs.class);

        if (result != null && result.getValute() != null) {
            result.getValute().forEach(e -> {
                try {
                    e.setUpdateAt(LocalDate.parse(result.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                } catch (DateTimeParseException ex) {
                    e.setUpdateAt(LocalDate.now());
                }
            });
            return result.getValute();
        }
        return null;
    }

    public void synchronizeData() {
        try {
            currencyRepository.saveAll(resolveCurrency());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
