package uz.abdulhay.currency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final CurrencyService currencyService;

    @Override
    public void run(String...args) throws Exception {
        currencyService.synchronizeData();
    }

    // har 1 soatda ishlaydi
    @Scheduled(cron = "0 1 * * * *")
    public void synchronizeData() {

        currencyService.synchronizeData();
//          System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss SSS").format(LocalTime.now()));
    }


}
