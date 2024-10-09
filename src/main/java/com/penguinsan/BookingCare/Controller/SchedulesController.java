package com.penguinsan.BookingCare.Controller;


import com.penguinsan.BookingCare.Model.Schedules;
import com.penguinsan.BookingCare.Service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class SchedulesController {

    @Autowired
    SchedulesService schedulesService;


    // Lấy toàn bộ danh sách lịch khám
    @GetMapping("/all")
    public List<Schedules> getAllSchedules()
    {
        return schedulesService.getAllSchedules();
    }
}
