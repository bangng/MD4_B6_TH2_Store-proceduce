package rikkei.academy.repository.customer;

import rikkei.academy.model.Customer;
import rikkei.academy.repository.IGenericRepository;
import rikkei.academy.service.IGenericService;

public interface ICustomerRepository extends IGenericRepository<Customer> {
    boolean insertWithStoredProcedure(Customer customer);

}
