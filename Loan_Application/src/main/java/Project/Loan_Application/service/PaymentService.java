package Project.Loan_Application.service;

import Project.Loan_Application.dto.PaymentRequest;
import Project.Loan_Application.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse makePayment(
            Long loanId,
            PaymentRequest request
    );

    List<PaymentResponse> getPaymentsByLoan(
            Long loanId
    );

    PaymentResponse getPaymentById(
            Long paymentId
    );
}
