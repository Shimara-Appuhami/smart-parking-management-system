package lk.ijse.paymentservice.service;

import lk.ijse.paymentservice.model.Payment;
import lk.ijse.paymentservice.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    // Validate mock card
    public boolean validateCard(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    // Process Payment
    public Payment makePayment(Long userId, String vehicleNumber, String cardNumber, Double amount) {
        if (!validateCard(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number");
        }

        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setVehicleNumber(vehicleNumber);
        payment.setCardNumber(cardNumber);
        payment.setAmount(amount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setStatus("SUCCESS");
        payment.setReceiptId(UUID.randomUUID().toString());

        return paymentRepo.save(payment);
    }

    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepo.findByUserId(userId);
    }

    public Optional<Payment> getReceipt(Long paymentId) {
        return paymentRepo.findById(paymentId);
    }
}

