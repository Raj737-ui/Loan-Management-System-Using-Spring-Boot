package Project.Loan_Application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*@AllArgsConstructor
@NoArgsConstructor*/
public class PaymentRequest {
    @NotNull
    @Positive
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
