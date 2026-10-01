package Project.Loan_Application.service;

import Project.Loan_Application.dto.LoanRequest;
import Project.Loan_Application.dto.LoanResponse;

import java.util.List;

public interface LoanService {
    LoanResponse applyLoan(
            Long customerId,
            LoanRequest request
    );

    LoanResponse getLoanById(Long loanId);

    List<LoanResponse> getAllLoans();

    List<LoanResponse> getLoansByCustomer(
            Long customerId
    );

    LoanResponse approveLoan(Long loanId);

    LoanResponse rejectLoan(Long loanId);
}
