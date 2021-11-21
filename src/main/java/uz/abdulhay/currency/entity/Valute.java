package uz.abdulhay.currency.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Valute")
@Getter
public class Valute {

    // name - XML dagi maydon bilan bir xil bo'lishi kere

    @Id
    @Column(unique = true, name = "id")
    @XmlAttribute(name = "ID")
    private String id;

    @XmlElement(name = "CharCode")
    private String charCode;

    @XmlElement(name = "Value")
    private String value;

    @XmlElement(name = "Nominal")
    private String nominal;

    @XmlElement(name = "NumCode")
    private String numCode;

    @XmlElement(name = "Name")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate updateAt;


    public Integer getValue() {
        try {
            return Integer.parseInt(this.value.replace(",", "."));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getNominal() {
        try {
            return Integer.parseInt(this.nominal);
        } catch (NumberFormatException e) {
            return 1;
        }


    }

    @Override
    public String toString() {
        return "\nCurrency{" +
                "\nCharCode='" + charCode + '\'' +
                ",\n Value='" + value + '\'' +
                ",\n ID='" + id + '\'' +
                ",\n Nominal=" + nominal +
                ",\n NumCode='" + numCode + '\'' +
                ",\n Name='" + name + '\'' +
                "\n}";
    }
}
