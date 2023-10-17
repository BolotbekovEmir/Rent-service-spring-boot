package kg.mega.rentserviceproject.models.car;

import kg.mega.rentserviceproject.models.car.Car;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_price")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Car car;

    private BigDecimal price;

    private BigDecimal dayPrice;

    private LocalDate setDate;

    public Price(Car car, BigDecimal price, BigDecimal dayPrice) {
        this.car = car;
        this.price = price;
        this.dayPrice = dayPrice;
        this.setDate = LocalDate.now();
    }

}
