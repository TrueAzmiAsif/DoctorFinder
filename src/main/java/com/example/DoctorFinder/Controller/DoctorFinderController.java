package com.example.DoctorFinder.Controller;

import com.example.DoctorFinder.DTO.ResponseDTO.DoctorSuggestionResponse;
import com.example.DoctorFinder.Exceptions.InvalidCityException;
import com.example.DoctorFinder.Exceptions.PatientNotFoundException;
import com.example.DoctorFinder.Models.Patient;
import com.example.DoctorFinder.Service.DoctorService;
import com.example.DoctorFinder.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctorfinder")
public class DoctorFinderController {
    @Autowired
    PatientService patServ;
    @Autowired
    DoctorService docServ;
    @GetMapping("/find")
    public ResponseEntity find(@RequestParam("id") int patientId){
        List<DoctorSuggestionResponse> suggestions=new ArrayList<>();
        Patient pat=null;
        try{
            pat=patServ.find(patientId);
            suggestions=docServ.find(pat.getCity(),pat.getSymptom());
        } catch (PatientNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidCityException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(suggestions, HttpStatus.FOUND);
    }
}
