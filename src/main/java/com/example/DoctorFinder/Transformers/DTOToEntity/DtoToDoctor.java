package com.example.DoctorFinder.Transformers.DTOToEntity;

import com.example.DoctorFinder.DTO.RequestDTO.DoctorRequest;
import com.example.DoctorFinder.Enum.City;
import com.example.DoctorFinder.Enum.Speciality;
import com.example.DoctorFinder.Models.Doctor;

public class DtoToDoctor {
    public static Doctor dtoToDoctor(DoctorRequest docReq, City ct, Speciality sp){
        return Doctor.builder()
                .name(docReq.getName())
                .email(docReq.getEmail())
                .phoneNumber(docReq.getPhoneNumber())
                .city(ct)
                .speciality(sp)
                .build();
    }
}
