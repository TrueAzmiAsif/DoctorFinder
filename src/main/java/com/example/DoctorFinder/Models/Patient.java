package com.example.DoctorFinder.Models;

import com.example.DoctorFinder.Enum.City;
import com.example.DoctorFinder.Enum.Speciality;
import com.example.DoctorFinder.Enum.Symptoms;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    long phoneNumber;
    String name;
    String city;
    String email;
    @Enumerated(EnumType.STRING)
    Symptoms symptom;
}
