package Project.Loan_Application.repository;

import Project.Loan_Application.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    List<Payment> findByLoanLoanId(Long loanId);
}