package uz.abdulhay.currency.entity.user.superEntity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
    public abstract class SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

}


