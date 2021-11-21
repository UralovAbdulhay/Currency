package uz.abdulhay.currency.entity.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.abdulhay.currency.entity.user.superEntity.Role;
import uz.abdulhay.currency.entity.user.superEntity.SuperEntity;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends SuperEntity {

    @Column(nullable = false)
    String fullName;

    @Column(unique = true)
    String phone;

    @Column(unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;


}
