package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Payments;
import com.example.bookStoreProject.repository.PaymentsRepository;
import com.example.bookStoreProject.services.PaymentsService;

import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    @Override
    public Payments getPaymentById(Long paymentId) {
        return paymentsRepository.findById(paymentId).orElse(null);
    }

    @Override
    public Payments createPayment(Payments payment) {
        return paymentsRepository.save(payment);
    }

    @Override
    public Payments updatePayment(Long paymentId, Payments payment) {
        if (paymentsRepository.existsById(paymentId)) {
            payment.setPaymentID(paymentId);
            return paymentsRepository.save(payment);
        }
        return null;
    }

    @Override
    public void deletePayment(Long paymentId) {
        paymentsRepository.deleteById(paymentId);
    }
}

