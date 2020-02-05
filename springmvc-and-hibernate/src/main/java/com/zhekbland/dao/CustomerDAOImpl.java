package com.zhekbland.dao;

import com.zhekbland.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Customer> getCustomer() {
        // get the current hibernate session
        Session session = this.sessionFactory.getCurrentSession();

        // create query
        Query<Customer> query = session.createQuery("from Customer", Customer.class);

        // execute query and get result list
        List<Customer> customers = query.getResultList();

        //return the result
        return customers;
    }

    @Transactional
    public List<Customer> getCustomerAdvance() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Customer", Customer.class).list();
    }
}