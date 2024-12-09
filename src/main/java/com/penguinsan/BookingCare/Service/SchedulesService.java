package com.penguinsan.BookingCare.Service;


import com.penguinsan.BookingCare.Model.Schedules;
import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.SchedulesRepository;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public Schedules findScheduleById(int id) {
        return schedulesRepository.findById(id).orElse(null);
    }

//    public void generateSchedules(int doctorId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int duration) {
//        Users doctor = usersRepository.findById(doctorId).orElse(null);
//
//        for (LocalDate workingDate = startDate; workingDate.isBefore(endDate.plusDays(1)); workingDate = workingDate.plusDays(1)) {
//            for (LocalTime potentialStartTime = startTime; potentialStartTime.isBefore(endTime); potentialStartTime = potentialStartTime.plusMinutes(duration)) {
//                final LocalTime currentTime = potentialStartTime;
//                // Check for overlapping schedules
//                List<Schedules> existingSchedules = schedulesRepository.findByDoctorIdAndWorkingDate(doctorId, workingDate);
//                boolean isOverlapping = existingSchedules.stream().anyMatch(schedule ->
//                        (currentTime.isBefore(schedule.getEnd_time()) && currentTime.isAfter(schedule.getStart_time())) ||
//                                currentTime.equals(schedule.getStart_time()) || currentTime.equals(schedule.getEnd_time()));
//
//                if (!isOverlapping) {
//                    Schedules newSchedule = new Schedules();
//                    newSchedule.setUser(doctor);
//                    newSchedule.setWorking_date(workingDate);
//                    newSchedule.setStart_time(currentTime);
//                    newSchedule.setEnd_time(currentTime.plusMinutes(duration));
//                    newSchedule.set_booked(false);
//                    newSchedule.setDuration(duration); // Assuming duration is constant for this case
//
//                    schedulesRepository.save(newSchedule);
//                }
//            }
//        }
//    }
}
