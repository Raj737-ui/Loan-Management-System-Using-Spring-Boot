package Project.Loan_Application.service;

import Project.Loan_Application.dto.CustomerRequest;
import Project.Loan_Application.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request
    );
    void deleteCustomer(Long id);



}
