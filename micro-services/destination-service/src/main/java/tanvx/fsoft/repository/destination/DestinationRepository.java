package tanvx.fsoft.repository.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    /**
     * Find destination by name
     * @param name String
     * @return Optional<Destination>
     */
    Optional<Destination> findDestinationByName(String name);
}
