package com.example.demo.Facades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbdao.CouponDBDAO;
import com.example.demo.dbdao.CustomerDBDAO;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CouponExistException;
import com.example.demo.exceptions.CouponNotExistException;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.exceptions.CustomerExistException;
import com.example.demo.exceptions.CustomersNotExistException;
import com.example.demo.exceptions.ExpiredDateException;
import com.example.demo.exceptions.IllegalAmountException;
import com.example.demo.exceptions.OutOfStockException;
import com.example.demo.enums.CouponType;


@Component
public class CustomerFacade implements CouponClientFacade {
	
		// Object's members
		
		private Customer loggedIn;
		public Customer getLoggedIn() {
			return loggedIn;
		}

		@Autowired
		private CustomerDBDAO custDb;
		@Autowired
		private CouponDBDAO coupDb;

		/***
		 * Login method for Customer
		 */
		@Override
		public CustomerFacade login(String name, String password, ClientType type) {
			// Checking type first
			if (!type.equals(ClientType.CUSTOMER)) {
				return null;
			}
			// Checking name and password

			if (!custDb.login(name, password)) {
				return null;
			}

			// Success - providing facade
			loggedIn = custDb.getCustomerByNameAndPassword(name, password);
			return this;

		}

		/***
		 * Purchasing Coupon
		 * 
		 * @param couponId
		 * @throws CouponNotFoundException
		 * @throws IllegalAmountException
		 * @throws ExpiredDateException
		 * @throws CustomerNotFoundException
		 * @throws CouponExistException
		 * @throws OutOfStockException 
		 */
		public synchronized void purchaseCouopn(int couponId) throws CouponNotExistException,
				ExpiredDateException, CustomersNotExistException, CouponExistException, OutOfStockException {
			// Checking Customer
			if (loggedIn == null) {
				throw new CouponNotExistException("Customer does not login please relogin");
			}

			Coupon couponCheck = coupDb.getCoupon(couponId);

			// Checking if exist
			if (couponCheck == null) {
				throw new CouponNotExistException("Coupon with the ID:" + couponId + " does not exist");
			}

			// Checking if Customer holds it
			Coupon customerCoupon = custDb.getCustomerCoupon(couponId, loggedIn.getId());
			if (customerCoupon != null) {
				throw new CouponExistException("Customer already holds this Coupon.");
			}

			// Checking amount
			if (couponCheck.getAmount() <= 0) {
				throw new OutOfStockException("Cant purchase Coupon - Amount lower then 0");
			}
			// Checking expired date

			if (couponCheck.getEndDate().before(new Date(System.currentTimeMillis()))) {
				throw new ExpiredDateException("Coupon's end date expired");
			}

			// Success - purchasing Coupon
			custDb.purchaseCoupon(loggedIn.getId(), couponId);
		}

		/**
		 * Getting a single Coupon that Customer holds
		 * @param couponId
		 * @return Coupon
		 * @throws CustomerNotFoundException
		 * @throws CouponNotFoundException
		 */
		public Coupon getPurchasedCouopn(int couponId) throws CustomersNotExistException, CouponNotExistException {

			// Checking Customer
			if (loggedIn == null) {
				throw new CustomersNotExistException("Customer does not login please relogin");
			}
			
			// Checking Coupon
			Coupon coupon = custDb.getCustomerCoupon(couponId, loggedIn.getId());
			if(coupon == null) {
				throw new CouponNotExistException("There is no purchased Coupon with the ID:" + couponId);
			}
			// Success - providing Coupon
			return coupon;

		}

		/***
		 * Get All purchased Coupons
		 * 
		 * @return ArrayList<Coupon>
		 * @throws CouponsNotExistException 
		 * @throws CustomerNotFoundException
		 * @throws CouponsNotFoundException
		 */
		public ArrayList<Coupon> getAllPurchasedCoupons() throws CustomersNotExistException, CouponNotExistException, CouponsNotExistException {

			// Checking Customer
			if (loggedIn == null) {
				throw new CustomersNotExistException("Customer does not login please login");
			}

			ArrayList<Coupon> customerCoupons = custDb.getCustomerCoupons(loggedIn.getId());
			// Checking array list
			if (customerCoupons == null) {
				throw new CustomersNotExistException("Coupons does not exist yet");
			}
			// Success providing Coupons
			return customerCoupons;
		}

		/***
		 * Get Coupons by type
		 * 
		 * @param type
		 * @return ArrayList<Coupon>
		 * @throws CustomerNotFoundException
		 * @throws CouponsNotFoundException
		 */

//
//		public ArrayList<Coupon> getCouponsByType(String type)
//				throws CustomersNotExistException, CouponNotExistException {
//			// Checking Customer
//			if (loggedIn == null) {
//				throw new CustomersNotExistException("Customer does not login please relogin");
//			}
//		}
//
//			ArrayList<Coupon> customerCoupons = custDb.getCouponsByType(loggedIn.getId(), type);
//			// Checking array list
//			if (customerCoupons == null) {
//				throw new CouponNotExistException("Coupons does not exist yet");
//			}
//			// Success providing Coupons
//			return customerCoupons;
//		}

		/***
		 * Get Coupons by price
		 * 
		 * @param type
		 * @return ArrayList<Coupon>
		 * @throws CouponsNotExistException 
		 * @throws CustomerNotFoundException
		 * @throws CouponsNotFoundException
		 */
		public ArrayList<Coupon> getCouponsByPrice(int customerId ,double price)
				throws CustomersNotExistException, CouponNotExistException, CouponsNotExistException {
			// Checking Customer
			if (loggedIn == null) {
				throw new CustomersNotExistException("Customer does not login please relogin");
			}

			ArrayList<Coupon> customerCoupons = custDb.getCouponsByPrice(customerId, price);
			// Checking array list
			if (customerCoupons == null) {
				throw new CouponNotExistException("Coupons does not exist yet");
			}
			// Success providing Coupons
			return customerCoupons;
		}

		@Override
		public CouponClientFacade purchaseCoupon(String string, Coupon coupon) {
			// TODO Auto-generated method stub
			return null;
		}

		public List<Coupon> getCouponsByPrice(double price) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CouponClientFacade purchaseCoupon(int CouponId, int CompanyID) {
			// TODO Auto-generated method stub
			return null;
		}
	}
