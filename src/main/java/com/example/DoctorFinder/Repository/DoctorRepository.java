package com.example.DoctorFinder.Repository;

import com.example.DoctorFinder.Enum.City;
import com.example.DoctorFinder.Enum.Speciality;
import com.example.DoctorFinder.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    public List<Doctor> findByCityAndSpeciality(City city, Speciality speciality);
    public Doctor findByPhoneNumber(long phoneNumber);
}
