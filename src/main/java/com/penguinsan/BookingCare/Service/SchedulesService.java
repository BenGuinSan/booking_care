package com.penguinsan.BookingCare.Service;


import com.penguinsan.BookingCare.Model.Schedules;
import com.penguinsan.BookingCare.Repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SchedulesService {
    @Autowired
    SchedulesRepository schedulesRepository;

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

    public Schedules findScheduleById(int id) {
        return schedulesRepository.findById(id).orElse(null);
    }
}
