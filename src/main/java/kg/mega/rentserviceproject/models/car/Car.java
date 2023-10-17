package kg.mega.rentserviceproject.models.car;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Model model;

    @Column(nullable = false)
    private String vin;

    @Column(nullable = false)
    private LocalDate yearProduction;

    @Column(nullable = false)
    private boolean isRent;

    @Column(nullable = false)
    private boolean isActive;

    private String pathImg;

    public Car(String vin,
               LocalDate yearProduction,
               Model model,
               boolean isRent,
               boolean isActive
    ) {
        this.vin = vin;
        this.yearProduction = yearProduction;
        this.model = model;
        this.isRent = isRent;
        this.isActive = isActive;
    }

}
