package kg.mega.rentserviceproject.models.car;

import kg.mega.rentserviceproject.enums.BodyType;
import kg.mega.rentserviceproject.enums.ModelClass;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_model")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Producer producer;

    @Column(nullable = false)
    private String name;

    @Enumerated
    @Column(nullable = false)
    private ModelClass modelClass;

    @Enumerated
    @Column(nullable = false)
    private BodyType bodyType;

    public Model(
            String modelName,
            BodyType bodyType,
            ModelClass modelClass,
            Producer producer
    ) {
        this.name = modelName;
        this.bodyType = bodyType;
        this.modelClass = modelClass;
        this.producer = producer;
    }

}
