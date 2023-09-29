package com.example.DoctorFinder.Transformers.DTOToEntity;

import com.example.DoctorFinder.DTO.RequestDTO.PatientRequest;
import com.example.DoctorFinder.Enum.Symptoms;
import com.example.DoctorFinder.Models.Patient;

public class DtoToPatient {
    public static Patient dtoToPatient(PatientRequest patReq, Symptoms symp){
        return Patient.builder()
                .name(patReq.getName())
                .city(patReq.getCity())
                .email(patReq.getEmail())
                .phoneNumber(patReq.getPhoneNumber())
                .symptom(symp)
                .build();
    }
}
