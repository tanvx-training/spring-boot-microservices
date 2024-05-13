package tanvx.fsoft.repository.destination;

import fsoft.tanvx.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tanvx.fsoft.repository.location.Location;

import java.util.List;

@SuperBuilder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "destination")
@EqualsAndHashCode(callSuper = true)
public class Destination extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Long destinationId;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "population")
    private Integer population;

    @Column(name = "capital_city")
    private String capitalCity;

    @Column(name = "language")
    private String language;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;

    @Column(name = "continent")
    private String continent;

    @OneToMany(mappedBy = "destination")
    private List<Location> locations;
}
