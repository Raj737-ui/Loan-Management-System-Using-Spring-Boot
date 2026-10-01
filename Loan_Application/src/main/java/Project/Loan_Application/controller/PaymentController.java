package Project.Loan_Application.controller;


import Project.Loan_Application.dto.PaymentRequest;
import Project.Loan_Application.dto.PaymentResponse;
import Project.Loan_Application.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping("/loan/{loanId}")
    public ResponseEntity<PaymentResponse> makePayment(
            @PathVariable Long loanId,
            @Valid @RequestBody PaymentRequest request) {

        return new ResponseEntity<>(
                paymentService.makePayment(
                        loanId,
                        request
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<PaymentResponse>>
    getPaymentsByLoan(
            @PathVariable Long loanId) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByLoan(
                        loanId
                )
        );
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse>
    getPaymentById(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(
                paymentService.getPaymentById(
                        paymentId
                )
        );
    }
}
