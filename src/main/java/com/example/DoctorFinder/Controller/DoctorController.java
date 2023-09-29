package com.example.DoctorFinder.Controller;

import com.example.DoctorFinder.DTO.RequestDTO.DoctorRequest;
import com.example.DoctorFinder.DTO.ResponseDTO.DoctorResponse;
import com.example.DoctorFinder.Exceptions.*;
import com.example.DoctorFinder.Models.Doctor;
import com.example.DoctorFinder.Service.Implementation.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorServiceImpl docServ;
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody DoctorRequest docReq){
        DoctorResponse docResp=null;
        try{
            docResp=docServ.add(docReq);
        } catch (InvalidNameException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidPhoneNumberException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (InvalidCityException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (InvalidEmailException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (InvalidSpecialityException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(docResp, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity remove(@RequestParam("number") Long docNumber){
        DoctorResponse docResp=null;
        try{
            docResp=docServ.remove(docNumber);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(docResp,HttpStatus.ACCEPTED);
    }
}
