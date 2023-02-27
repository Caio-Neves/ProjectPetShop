package petshop.api.management.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petshop.api.management.dtos.AnimalDto;
import petshop.api.management.models.AnimalModel;
import petshop.api.management.services.AnimalService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Object> saveAnimal(@RequestBody @Valid AnimalDto animalDto){

        var animalModel = new AnimalModel();

        BeanUtils.copyProperties(animalDto, animalModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.save(animalModel));
    }

    @GetMapping
    public ResponseEntity<List<AnimalModel>>getAllAnimals(){return ResponseEntity.status(HttpStatus.OK).body(animalService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAnimalByName(@PathVariable(value = "id") UUID id){
        Optional<AnimalModel> animalModelOptional = animalService.findById(id);

        if(!animalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Animal not exists!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(animalModelOptional.get());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAnimalById(@PathVariable(value = "id") UUID id){
        Optional<AnimalModel> animalModelOptional = animalService.findById(id);

        if(!animalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: Animal by name not found!");
        }

        animalService.deleteById(animalModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("Animal deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid AnimalDto animalDto){

        Optional<AnimalModel> animalModelOptional = animalService.findById(id);

        if(!animalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Animal not found!");
        }

        var animalModel = animalModelOptional.get();
        BeanUtils.copyProperties(animalDto, animalModel);
        animalModel.setId(animalModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(animalService.save(animalModel));
    }

}
