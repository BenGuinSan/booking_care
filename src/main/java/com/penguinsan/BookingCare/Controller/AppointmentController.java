package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.DTO.AppointmentRequest;
import com.penguinsan.BookingCare.DTO.UserRequest;
import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Statues;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Service.AppointmentService;
import com.penguinsan.BookingCare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    UserService userService;
    // lay lich hen theo id benh nhan

    @PostMapping("/")
    public ResponseEntity<List<Appointment>> getAppointments(@RequestBody UserRequest request) {
        Integer userId = request.getUserId();
        List<Appointment> appointments = appointmentService.getAppointmentByPatientId(userId);
        return ResponseEntity.ok(appointments);
    }

    //lay tat ca lich kham
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments()
    {
        List<Appointment> appointments = appointmentService.getAllAppointment();
        return ResponseEntity.ok(appointments);
    }

    //them lich hen
    @PostMapping("/add")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentRequest request) {
        System.out.println("Doctor: " + request.getDoctor().getUserId());
        System.out.println("benh nhan" + request.getPatient().getUserId());
        System.out.println("appointmentDate: " + request.getAppointmentDate());
        System.out.println("startTime: " + request.getStartTime());

        // Tìm bác sĩ
        Users doctor = userService.findUserById(request.getDoctor().getUserId());
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        System.out.println("benh nhan" + request.getPatient().getUserId());

        // Tìm bệnh nhân
        Users patient = userService.findUserById(request.getPatient().getUserId());
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }

        // Kiểm tra thông tin ngày và giờ
        //xử lý ngày và giờ trong request
        Long date = request.getAppointmentDate().getTime();
        System.out.println("date: " + date);
        Date appointmentDate = new Date(date);
        Time startTime = request.getStartTime();
        if (appointmentDate == null || startTime == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment date or start time cannot be null");
        }

        // Tạo đối tượng Appointment và thiết lập các thuộc tính
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointment_Date(appointmentDate);
        appointment.setStart_time(startTime);

        // Tạo trạng thái cuộc hẹn (có thể thay đổi theo trạng thái thực tế)
        Statues status = new Statues();
        status.setStatus_Id(1); // Đảm bảo trạng thái với id này tồn tại trong DB
        appointment.setStatus(status);

        // Lưu cuộc hẹn vào cơ sở dữ liệu
        appointmentService.addAppointment(appointment);

        return ResponseEntity.ok("Add appointment success");
    }







}
