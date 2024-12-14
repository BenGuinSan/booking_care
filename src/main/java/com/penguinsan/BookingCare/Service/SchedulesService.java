package com.penguinsan.BookingCare.Service;


import com.penguinsan.BookingCare.DTO.SchedulesRequestDTO;
import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Schedules;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.SchedulesRepository;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SchedulesService {
    @Autowired
    SchedulesRepository schedulesRepository;

    @Autowired
    UsersRepository usersRepository;

    // Lấy toàn bộ danh sách lịch khám
    public List<Schedules> getAllSchedules()
    {
        return schedulesRepository.findAll();
    }


    // Lấy ra lịch khám theo user id doctor
    public List<Schedules> findSchedulesByDoctorId(int id){

        return schedulesRepository.getSchedulesByDoctorIdIsBooked(id);
    }

    public void addSchedule(Schedules schedules) {
        schedulesRepository.save(schedules);
    }

    public void addNewSchedule(SchedulesRequestDTO schedulesRequestDTO, int id){
        Users user = usersRepository.findById(id).orElse(null);

        Schedules schedule = new Schedules();
        schedule.setUser(user);
        schedule.set_booked(false);
        schedule.setDuration(0);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate workingDate = LocalDate.parse(schedulesRequestDTO.getWorking_date(), dateFormatter);
        schedule.setWorking_date(workingDate);

        LocalTime startTime = LocalTime.parse(schedulesRequestDTO.getStart_time());
        schedule.setStart_time(startTime);

        LocalTime endTime = LocalTime.parse(schedulesRequestDTO.getEnd_time());
        schedule.setEnd_time(endTime);

        schedulesRepository.save(schedule);
    }

    public Schedules findScheduleById(int id) {
        return schedulesRepository.findById(id).orElse(null);
    }

    public Page<Schedules> getSchedulesByDoctorEmail(String doctorEmail, int skip, int size)
    {
        Pageable pageable = PageRequest.of(skip/size, size);
        return schedulesRepository.findByDoctorEmail(doctorEmail, pageable);
    }
}
