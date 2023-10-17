package kg.mega.rentserviceproject.models.car;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_producer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    public Producer(
            String producerName,
            String producerCountry
    ) {
        this.name = producerName;
        this.country = producerCountry;
    }
}
