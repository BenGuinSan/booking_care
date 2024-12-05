package com.penguinsan.BookingCare.Repository.Impl;

import com.penguinsan.BookingCare.Model.Users;
import com.penguinsan.BookingCare.Repository.CustomUserRepository;
import com.penguinsan.BookingCare.Repository.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Users> findAllDoctor(){
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.role.id = 2", Users.class);
        return query.getResultList();
    }

    public List<Users> findAllPatient(){
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u WHERE u.role.id = 3", Users.class);
        return query.getResultList();
    }

    //lấy thông tin của bác sĩ trên 5 năm kinh nghiệm
    public List<Users> findDoctorExperience(){
        TypedQuery<Users> query = entityManager.createQuery("SELECT * FROM USERS u WHERE u.Experience > 5", Users.class);
        return query.getResultList();
    }
}
