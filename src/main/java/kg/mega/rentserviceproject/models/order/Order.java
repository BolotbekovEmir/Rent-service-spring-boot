package kg.mega.rentserviceproject.models.order;

import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.models.user.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Discount discount;

    @Column(nullable = false)
    private LocalDate fromDate;

    @Column(nullable = false)
    private LocalDate toDate;

    @Column(nullable = false)
    private Integer countDays;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    public Order(
            User user,
            Car car,
            Discount discount,
            LocalDate fromDate,
            LocalDate toDate,
            Integer countDays,
            BigDecimal totalAmount) {
        this.user = user;
        this.car = car;
        this.discount = discount;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.countDays = countDays;
        this.totalAmount = totalAmount;
    }

}
