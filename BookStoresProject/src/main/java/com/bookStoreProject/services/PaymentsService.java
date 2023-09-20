package com.bookStoreProject.services;

import java.util.List;

import com.bookStoreProject.entity.Payments;

public interface PaymentsService {
    List<Payments> getAllPayments();
    Payments getPaymentById(Long paymentId);
    Payments createPayment(Payments payment);
    Payments updatePayment(Long paymentId, Payments payment);
    void deletePayment(Long paymentId);
}

