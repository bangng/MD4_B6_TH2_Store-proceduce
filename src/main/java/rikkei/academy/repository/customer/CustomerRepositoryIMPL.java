package rikkei.academy.repository.customer;

import org.springframework.transaction.annotation.Transactional;
import rikkei.academy.model.Customer;

import javax.persistence.*;
import java.util.List;

@Transactional
public class CustomerRepositoryIMPL implements ICustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        String sql = "CALL Insert_Customer(:firstName, :lastName)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("firstName", customer.getFirstName());
        query.setParameter("lastName", customer.getLastName());
        System.out.println(query);
        return query.executeUpdate() == 0;
    }


    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT C FROM Customer C WHERE C.id = :id", Customer.class);
        query.setParameter("id", id);
        try {
            return   query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }


    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() == null){
            entityManager.persist(customer);
        }else {
            entityManager.merge(customer);
        }

    }

    @Override
    public void remove(Long id) {
        Customer customer = findById(id);
        if (customer != null){
            entityManager.remove(customer);
        }

    }
}
