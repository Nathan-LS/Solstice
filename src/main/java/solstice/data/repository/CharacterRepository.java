package solstice.data.repository;

import solstice.data.entity.CharacterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends CrudRepository<CharacterEntity, Integer> {
    public Optional<CharacterEntity> findOneById(Integer id);
}
