package patika.dev.veterineryonetimsistemi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Table(name = "vaccine")

    public class Vaccine {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)


        @Column (name="id")
        private Long id;


        @Column(name= "name" ,nullable = false)
        private String name;

        @Column(name= "code",nullable = false)
        private String code;

        @Column(name= "protectionStartDate",nullable = false)
        private LocalDate protectionStartDate;

        @Column(name= "protectionFinishDate",nullable = false)
        private LocalDate protectionFinishDate;

        @ManyToOne (fetch = FetchType.EAGER)
        @JoinColumn(name= "animal_id")
        private Animal animal;

    }
