package Project.Loan_Application.controller;

import Project.Loan_Application.dto.LoanRequest;
import Project.Loan_Application.dto.LoanResponse;
import Project.Loan_Application.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(
            LoanService loanService) {

        this.loanService = loanService;
    }

    @PostMapping("/apply/{customerId}")
    public ResponseEntity<LoanResponse> applyLoan(
            @PathVariable Long customerId,
            @Valid @RequestBody LoanRequest request) {

        return new ResponseEntity<>(
                loanService.applyLoan(
                        customerId,
                        request
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getLoan(
            @PathVariable Long loanId) {

        return ResponseEntity.ok(
                loanService.getLoanById(loanId)
        );
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>>
    getAllLoans() {

        return ResponseEntity.ok(
                loanService.getAllLoans()
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<LoanResponse>>
    getLoansByCustomer(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                loanService.getLoansByCustomer(
                        customerId
                )
        );
    }

    @PutMapping("/{loanId}/approve")
    public ResponseEntity<LoanResponse> approveLoan(
            @PathVariable Long loanId) {

        return ResponseEntity.ok(
                loanService.approveLoan(loanId)
        );
    }

    @PutMapping("/{loanId}/reject")
    public ResponseEntity<LoanResponse> rejectLoan(
            @PathVariable Long loanId) {

        return ResponseEntity.ok(
                loanService.rejectLoan(loanId)
        );
    }
}
