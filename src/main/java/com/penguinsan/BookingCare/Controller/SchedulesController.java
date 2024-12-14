package com.penguinsan.BookingCare.Controller;


import com.penguinsan.BookingCare.DTO.SchedulesRequestDTO;
import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Schedules;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Service.SchedulesService;
import com.penguinsan.BookingCare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class SchedulesController {

    @Autowired
    SchedulesService schedulesService;
    @Autowired
    UserService userService;

    // Lấy toàn bộ danh sách lịch khám
    @GetMapping("all")
//    @CrossOrigin(origins = "http://localhost:5173")
    public List<Schedules> getAllSchedules()
    {
        return schedulesService.getAllSchedules();
    }

    // Lấy ra lịch khám theo id doctor
    @GetMapping("/doctor")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<Schedules> findSchedulesByDoctor(@RequestParam int id){
        return schedulesService.findSchedulesByDoctorId(id);
    }

    // Thêm lịch khám
    @PostMapping("/add/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String addSchedule(@RequestBody Schedules schedules, @PathVariable int id) {
        Users doctor = userService.findUserById(id);
        if(doctor == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }
        schedules.setUser(doctor);
        schedulesService.addSchedule(schedules);
        return "Add schedule success";
    }

    //Sửa lịch khám (isbooked)
    @PutMapping("/editIsBooked/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    @ResponseStatus(HttpStatus.OK)
    public String editSchedule(@PathVariable int id){
        Schedules schedules = schedulesService.findScheduleById(id);
        if(schedules == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
        schedules.set_booked(!schedules.is_booked());
        schedulesService.addSchedule(schedules);
        return "Edit schedule isbooked success";
    }

    @GetMapping("get-by-doctor-email/{email}")
    public ResponseEntity<Page<Schedules>> getSchedulesDoctorByEmail(@PathVariable String email, @RequestParam int skip, @RequestParam int size){
        Page<Schedules> schedules = schedulesService.getSchedulesByDoctorEmail(email, skip, size);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<String> addNewSchedule(@RequestBody SchedulesRequestDTO schedulesRequestDTO, @PathVariable int id){
        schedulesService.addNewSchedule(schedulesRequestDTO, id);
        return ResponseEntity.ok("Add new Schedule Suc");
    }

}
