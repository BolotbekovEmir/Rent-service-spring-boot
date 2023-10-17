package kg.mega.rentserviceproject.models.order;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_discount")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal discountPercentage;

    @Column(nullable = false)
    private LocalDate setDate;

    public Discount(String name, BigDecimal discountPercentage, LocalDate setDate) {
        this.name = name;
        this.discountPercentage = discountPercentage;
        this.setDate = setDate;
    }

}
