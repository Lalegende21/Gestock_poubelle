package com.gestock.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long produit_id ;

    @NotNull(message = "le libelle ne droi pas etre vide ")
    @Size(min = 2 ,max = 20 , message = "le libelle est compris entre 2 et 20 ")
    private String libelle ;

    @NotNull
    @Max(value = 2000 , message = "le montant doit etre inferieur a 2000xaf")
    @Min(value = 0 , message = "le montant doit etre aumoins 0")
    private int prix ;

    @NotNull(message = "le description ne droi pas etre vide ")
    @Size(min = 2 ,max = 20 , message = "le description est compris entre 2 et 20 ")
    private  String description ;

    @Max(value = 100 , message = "la quantite doit etre inferieur a 2000xaf")
    @Min(value = 0 , message = "la quantiter doit etre aumoins 0")
    @NotNull
    private int qte ;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date_creation;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date_modificaion;


}
