package com.gestock.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public class Commande implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long commande_id ;



        //    @NotNull(message = "Le client ne doit pas être null")
//    @ManyToOne
//    @JoinColumn(name = "client_id", referencedColumnName = "id")
        private Long client;
        //
//    @NotNull(message = "Le produit ne doit pas être null")
//    @ManyToMany
//    @JoinTable(
//            name = "commande_produit",
//            joinColumns = @JoinColumn(name = "commande_id"),
//            inverseJoinColumns = @JoinColumn(name = "produit_id")
//    )
        private Long produit ;

        @DateTimeFormat(pattern = "yyyy-mm-jj 'T' hh:mm:ss")
        private LocalDateTime data_creation;


        @DateTimeFormat(pattern = "yyyy-mm-jj 'T' hh:mm:ss")
        private LocalDateTime data_modification;
}
