package com.harsh.hospitalManagement.entity;

import com.harsh.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString //will provide toString method for all the fields.
@Getter
@Setter
@Table(
        name="patient",
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name", "birth_date"})
        },
        indexes = {
                @Index(name =  "idx_patient_birth_date", columnList = "birth_date")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //will generate value on auto incremented db columns
    private Long id;

//    @Column(name = "patient_name", nullable = false, length = 40) //by this it will update the column name from name to patient_name
    @Column(nullable = false, length = 40)
    private String name;

    @ToString.Exclude //will exclude this variable in toString method
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(unique = true, nullable = false) //using this annotation we do not need to explicitly declare this container as unique
    private String email;

    private String gender;

    @CreationTimestamp //it will fill it with the time of its creation
    @Column(updatable = false) //not mandatory because we have already used the annotation @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
}
