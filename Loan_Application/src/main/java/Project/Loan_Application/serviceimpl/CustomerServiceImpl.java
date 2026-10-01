package Project.Loan_Application.serviceimpl;

import Project.Loan_Application.dto.CustomerRequest;
import Project.Loan_Application.dto.CustomerResponse;
import Project.Loan_Application.entity.Customer;
import Project.Loan_Application.exception.ResourceNotFoundException;
import Project.Loan_Application.repository.CustomerRepository;
import Project.Loan_Application.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(
            CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setIncome(request.getIncome());

        Customer savedCustomer =
                customerRepository.save(customer);

        return convertToResponse(savedCustomer);
    }
    @Override
    public CustomerResponse getCustomerById(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found with ID: " + id
                                ));

        return convertToResponse(customer);
    }
    @Override
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
    @Override
    public CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found with ID: " + id
                                ));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setIncome(request.getIncome());

        Customer updatedCustomer =
                customerRepository.save(customer);

        return convertToResponse(updatedCustomer);
    }
    @Override
    public void deleteCustomer(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found with ID: " + id
                                ));

        customerRepository.delete(customer);
    }
    private CustomerResponse convertToResponse(
            Customer customer) {

        CustomerResponse response =
                new CustomerResponse();

        response.setCustomerId(
                customer.getCustomerId()
        );

        response.setName(customer.getName());

        response.setEmail(customer.getEmail());

        response.setPhone(customer.getPhone());

        response.setAddress(customer.getAddress());

        response.setIncome(customer.getIncome());

        return response;
    }

}
