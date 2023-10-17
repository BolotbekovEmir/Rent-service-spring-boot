package kg.mega.rentserviceproject.models.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    private String email;

    @Column(nullable = false)
    private LocalDate dateRegister;

    @Column(nullable = false)
    private boolean isActive;

    public User(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;

        this.isActive = true;
        this.dateRegister = LocalDate.now();
    }

}
