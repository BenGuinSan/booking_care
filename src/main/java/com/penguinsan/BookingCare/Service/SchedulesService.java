package com.penguinsan.BookingCare.Service;


import com.penguinsan.BookingCare.Model.Schedules;
import com.penguinsan.BookingCare.Repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
