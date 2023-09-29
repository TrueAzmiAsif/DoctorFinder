package com.example.DoctorFinder.Controller;

import com.example.DoctorFinder.DTO.RequestDTO.PatientRequest;
import com.example.DoctorFinder.DTO.ResponseDTO.DoctorResponse;
import com.example.DoctorFinder.DTO.ResponseDTO.PatientResponse;
import com.example.DoctorFinder.Exceptions.*;
import com.example.DoctorFinder.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patServ;
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody PatientRequest patReq){
        PatientResponse patResp;
        try{
            patResp=patServ.add(patReq);
        } catch (InvalidNameException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidPhoneNumberException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidEmailException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidSymptomException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidCityException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(patResp, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity remove(@RequestParam("number") Long patNumber){
        PatientResponse patResp=null;
        try{
            patResp=patServ.remove(patNumber);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(patResp,HttpStatus.ACCEPTED);
    }
}
