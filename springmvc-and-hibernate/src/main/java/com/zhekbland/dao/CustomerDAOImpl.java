package com.zhekbland.dao;

import com.zhekbland.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session session = this.sessionFactory.getCurrentSession();

        // create query
        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get result list
        List<Customer> customers = query.getResultList();

        //return the result
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }


    public List<Customer> getCustomerAdvance() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Customer", Customer.class).list();
    }
}