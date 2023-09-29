package com.example.DoctorFinder.DTO.RequestDTO;

import com.example.DoctorFinder.Enum.City;
import com.example.DoctorFinder.Enum.Speciality;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequest {
    long phoneNumber;
    String name;
    String city;
    String email;
    String speciality;
}
