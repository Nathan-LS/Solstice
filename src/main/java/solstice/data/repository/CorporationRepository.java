package solstice.data.repository;

import solstice.data.entity.CorporationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorporationRepository extends CrudRepository<CorporationEntity, Integer> {
    public Optional<CorporationEntity> findOneById(Integer id);
}
