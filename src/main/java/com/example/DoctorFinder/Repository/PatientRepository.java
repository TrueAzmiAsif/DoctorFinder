package com.example.DoctorFinder.Repository;

import com.example.DoctorFinder.Exceptions.PatientNotFoundException;
import com.example.DoctorFinder.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    public Patient findByPhoneNumber(long phoneNumber) throws PatientNotFoundException;
}
