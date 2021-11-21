package uz.abdulhay.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class ValCurs implements Serializable {

    @XmlAttribute(name = "name")
    private String Name;

    // name - XML dagi maydon bilan bir xil bo'lishi kere
    @XmlAttribute(name = "Date")
    private String date;

    // name - XML dagi maydon bilan bir xil bo'lishi kere
    @XmlElement(name = "Valute")
    private List<Valute> valute;


    @Override
    public String toString() {
        return "ValCurs{" +
                "name='" + Name + '\'' +
                ", Date='" + date + '\'' +
                ", \n Valute=" + valute +
                '}';
    }
}
