package petshop.api.management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import petshop.api.management.enums.Treatment;

@Data
public class AnimalDto {

    @NotBlank
    private String name;

    @NotBlank
    private String specie;

    @NotBlank
    private String breed;

    @NotBlank
    private String height;

    @NotNull
    private Double peso;

    @NotNull
    private Treatment treatment;

}
