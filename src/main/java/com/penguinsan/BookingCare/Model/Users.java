package com.penguinsan.BookingCare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int User_Id;
    private String FullName;
    private String Email;
    private String Password;
    private String Phone;
    private String Degree;
    private float Booking_Fee;
    private float Rating;
    private boolean Available;
    private boolean Gender;
    private Date DateOfBirth;
    private Year Experience;


    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public float getBooking_Fee() {
        return Booking_Fee;
    }

    public void setBooking_Fee(float booking_Fee) {
        Booking_Fee = booking_Fee;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }

    public Year getExperience() {
        return Experience;
    }

    public void setExperience(Year experience) {
        Experience = experience;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    @ManyToOne
    @JoinColumn(name = "Role_Id")
    public Roles Role_Id;

    @ManyToOne
    @JoinColumn(name = "Specialization_Id")
    public Specializations Specialization_Id;

    @ManyToOne
    @JoinColumn(name = "Clinic_Id")
    public Clinics Clinic_Id;
}
