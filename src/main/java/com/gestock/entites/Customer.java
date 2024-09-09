package com.gestock.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestock.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastname;

    @NotNull
    @NotEmpty
    private String dob;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    private Genre sexe;


    @NotNull
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH-mm-ss")
    private LocalDateTime created_at;

    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
    }

//    @JsonIgnore
    @Column(name = "update_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH-mm-ss")
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
