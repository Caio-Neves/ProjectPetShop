package petshop.api.management.models;

import jakarta.persistence.*;
import lombok.Data;
import petshop.api.management.enums.Treatment;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ANIMALS")
public class AnimalModel implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(unique = false, nullable = false)
    private String specie;

    @Column(unique = false, nullable = false)
    private String breed;

    @Column(unique = false, nullable = false)
    private String height;

    @Column(unique = false, nullable = false)
    private Double peso;

    @Column(unique = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private Treatment treatment;

}
