package tanvx.fsoft.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    /**
     * Find all locations in list of id and have delete_flg
     * @param locationIds List<Long>
     * @param deleteFlg Boolean
     * @return List<Location>
     */
    List<Location> findAllByLocationIdInAndDeleteFlg(List<Long> locationIds, Boolean deleteFlg);

    /**
     * Find all locations by destination id and delete_flg
     * @param destinationId Long
     * @param deleteFlg Boolean
     * @return List<Location>
     */
    List<Location> findAllByDestinationDestinationIdAndDeleteFlg(Long destinationId, Boolean deleteFlg);
}
