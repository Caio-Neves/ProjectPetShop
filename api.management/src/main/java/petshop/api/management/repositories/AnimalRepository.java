package petshop.api.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petshop.api.management.models.AnimalModel;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalModel, UUID> {

    Optional<AnimalModel> findByName(String name);

    Optional<AnimalModel> findByBreed(String breed);
}
