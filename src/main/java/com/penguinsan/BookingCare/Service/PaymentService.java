package com.penguinsan.BookingCare.Service;

import com.penguinsan.BookingCare.Model.Payments;
import com.penguinsan.BookingCare.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    // Get payment by id
    public Payments getPaymentById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }


    // Them payment
    public Payments addPayment(Payments payment) {
        return paymentRepository.save(payment);
    }
}
