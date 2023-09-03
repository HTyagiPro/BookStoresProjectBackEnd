package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.Payments;

public interface PaymentsService {
    List<Payments> getAllPayments();
    Payments getPaymentById(Long paymentId);
    Payments createPayment(Payments payment);
    Payments updatePayment(Long paymentId, Payments payment);
    void deletePayment(Long paymentId);
}

