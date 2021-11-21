package uz.abdulhay.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import uz.abdulhay.currency.service.CurrencyService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class CurrencyApplication {

    private final CurrencyService currencyService;

    public static void main(String[] args) {
        SpringApplication.run(CurrencyApplication.class, args);
    }


    @Scheduled(cron = "0 */1 * * * *")
    public void synchronizeData() {
        System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss SSS").format(LocalTime.now()));
        currencyService.synchronizeData();
        System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss SSS").format(LocalTime.now()));

    }


}
