package com.example.demo.db;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.exceptions.CustomerExistException;
import com.example.demo.exceptions.CustomerNotFoundExceptin;
import com.example.demo.exceptions.CustomersNotExistException;

@Service
public interface CustomerDAO {

	void createCustomer(Customer customer) throws CustomerExistException;

	void removeCustomer(int id)throws CustomerNotFoundExceptin;
	
	void updateCustomer(String password , int id) throws CustomerNotFoundExceptin;
	
	Customer getCustomer(int id) throws CustomerNotFoundExceptin;
	
	List<Customer> getAllCustomers () throws CustomersNotExistException;
	
	List<Coupon> getAllCoupons (int custId) throws 	CouponsNotExistException; 
	
	boolean login(String custName , String password);

}
