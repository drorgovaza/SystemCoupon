/**
 * 
 */
package com.example.demo.dbdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.db.CustomerDAO;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.exceptions.CustomerExistException;
import com.example.demo.exceptions.CustomerNotFoundExceptin;
import com.example.demo.exceptions.CustomersNotExistException;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.enums.CouponType;

@Service
public class CustomerDBDAO implements CustomerDAO {
	
	CustomerRepo customerRepo;

	/**
	 * CRT
	 */
	public CustomerDBDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCustomer(Customer customer) throws CustomerExistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCustomer(int id) throws CustomerNotFoundExceptin {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(String password, int id) throws CustomerNotFoundExceptin {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomer(int id) throws CustomerNotFoundExceptin {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomersNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getAllCoupons(int custId) throws CouponsNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String custName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public Customer getCustomerByName(String custName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer getCustomerByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public Coupon getCustomerCoupon(int couponId, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void purchaseCoupon(int id, int couponId) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Coupon> getCouponsByPrice(int id, double price) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Coupon> getCustomerCoupons(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Coupon> getCouponsByType(int id, CouponType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
