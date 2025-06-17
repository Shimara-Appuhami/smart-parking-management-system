package lk.ijse.paymentservice.controller;

import lk.ijse.paymentservice.model.Payment;
import lk.ijse.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<Payment> makePayment(@RequestBody Map<String, Object> body) {
        Long userId = Long.valueOf(body.get("userId").toString());
        String vehicleNumber = body.get("vehicleNumber").toString();
        String cardNumber = body.get("cardNumber").toString();
        Double amount = Double.valueOf(body.get("amount").toString());

        Payment payment = paymentService.makePayment(userId, vehicleNumber, cardNumber, amount);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPayments(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }

    @GetMapping("/receipt/{paymentId}")
    public ResponseEntity<Payment> getReceipt(@PathVariable Long paymentId) {
        return paymentService.getReceipt(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

