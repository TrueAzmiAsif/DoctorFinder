package com.example.DoctorFinder.Service.Implementation;

import com.example.DoctorFinder.DTO.RequestDTO.DoctorRequest;
import com.example.DoctorFinder.DTO.ResponseDTO.DoctorResponse;
import com.example.DoctorFinder.DTO.ResponseDTO.DoctorSuggestionResponse;
import com.example.DoctorFinder.Enum.City;
import com.example.DoctorFinder.Enum.Speciality;
import com.example.DoctorFinder.Enum.Symptoms;
import com.example.DoctorFinder.Exceptions.*;
import com.example.DoctorFinder.Models.Doctor;
import com.example.DoctorFinder.Repository.DoctorRepository;
import com.example.DoctorFinder.Service.DoctorService;
import com.example.DoctorFinder.Transformers.DTOToEntity.DtoToDoctor;
import com.example.DoctorFinder.Transformers.EntityToDTO.DoctorToDoctorSuggestionResponse;
import com.example.DoctorFinder.Transformers.EntityToDTO.DoctorToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository docRep;
    public DoctorResponse add(DoctorRequest docReq) throws InvalidNameException, InvalidCityException, InvalidPhoneNumberException, InvalidEmailException, InvalidSpecialityException {
        Boolean flag=true;
        City city=null;
        for(City ct: City.values()){
            if(docReq.getCity().equalsIgnoreCase(ct.toString())){
                flag=false;
                city=ct;
            }
        }

        if(flag){
            throw new InvalidCityException("Currently we are not functional in the mentioned city");
        }
        flag=true;
        if(docReq.getName().length()<3){
            throw new InvalidNameException("Name should be at least 3 characters long");
        }

        if(docReq.getCity().length()>20 || docReq.getCity().length()==0){
            throw new InvalidCityException("City name should be at max 20 characters long and not null");
        }
        if(Long.toString(docReq.getPhoneNumber()).length()!=10){
            throw new InvalidPhoneNumberException("Phone number should be having 10 digits");
        }
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9.-_]+@[a-zA-Z0-9]+\\.[a-z]");
        Matcher matcher = pattern.matcher(docReq.getEmail());
        if(!matcher.find()){
            throw new InvalidEmailException("The entered email is invalid");
        }
        Speciality speciality=null;
        for(Speciality sp: Speciality.values()){
            if(docReq.getSpeciality().equalsIgnoreCase(sp.toString())){
                flag=false;
                speciality=sp;
            }
        }
        if(flag){
            throw new InvalidSpecialityException("We don't have the required department yet!");
        }
        return DoctorToDto.doctorToDto(docRep.save(DtoToDoctor.dtoToDoctor(docReq,city,speciality)),"Thank you for registering yourself with us!");
    }

    public List<DoctorSuggestionResponse> find(String city, Symptoms symptom) throws InvalidCityException {
        List<Doctor> docList=new ArrayList<>();
        List<DoctorSuggestionResponse> ans=new ArrayList<>();
        Speciality speciality=null;
        Boolean flag=true;
        City cityName=null;
        for(City ct: City.values()){
            if(city.equalsIgnoreCase(ct.toString())){
                flag=false;
                cityName=ct;
            }
        }
        if(flag){
            throw new InvalidCityException("We are still waiting to expand to your location");
        }
        if(symptom==Symptoms.ARTHRITIS || symptom==Symptoms.BACK_PAIN || symptom==Symptoms.TISSUE_INJURIES)
            speciality=Speciality.ORTHOPEDIC;
        else if(symptom==Symptoms.DYSMENORRHEA)
            speciality=Speciality.GYNECOLOGY;
        else if(symptom==Symptoms.SKIN_INFECTION || symptom==Symptoms.SKIN_BURN)
            speciality=Speciality.DERMATOLOGY;
        else
            speciality=Speciality.ENT_SPECIALIST;
        docList=docRep.findByCityAndSpeciality(cityName,speciality);
        for(Doctor x: docList){
            ans.add(DoctorToDoctorSuggestionResponse.doctorToDoctorSuggestionResponse(x));
        }
        return ans;
    }

    public DoctorResponse remove(long docNumber) throws DoctorNotFoundException {
        Doctor doc=docRep.findByPhoneNumber(docNumber);
        if(doc==null)
            throw new DoctorNotFoundException("The entered contact number is not registered with us");
        DoctorResponse docResp=DoctorToDto.doctorToDto(doc,"The doctor has been successfully removed from our inventory");
        docRep.deleteById(doc.getId());
        return docResp;
    }
}
