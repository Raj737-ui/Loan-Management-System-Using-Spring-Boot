package Project.Loan_Application.serviceimpl;


import Project.Loan_Application.dto.LoanRequest;
import Project.Loan_Application.dto.LoanResponse;
import Project.Loan_Application.entity.Customer;
import Project.Loan_Application.entity.Loan;
import Project.Loan_Application.exception.ResourceNotFoundException;
import Project.Loan_Application.repository.CustomerRepository;
import Project.Loan_Application.repository.LoanRepository;
import Project.Loan_Application.service.LoanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    private final CustomerRepository customerRepository;

    public LoanServiceImpl(
            LoanRepository loanRepository,
            CustomerRepository customerRepository) {

        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public LoanResponse applyLoan(
            Long customerId,
            LoanRequest request) {

        Customer customer =
                customerRepository.findById(customerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found with ID: "
                                                + customerId
                                ));

        Loan loan = new Loan();

        loan.setAmount(request.getAmount());

        loan.setInterestRate(
                request.getInterestRate()
        );

        loan.setTenure(
                request.getTenure()
        );

        loan.setLoanType(
                request.getLoanType()
        );

        loan.setStatus("PENDING");

        loan.setApplicationDate(
                LocalDate.now()
        );

        loan.setCustomer(customer);

        Loan savedLoan =
                loanRepository.save(loan);

        return convertToResponse(savedLoan);
    }

    @Override
    public LoanResponse getLoanById(
            Long loanId) {

        Loan loan =
                loanRepository.findById(loanId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Loan not found with ID: "
                                                + loanId
                                ));

        return convertToResponse(loan);
    }

    @Override
    public List<LoanResponse> getAllLoans() {

        return loanRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
    @Override
    public List<LoanResponse> getLoansByCustomer(
            Long customerId) {

        if (!customerRepository.existsById(customerId)) {

            throw new ResourceNotFoundException(
                    "Customer not found with ID: "
                            + customerId
            );
        }

        return loanRepository
                .findByCustomerCustomerId(customerId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
    @Override
    public LoanResponse approveLoan(
            Long loanId) {

        Loan loan = getLoanEntity(loanId);

        loan.setStatus("APPROVED");

        Loan updatedLoan =
                loanRepository.save(loan);

        return convertToResponse(updatedLoan);
    }

    @Override
    public LoanResponse rejectLoan(
            Long loanId) {

        Loan loan = getLoanEntity(loanId);

        loan.setStatus("REJECTED");

        Loan updatedLoan =
                loanRepository.save(loan);

        return convertToResponse(updatedLoan);
    }

    private Loan getLoanEntity(Long loanId) {

        return loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Loan not found with ID: "
                                        + loanId
                        ));
    }
    private LoanResponse convertToResponse(
            Loan loan) {

        LoanResponse response =
                new LoanResponse();

        response.setLoanId(
                loan.getLoanId()
        );

        response.setCustomerId(
                loan.getCustomer()
                        .getCustomerId()
        );

        response.setAmount(
                loan.getAmount()
        );

        response.setInterestRate(
                loan.getInterestRate()
        );

        response.setTenure(
                loan.getTenure()
        );

        response.setLoanType(
                loan.getLoanType()
        );

        response.setStatus(
                loan.getStatus()
        );

        response.setApplicationDate(
                loan.getApplicationDate()
        );

        return response;
    }

}
