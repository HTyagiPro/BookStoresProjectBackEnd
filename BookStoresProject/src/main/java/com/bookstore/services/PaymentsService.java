package com.bookstore.services;

import java.util.List;

import com.bookstore.entity.Payments;

public interface PaymentsService {
    List<Payments> getAllPayments();
    Payments getPaymentById(Long paymentId);
    Payments createPayment(Payments payment);
    Payments updatePayment(Long paymentId, Payments payment);
    void deletePayment(Long paymentId);
}

