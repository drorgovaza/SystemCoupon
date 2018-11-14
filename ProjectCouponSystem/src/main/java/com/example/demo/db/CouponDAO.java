package com.example.demo.db;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CompanyNotFoundException;
import com.example.demo.exceptions.CouponExistException;
import com.example.demo.exceptions.CouponNotFoundException;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.exceptions.CustomerNotFoundExceptin;
import com.example.demo.enums.CouponType;

@Service
public interface CouponDAO {

	void createCoupon (Coupon coupon , int compId) throws CouponExistException , CompanyNotFoundException;
	
	void removeCoupon (int coupId , int compId) throws CouponNotFoundException , CompanyNotFoundException;
	
	void updateCoupon (Date endDate ,double price , int coupId ,int compId) throws CompanyNotFoundException, CouponNotFoundException;
	
	List<Coupon> getAllCoupns() throws CouponsNotExistException;
	
	List<Coupon> getCouponsByType (CouponType couponType) throws CouponsNotExistException;

	Customer getCustomer(int id) throws CustomerNotFoundExceptin;
	
}
