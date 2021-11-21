package uz.abdulhay.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.abdulhay.currency.entity.Valute;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Valute, String> {

    Optional<Valute> findByCharCodeOrNumCode(String charCode, String numCode );



}
