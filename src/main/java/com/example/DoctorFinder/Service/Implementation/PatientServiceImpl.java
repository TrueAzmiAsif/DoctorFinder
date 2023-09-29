package com.example.DoctorFinder.Service.Implementation;

import com.example.DoctorFinder.DTO.RequestDTO.PatientRequest;
import com.example.DoctorFinder.DTO.ResponseDTO.DoctorResponse;
import com.example.DoctorFinder.DTO.ResponseDTO.PatientResponse;
import com.example.DoctorFinder.Enum.City;
import com.example.DoctorFinder.Enum.Speciality;
import com.example.DoctorFinder.Enum.Symptoms;
import com.example.DoctorFinder.Exceptions.*;
import com.example.DoctorFinder.Models.Doctor;
import com.example.DoctorFinder.Models.Patient;
import com.example.DoctorFinder.Repository.PatientRepository;
import com.example.DoctorFinder.Service.PatientService;
import com.example.DoctorFinder.Transformers.DTOToEntity.DtoToDoctor;
import com.example.DoctorFinder.Transformers.DTOToEntity.DtoToPatient;
import com.example.DoctorFinder.Transformers.EntityToDTO.DoctorToDto;
import com.example.DoctorFinder.Transformers.EntityToDTO.PatientToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patResp;
    public PatientResponse add(PatientRequest patReq) throws InvalidCityException, InvalidNameException, InvalidPhoneNumberException, InvalidEmailException, InvalidSymptomException {
        Boolean flag=true;
        if(patReq.getName().length()<3){
            throw new InvalidNameException("Name should be at least 3 characters long");
        }

        if(patReq.getCity().length()>20 || patReq.getCity().length()==0){
            throw new InvalidCityException("City name should be at max 20 characters long and not null");
        }
        if(Long.toString(patReq.getPhoneNumber()).length()!=10){
            throw new InvalidPhoneNumberException("Phone number should be having 10 digits");
        }
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9.-_]+@[a-zA-Z0-9]+\\.[a-z]");
        Matcher matcher = pattern.matcher(patReq.getEmail());
        if(!matcher.find()){
            throw new InvalidEmailException("The entered email is invalid");
        }
        Symptoms symptom=null;
        for(Symptoms sp: Symptoms.values()){
            if(patReq.getSymptom().equalsIgnoreCase(sp.toString())){
                flag=false;
                symptom=sp;
            }
        }
        if(flag){
            throw new InvalidSymptomException("There isn’t any doctor present at your location for your symptom");
        }
        return PatientToDto.patientToDto(patResp.save(DtoToPatient.dtoToPatient(patReq,symptom)),"Thank you for registering yourself with us!");
    }

    public Patient find(int patId) throws PatientNotFoundException {
        Optional<Patient> optPat=patResp.findById(patId);
        if(optPat.isEmpty()){
            throw new PatientNotFoundException("The patient ID is invalid");
        }
        return optPat.get();
    }

    public PatientResponse remove(long patNumber) throws PatientNotFoundException {
        Patient pat=patResp.findByPhoneNumber(patNumber);
        if(pat==null)
            throw new PatientNotFoundException("The entered contact number is not registered with us");
            PatientResponse patResponse=PatientToDto.patientToDto(pat,"The patient has been successfully removed from our inventory");
        patResp.deleteById(pat.getId());
        return patResponse;
    }
}
