
package com.couponsystem.DBDAO;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.DAO.CouponDAO;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.repo.CompanyRepo;
import com.couponsystem.repo.CouponRepo;

/** Data Bas Data Access Object for Coupon
 * @author DrorGovaza
 *
 */
@Service
public class CouponDBDAO implements CouponDAO {
	
	// Object's members
	@Autowired
	private CouponRepo couponRepo;
	@Autowired
	private CompanyRepo companyRepo;

	/**
	 * Creating new Coupon by Company's ID
	 * @param coupon
	 * @param companyId
	 * @throws CouponExistException
	 * @throws CompanyNotExistException
	 */
	@Override
	public void createCoupon(Coupon coupon, int companyId) throws CouponExistException, CompanyNotExistException {
		// Getting the Company
		Company father = companyRepo.findByid(companyId);
		coupon.setCompany(father);
		// Creating the Coupon
		couponRepo.save(coupon);
	}

	/***
	 * Removing Coupon by ID & Company's ID
	 */
	@Override
	public void removeCoupon(int couponId, int companyId) throws CouponNotExistException, CompanyNotExistException {
		couponRepo.removeCoupon(couponId, companyId);
	}

	/***
	 * Update Coupon set only endDate & price by ID & Company's ID
	 */
	@Override
	public void updateCoupon(Date endDate, double price, int couponId, int companyId)
			throws CouponNotExistException, CompanyNotExistException {
		couponRepo.updateCoupon(endDate, price, couponId, companyId);

	}

	/***
	 * Get Coupon by ID
	 */
	@Override
	public Coupon getCoupon(int couponId) throws CouponNotExistException {
		return couponRepo.findByid(couponId);
	}
	
	/***
	 * Get Coupon by title;
	 * @param title
	 * @return Coupon
	 */
	public Coupon getCouponByTitle(String title)
	{
		return couponRepo.findBytitle(title);
	}
	/***
	 * 
	 * @param couponId
	 * @param id
	 * @return
	 */
	public Coupon getCompanyCoupon(int couponId, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/***
	 * Get all Coupons
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons() throws CouponsNotExistException {
		return (ArrayList<Coupon>) couponRepo.findAll();
	}

	/***
	 * Get all Coupons by type
	 */
	@Override
	public ArrayList<Coupon> getAllCouponsByType(CouponType type) throws CouponsNotExistException {
		return couponRepo.findBytype(type);
	}

	@Override
	public Coupon amountCoupon(int amount) throws CouponNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
