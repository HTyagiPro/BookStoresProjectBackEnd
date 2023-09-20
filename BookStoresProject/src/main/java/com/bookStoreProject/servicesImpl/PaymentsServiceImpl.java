package com.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreProject.entity.Payments;
import com.bookStoreProject.repository.PaymentsRepository;
import com.bookStoreProject.services.PaymentsService;

import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    private final PaymentsRepository paymentsRepository;

    // Constructor injection to provide the PaymentsRepository
    @Autowired
    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    // Retrieve a list of all payments
    @Override
    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    // Retrieve a payment by its ID
    @Override
    public Payments getPaymentById(Long paymentId) {
        return paymentsRepository.findById(paymentId).orElse(null);
    }

    // Create a new payment
    @Override
    public Payments createPayment(Payments payment) {
        return paymentsRepository.save(payment);
    }

    // Update an existing payment
    @Override
    public Payments updatePayment(Long paymentId, Payments payment) {
        if (paymentsRepository.existsById(paymentId)) {
            payment.setPaymentID(paymentId);
            return paymentsRepository.save(payment);
        }
        return null;
    }

    // Delete a payment by its ID
    @Override
    public void deletePayment(Long paymentId) {
        paymentsRepository.deleteById(paymentId);
    }
}


















//package com.bookStoreProject.servicesImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Payments;
//import com.bookStoreProject.repository.PaymentsRepository;
//import com.bookStoreProject.services.PaymentsService;
//
//import java.util.List;
//
//@Service
//public class PaymentsServiceImpl implements PaymentsService {
//    private final PaymentsRepository paymentsRepository;
//
//    @Autowired
//    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
//        this.paymentsRepository = paymentsRepository;
//    }
//
//    @Override
//    public List<Payments> getAllPayments() {
//        return paymentsRepository.findAll();
//    }
//
//    @Override
//    public Payments getPaymentById(Long paymentId) {
//        return paymentsRepository.findById(paymentId).orElse(null);
//    }
//
//    @Override
//    public Payments createPayment(Payments payment) {
//        return paymentsRepository.save(payment);
//    }
//
//    @Override
//    public Payments updatePayment(Long paymentId, Payments payment) {
//        if (paymentsRepository.existsById(paymentId)) {
//            payment.setPaymentID(paymentId);
//            return paymentsRepository.save(payment);
//        }
//        return null;
//    }
//
//    @Override
//    public void deletePayment(Long paymentId) {
//        paymentsRepository.deleteById(paymentId);
//    }
//}
//
