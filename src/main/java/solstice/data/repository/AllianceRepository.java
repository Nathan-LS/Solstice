package solstice.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import solstice.data.entity.AllianceEntity;

import java.util.Optional;

@Repository
public interface AllianceRepository extends CrudRepository<AllianceEntity, Integer> {
    public Optional<AllianceEntity> findOneById(Integer id);
}
