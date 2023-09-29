package com.example.DoctorFinder.Transformers.EntityToDTO;

import com.example.DoctorFinder.DTO.ResponseDTO.PatientResponse;
import com.example.DoctorFinder.Models.Patient;

public class PatientToDto {
    public static PatientResponse patientToDto(Patient patient,String msg){
        return PatientResponse.builder()
                .name(patient.getName())
                .message(msg)
                .build();
    }
}
