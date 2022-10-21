package rikkei.academy.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Customer;
import rikkei.academy.repository.customer.CustomerRepositoryIMPL;
import rikkei.academy.repository.customer.ICustomerRepository;

import java.util.List;

@Service
public class CustomerServiceIMPL implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        return customerRepository.insertWithStoredProcedure(customer);
    }
    @Bean
    public ICustomerRepository customerRepository(){
        return new CustomerRepositoryIMPL();
    }
    @Bean
    public ICustomerService customerService(){
        return new CustomerServiceIMPL();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public void remove(Long id) {
        customerRepository.remove(id);

    }
}
