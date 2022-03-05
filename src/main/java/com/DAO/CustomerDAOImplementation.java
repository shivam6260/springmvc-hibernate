package com.DAO;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Entity.Customer;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public List<Customer> getCustomers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		return customers;
		
	}


	public void saveCustomer(Customer theCustomer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCustomer);
		
	}


	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}
	
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		currentSession.delete(theCustomer);
	}
	
}
