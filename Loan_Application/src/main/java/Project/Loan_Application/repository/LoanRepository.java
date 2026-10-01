package Project.Loan_Application.repository;

import Project.Loan_Application.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository  extends JpaRepository<Loan,Long> {
    List<Loan> findByCustomerCustomerId(Long customerID);

    List<Loan> findByStatus(String status);

}
