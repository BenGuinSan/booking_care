package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.DTO.AppointmentRequest;
import com.penguinsan.BookingCare.DTO.UserRequest;
import com.penguinsan.BookingCare.Model.*;
import com.penguinsan.BookingCare.Service.AppointmentService;
import com.penguinsan.BookingCare.Service.PaymentService;
import com.penguinsan.BookingCare.Service.SchedulesService;
import com.penguinsan.BookingCare.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    PaymentService paymentService;
    UserService userService;
    SchedulesService schedulesService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService, PaymentService paymentService, UserService userService, SchedulesService schedulesService) {
        this.appointmentService = appointmentService;
        this.paymentService = paymentService;
        this.userService = userService;
        this.schedulesService = schedulesService;
    }



    @PostMapping("/")
    public ResponseEntity<List<Appointment>> getAppointments(@RequestBody UserRequest request , @RequestParam int page, @RequestParam int size) {
        Integer userId = request.getUserId();
        List<Appointment> appointments = appointmentService.getAppointmentByPatientId(userId , page, size);
        return ResponseEntity.ok(appointments);
    }

    //dem so lich hen theo id benh nhan
    @PostMapping("/count")
    public ResponseEntity<Integer> countAppointments(@RequestBody UserRequest request) {
        int count = appointmentService.countByPatientId(request.getUserId());
        return ResponseEntity.ok(count);
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
        System.out.println("benh nhan: " + request.getPatient().getUserId());
        System.out.println("appointmentDate: " + request.getAppointmentDate());
        System.out.println("startTime: " + request.getStartTime());
        System.out.println("scheduleId: " + request.getSchedule().getSchedule_Id());

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

        Schedules schedules = schedulesService.findScheduleById(request.getSchedule().getSchedule_Id());

        // Kiểm tra thông tin ngày và giờ
        //xử lý ngày và giờ trong request
        Long date = request.getAppointmentDate().getTime();
        System.out.println("date: " + date);
        Date appointmentDate = new Date(date);
        Time startTime = request.getStartTime();
        if (appointmentDate == null || startTime == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment date or start time cannot be null");
        }
        Payments payments = new Payments();

        payments.setAmount(request.getAmout());
        payments.setPayment_Date(null);
        payments.setPayment_method(Payments.Payment_method.CASH);
        paymentService.addPayment(payments);

        // Tạo đối tượng Appointment và thiết lập các thuộc tính
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointment_Date(appointmentDate);
        appointment.setStart_time(startTime);
        appointment.setSchedule(schedules);
        appointment.setPayment(payments);
        // Tạo trạng thái cuộc hẹn (có thể thay đổi theo trạng thái thực tế)
        Statues status = new Statues();
        status.setStatus_Id(1); // Đảm bảo trạng thái với id này tồn tại trong DB
        appointment.setStatus(status);

        // Lưu cuộc hẹn vào cơ sở dữ liệu
        appointment = appointmentService.addAppointment(appointment);








        return ResponseEntity.ok("Add appointment success");
    }

    //huy lịch hẹn
    @PutMapping("/editIsStatus/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    @ResponseStatus(HttpStatus.OK)
    public String editSchedule(@PathVariable int id){
        Appointment appointment = appointmentService.getAppointmentById(id);
        if(appointment == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
//        Schedules schedules = schedulesService.
        Schedules schedules = schedulesService.findScheduleById(appointment.getSchedule().getSchedule_Id());
        schedules.set_booked(false);
        Statues status = new Statues();
        status.setStatus_Id(4);
        appointment.setStatus(status);
        appointment.setSchedule(schedules);
        appointmentService.addAppointment(appointment);
        return "Cancel appointment success";
    }

    // kiem tra benh nhan co dat trung lich khong
    @GetMapping("/checkexistsappointment")
    public ResponseEntity<Integer> checkExistsAppointment(@RequestParam int patientId, @RequestParam String appointmentDate, @RequestParam String startTime) {
        Long longDate = Long.parseLong(appointmentDate);
        Date date = new Date(longDate);
        Time time = Time.valueOf(startTime);
        System.out.println("date: " + date);
        System.out.println("time: " + time);
        System.out.println("patientId: " + patientId);
        int exists = appointmentService.existsByPatientIdAndAppointmentDateAndStartTime(patientId, date, time);
        return ResponseEntity.ok(exists);
    }






}
