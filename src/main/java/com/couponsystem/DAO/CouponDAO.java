package com.couponsystem.DAO;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;

/** Data Access Object for Coupon
 * @author DrorGovaza
 *
 */
@Service
public interface CouponDAO {
	
	/***
	 * Creating new Coupon by its Company's ID
	 * @param coupon
	 * @param companyId
	 * @throws CouponExistException
	 * @throws CompanyNotExistException
	 */
	void createCoupon(Coupon coupon , int companyId)throws CouponExistException , CompanyNotExistException;

	/***
	 * Removing Coupon by its ID & Its Company's ID
	 * @param couponId
	 * @param companyId
	 * @throws CouponNotExistException
	 * @throws CompanyNotExistException
	 */
	void removeCoupon(int couponId , int companyId)throws CouponNotExistException , CompanyNotExistException;
	
	/***
	 * Updating Coupon set only endDate & price by its ID & Company's ID
	 * @param endDate
	 * @param price
	 * @param couponId
	 * @param companyId
	 * @throws CouponNotExistException
	 * @throws CompanyNotExistException
	 */
	void updateCoupon(Date endDate , double price , int couponId , int companyId)throws CouponNotExistException , CompanyNotExistException;
	
	/***
	 *Get Coupon by its ID 
	 * @param couponId
	 * @return Coupon
	 * @throws CouponNotExistException
	 */
	Coupon getCoupon(int couponId)throws CouponNotExistException;
	
	/***
	 * Get All Coupons
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getAllCoupons()throws CouponsNotExistException;
	
	/***
	 * Get Coupons by CouponType - type
	 * @param type
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getAllCouponsByType(CouponType type)throws CouponsNotExistException;
	/***
	 * After purchase Coupon the Amount update...
	 * @param amount
	 * @return new amount
	 * @throws CouponNotExistException
	 */
	Coupon amountCoupon (int amount)throws CouponNotExistException;
}
