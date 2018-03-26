package com.couponsystem.DAO;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;
/** Data Access Object for Customer
 * @author DrorGovaza
 *
 */
@Service
public interface CustomerDAO {

	/***
	 * Creating new Customer
	 * @param customer
	 * @throws CustomerExistException
	 */
	void createCustomer(Customer customer)throws CustomerExistException;
	
	/***
	 * Removing Customer by its ID
	 * @param customerId
	 * @throws CustomerNotExistException
	 */
	void removeCustomer(int customerId)throws CustomerNotExistException;
	
	/***
	 * Updating Customer set only password by its ID
	 * @param password
	 * @param customerID
	 * @throws CustomerNotExistException
	 */
	void updateCustomer(String password , int customerID)throws CustomerNotExistException;
	
	/***
	 * Getting Customer by its ID
	 * @param customerId
	 * @return Customer
	 * @throws CustomerNotExistException
	 */
	Customer getCustomerByID(int customerId)throws CustomerNotExistException;
	
	/***
	 * Getting all Customers
	 * @return ArrayList<Customer>
	 * @throws CustomersNotExistException
	 */
	ArrayList<Customer> getAllCustomers()throws CustomersNotExistException;
	
	
	
	/***
	 * Get All Customer's Coupons by its ID
	 * @param customerId
	 * @return
	 * @throws CustomerNotExistException
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getCustomerCoupons(int customerId)throws CustomerNotExistException , CouponsNotExistException;

	/***
	 * Login method for Customer
	 * @param customerName
	 * @param password
	 * @return true/false by login parameters
	 */
	boolean login(String customerName , String password);

}
