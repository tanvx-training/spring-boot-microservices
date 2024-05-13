package tanvx.fsoft.repository.location;

import fsoft.tanvx.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tanvx.fsoft.repository.destination.Destination;

@SuperBuilder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
@EqualsAndHashCode(callSuper = true)
public class Location extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "display_flg")
    private Boolean displayFlg;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
}
