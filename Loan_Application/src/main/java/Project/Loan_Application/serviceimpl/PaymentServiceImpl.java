package Project.Loan_Application.serviceimpl;

import Project.Loan_Application.dto.PaymentRequest;
import Project.Loan_Application.dto.PaymentResponse;
import Project.Loan_Application.entity.Loan;
import Project.Loan_Application.entity.Payment;
import Project.Loan_Application.exception.ResourceNotFoundException;
import Project.Loan_Application.repository.LoanRepository;
import Project.Loan_Application.repository.PaymentRepository;
import Project.Loan_Application.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final LoanRepository loanRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            LoanRepository loanRepository) {

        this.paymentRepository = paymentRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public PaymentResponse makePayment(
            Long loanId,
            PaymentRequest request) {

        Loan loan =
                loanRepository.findById(loanId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan not found with ID: "
                                                + loanId
                                ));

        if (!"APPROVED".equals(
                loan.getStatus())) {

            throw new IllegalStateException(
                    "Payment can only be made for approved loans"
            );
        }

        Payment payment = new Payment();

        payment.setAmount(
                request.getAmount()
        );

        payment.setPaymentDate(
                LocalDate.now()
        );

        payment.setPaymentStatus(
                "SUCCESS"
        );

        payment.setLoan(loan);

        Payment savedPayment =
                paymentRepository.save(payment);

        return convertToResponse(savedPayment);
    }

    @Override
    public List<PaymentResponse> getPaymentsByLoan(
            Long loanId) {

        if (!loanRepository.existsById(loanId)) {

            throw new ResourceNotFoundException(
                    "Loan not found with ID: "
                            + loanId
            );
        }

        return paymentRepository
                .findByLoanLoanId(loanId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentById(
            Long paymentId) {

        Payment payment =
                paymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found with ID: "
                                                + paymentId
                                ));

        return convertToResponse(payment);
    }

    private PaymentResponse convertToResponse(
            Payment payment) {

        PaymentResponse response =
                new PaymentResponse();

        response.setPaymentId(
                payment.getPaymentId()
        );

        response.setLoanId(
                payment.getLoan()
                        .getLoanId()
        );

        response.setAmount(
                payment.getAmount()
        );

        response.setPaymentDate(
                payment.getPaymentDate()
        );

        response.setPaymentStatus(
                payment.getPaymentStatus()
        );

        return response;
    }
}
