package com.example.DoctorFinder.Service;

import com.example.DoctorFinder.DTO.RequestDTO.PatientRequest;
import com.example.DoctorFinder.DTO.ResponseDTO.PatientResponse;
import com.example.DoctorFinder.Exceptions.*;
import com.example.DoctorFinder.Models.Patient;

public interface PatientService {
    public PatientResponse add(PatientRequest patReq) throws InvalidCityException, InvalidNameException, InvalidPhoneNumberException, InvalidEmailException, InvalidSymptomException;
    public Patient find(int patId) throws PatientNotFoundException;
    public PatientResponse remove(long patNumber) throws PatientNotFoundException;

}
