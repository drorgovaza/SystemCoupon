
package com.couponsystem.DBDAO;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.DAO.CustomerDAO;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;
import com.couponsystem.repo.CouponRepo;
import com.couponsystem.repo.CustomerRepo;

/** Data Bas Data Access Object for Customer
 * @author DrorGovaza
 *
 */
@Service
public class CustomerDBDAO implements CustomerDAO {

	// Object's members
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;

	/***
	 * Creating new Customer
	 * @param customer
	 * @throws CustomerExistException
	 */
	@Override
	public void createCustomer(Customer customer) throws CustomerExistException {
		customerRepo.save(customer);
		
	}

	/***
	 * Removing Customer by its ID
	 */
	@Override
	public void removeCustomer(int customerId) throws CustomerNotExistException {
		customerRepo.deleteById(customerId);
	}

	/***
	 * Updating Customer set only password by its ID
	 */
	@Override
	public void updateCustomer(String password, int customerID) throws CustomerNotExistException {
		customerRepo.updateCustomer(password, customerID);
	}

	/***
	 * Get Customer by its ID
	 */
	@Override
	public Customer getCustomerByID(int customerId) throws CustomerNotExistException {
		return customerRepo.findByid(customerId);
	}
	
	/**
	 * Get Customer by Name
	 * @param custName
	 * @return Customer
	 */
	public Customer getCustomerByName(String custName)
	{
		return customerRepo.findBycustName(custName);
	}
	
	/***
	 * Get Customer by name & password
	 * @param custName
	 * @param password
	 * @return Customer
	 */
	public Customer getCustomerByNameAndPassword(String custName , String password)
	{
		return customerRepo.findBycustNameAndPassword(custName, password);
	}

	/***
	 * Get all Customers
	 */
	@Override
	public ArrayList<Customer> getAllCustomers() throws CustomersNotExistException {
		return (ArrayList<Customer>) customerRepo.findAll();
	}

	/***
	 * Purchase Coupon for Customer
	 * @param couponId
	 * @param customerId
	 * @throws CouponNotExistException
	 * @throws CustomerNotExistException
	 */
	public void purchaseCoupon(int couponId , int customerId)throws CouponNotExistException , CustomerNotExistException
	{
		customerRepo.insertCustomerCoupon(customerId, couponId);
		customerRepo.updateCouponAmount(couponId);
	}
	
	/***
	 * Get Customer's Coupon by title
	 * @param title
	 * @param customerId
	 * @return Coupon
	 */
	public Coupon getCustomerCouponByTitle(String title , int customerId)
	{
		return couponRepo.findBytitleAndCustomersId(title, customerId);
	}
	
	public ArrayList<Coupon> getCouponsByPrice(int customerId , double price)throws CustomerNotExistException , CouponsNotExistException
	{
		return couponRepo.getCustomerCouponsByPrice(price, customerId);
	}
	/***
	 * Get Customer Coupon
	 * @param couponId
	 * @param customerId
	 * @return Coupon
	 * @throws CouponNotExistException
	 * @throws CustomerNotExistException
	 */
	public Coupon getCustomerCoupon(int couponId , int customerId)throws CouponNotExistException , CustomerNotExistException
	{
		return couponRepo.findByidAndCustomersId(couponId, customerId);
	}
	/***
	 * Get All Customer's Coupons
	 */
	@Override
	public ArrayList<Coupon> getCustomerCoupons(int customerId)
			throws CustomerNotExistException, CouponsNotExistException {
		return couponRepo.findByCustomersId(customerId);
	}

	/***
	 * Login method for Customer
	 * @param customerName
	 * @param password
	 * @return true/ false
	 */
	@Override
	public boolean login(String customerName, String password) {
		Customer check = customerRepo.findBycustNameAndPassword(customerName, password);
		// Checking if exist
		if(check == null) {
			return false;
		}
			return true;
	}

	public ArrayList<Coupon> getCouponsByType(int id, CouponType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
