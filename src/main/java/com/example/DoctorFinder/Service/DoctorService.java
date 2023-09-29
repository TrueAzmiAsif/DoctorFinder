package com.example.DoctorFinder.Service;

import com.example.DoctorFinder.DTO.RequestDTO.DoctorRequest;
import com.example.DoctorFinder.DTO.ResponseDTO.DoctorResponse;
import com.example.DoctorFinder.DTO.ResponseDTO.DoctorSuggestionResponse;
import com.example.DoctorFinder.Enum.Symptoms;
import com.example.DoctorFinder.Exceptions.*;

import java.util.List;

public interface DoctorService {
    public DoctorResponse add(DoctorRequest docReq)throws InvalidNameException, InvalidCityException, InvalidPhoneNumberException, InvalidEmailException, InvalidSpecialityException;
    public List<DoctorSuggestionResponse> find(String city, Symptoms symptom) throws InvalidCityException;
}
