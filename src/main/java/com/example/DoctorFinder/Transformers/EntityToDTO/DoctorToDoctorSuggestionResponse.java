package com.example.DoctorFinder.Transformers.EntityToDTO;

import com.example.DoctorFinder.DTO.ResponseDTO.DoctorSuggestionResponse;
import com.example.DoctorFinder.Models.Doctor;

public class DoctorToDoctorSuggestionResponse {
    public static DoctorSuggestionResponse doctorToDoctorSuggestionResponse(Doctor doc){
        return DoctorSuggestionResponse.builder()
                .name(doc.getName())
                .city(doc.getCity().toString())
                .speciality(doc.getSpeciality().toString())
                .build();
    }
}
