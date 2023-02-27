package petshop.api.management.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petshop.api.management.models.AnimalModel;
import petshop.api.management.repositories.AnimalRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public AnimalModel save(AnimalModel animalModel){return animalRepository.save(animalModel);}

    public List<AnimalModel> findAll(){return animalRepository.findAll();}

    public Optional<AnimalModel> findById(UUID id){return animalRepository.findById(id);}

    public Optional<AnimalModel> findByName(String name){return animalRepository.findByName(name);}

    public void deleteById(UUID id){animalRepository.deleteById(id);}

    public Optional<AnimalModel> findByBreed(String breed) {return animalRepository.findByBreed(breed);}
}
