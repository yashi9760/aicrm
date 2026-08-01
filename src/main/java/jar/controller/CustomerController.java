package jar.controller;

import jar.model.Customer;
import jar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // 1. Get All Customers (with optional Search and Status Filter)
    @GetMapping
    public List<Customer> getAllCustomers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status) {

        if (search != null && !search.isEmpty() && status != null && !status.isEmpty()) {
            return customerRepository.searchAndFilter(search, status);
        } else if (search != null && !search.isEmpty()) {
            return customerRepository.searchByNameOrEmail(search);
        } else if (status != null && !status.isEmpty()) {
            return customerRepository.findByStatus(status);
        }

        return customerRepository.findAll();
    }

    // 2. Get Single Customer Details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Add Customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // 4. Update Customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customerDetails.getName());
                    existingCustomer.setEmail(customerDetails.getEmail());
                    existingCustomer.setPhone(customerDetails.getPhone());
                    existingCustomer.setCompany(customerDetails.getCompany());
                    existingCustomer.setStatus(customerDetails.getStatus());
                    existingCustomer.setNotes(customerDetails.getNotes());
                    existingCustomer.setFollowUpDate(customerDetails.getFollowUpDate());
                    Customer updatedCustomer = customerRepository.save(existingCustomer);
                    return ResponseEntity.ok(updatedCustomer);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Delete Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}