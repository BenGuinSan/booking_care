package com.penguinsan.BookingCare.Mapper;

import com.penguinsan.BookingCare.DTO.DoctorDTO;
import com.penguinsan.BookingCare.DTO.PatientDTO;
import com.penguinsan.BookingCare.DTO.UserDTO;
import com.penguinsan.BookingCare.Model.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toUserDTO(Users user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    public DoctorDTO toDoctorDTO(Users user){
        DoctorDTO doctorDTO = modelMapper.map(user, DoctorDTO.class);
        return doctorDTO;
    }

    public PatientDTO toPatientDTO(Users user){
        PatientDTO patientDTO = modelMapper.map(user, PatientDTO.class);
        return patientDTO;
    }


}
