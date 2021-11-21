package uz.abdulhay.currency.entity.user.superEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(unique = true)
    private String name;

    public Role(String user) {
        this.name = user;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
