package com.example.DoctorFinder.Models;

import com.example.DoctorFinder.Enum.City;
import com.example.DoctorFinder.Enum.Speciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    //@NotNull(message = "Phone number cannot be null")
    //@Length(min=10,max = 10,message = "Phone number should be of 10 digits")
    long phoneNumber;
    //@NotNull(message = "Name cannot be null")
    //@Size(min=3,message = "Name should be at least 3 characters long")
    String name;
    @Enumerated(EnumType.STRING)
    //@NotNull(message = "City Name cannot be null")
    //@Size(max = 20,message = "City name should be at max 20 characters long")
    City city;
    //@NotNull(message = "E-mail cannot be null")
    //@Email(message = "Please enter a valid email Id", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    String email;
    @Enumerated(EnumType.STRING)
    Speciality speciality;
}
