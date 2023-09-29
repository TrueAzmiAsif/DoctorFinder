package com.example.DoctorFinder.Transformers.EntityToDTO;

import com.example.DoctorFinder.DTO.ResponseDTO.DoctorResponse;
import com.example.DoctorFinder.Models.Doctor;

public class DoctorToDto {
    public static DoctorResponse doctorToDto(Doctor doc,String message){
        return DoctorResponse.builder()
                .name(doc.getName())
                .message(message)
                .build();
    }
}
