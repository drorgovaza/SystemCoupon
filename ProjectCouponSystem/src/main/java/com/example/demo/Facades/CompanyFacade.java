package com.example.demo.Facades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbdao.CompanyDBDAO;
import com.example.demo.dbdao.CouponDBDAO;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CompanyNotFoundException;
import com.example.demo.exceptions.CouponExistException;
import com.example.demo.exceptions.CouponNotExistException;
import com.example.demo.exceptions.CouponNotFoundException;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.enums.CouponType;

@Component
public class CompanyFacade implements CouponClientFacade {


	private Company loggedIn;

	@Autowired
	private CompanyDBDAO compdb;

	@Autowired
	private CouponDBDAO coupdb;

	/****
	 * Login method for Company
	 */
	@Override
	public CompanyFacade login(String name, String password, ClientType type) {

		// Checking type
		if (!type.equals(ClientType.COMPANY)) {
			return null;
		}

		// Checking name & password

		if (!compdb.login(name, password)) {
			return null;
		}

		loggedIn = compdb.getCompanyByNameAndPassword(name, password);
		return this;

	}

	/****
	 * Creating new Coupon
	 * 
	 * @param coupon
	 * @param companyId
	 * @throws CouponExistException
	 * @throws CompanyNotFoundException
	 */
	public synchronized void createCoupon(Coupon coupon) throws CouponExistException, CompanyNotExistException, CompanyNotFoundException {

		// Checking if Coupon exist
		Coupon couponCheck = coupdb.getCouponByTitle(coupon.getTitle());
		if (couponCheck != null) {
			throw new CouponExistException("Coupon " + coupon.getTitle() + " allready exist");
		}

		if (loggedIn == null) {
			throw new CompanyNotExistException("Company with the ID: " + loggedIn.getId() + " doesn't exist");
		}
		// Success - creating Coupon
		coupdb.createCoupon(coupon, loggedIn.getId());
	}

	/***
	 * Removing Company's Coupon
	 * 
	 * @param couponId
	 * @throws CompanyNotFoundException
	 * @throws CouponNotFoundException
	 */
	public void removeCoupon(int couponId) throws CompanyNotExistException, CouponNotExistException, CouponNotFoundException, CompanyNotFoundException {

		// Coupon to remove ( must be Company's Coupon)
		Coupon couponCheck = coupdb.getCompanyCoupon(couponId, loggedIn.getId());

		if (loggedIn == null) {
			throw new CompanyNotExistException("Company doesn't login please relogin");
		}

		if (couponCheck == null) {
			throw new CompanyNotExistException("Coupon with the ID: " + couponId + " does not exist");
		}

		// Success - removing Coupon
		coupdb.removeCoupon(couponId, loggedIn.getId());

	}

	/***
	 * Update Company's Coupon
	 * 
	 * @param endDate
	 * @param price
	 * @param couponId
	 * @throws CompanyNotFoundException
	 * @throws CouponNotFoundException
	 */
	public void updateCoupon(Date endDate, double price, int couponId)
			throws CompanyNotExistException, CouponNotExistException, CompanyNotFoundException, CouponNotFoundException {

		// Coupon to update ( must be Company's Coupon)
		Coupon couponCheck = coupdb.getCompanyCoupon(couponId, loggedIn.getId());

		if (loggedIn == null) {
			throw new CompanyNotExistException("Company doesn't login please relogin");
		}

		if (couponCheck == null) {
			throw new CouponNotExistException("Coupon with the ID: " + couponId + " does not exist");
		}

		// Success - updating Coupon
		coupdb.updateCoupon(endDate, price, couponId, loggedIn.getId());
	}

	/***
	 * Get Company Coupon
	 * 
	 * @param couponId
	 * @return Coupon
	 * @throws CompanyNotFoundException
	 * @throws CouponNotFoundException
	 */
	public Coupon getCompanyCoupon(int couponId) throws CompanyNotExistException, CouponNotExistException {

		// Coupon to update ( must be Company's Coupon)
		Coupon couponCheck = coupdb.getCompanyCoupon(couponId, loggedIn.getId());

		if (loggedIn == null) {
			throw new CompanyNotExistException("Company doesn't login please relogin");
		}

		if (couponCheck == null) {
			throw new CouponNotExistException("Coupon with the ID: " + couponId + " does not exist");
		}

		// Success - providing Coupon
		return couponCheck;
	}

	/***
	 * Getting Company's Coupons
	 * 
	 * @return
	 * @throws CouponsNotExistException 
	 * @throws CouponsNotFoundException
	 * @throws CompanyNotFoundException
	 */
	public ArrayList<Coupon> getCompanyCoupons() throws CouponNotExistException, CompanyNotExistException, CouponsNotExistException {
		// Company Coupons
		ArrayList<Coupon> companyCoupons = compdb.getCompanyCoupons(loggedIn.getId());

		// Checking Company
		if (loggedIn == null) {
			throw new CompanyNotExistException("Company doesn't login please relogin");
		}
		// Checking Array List of Coupons
		if (companyCoupons == null) {
			throw new CouponNotExistException("There is no Coupons yet");
		}
		// Success - providing Company Coupons
		return companyCoupons;
	}

	/***
	 * Get Company Coupons by type
	 * @param type
	 * @param companyId 
	 * @return ArrayList<Coupon>
	 * @throws CouponsNotExistException 
	 * @throws CouponsNotFoundException
	 * @throws CompanyNotFoundException
	 */
	public ArrayList<Coupon> getCompanyCouponsByType(CouponType type, int companyId) throws CouponNotExistException, CompanyNotExistException, CouponsNotExistException {
		// Company Coupons
		ArrayList<Coupon> companyCoupons = compdb.getCouponsByType(companyId, type);

		// Checking Company
		if (companyCoupons == null) {
			throw new CompanyNotExistException("Company doesn't login please relogin");
		}
		return companyCoupons;

	}// Success - providing Coupons by type
	

	@Override
	public CouponClientFacade purchaseCoupon(int CouponId, int CompanyID) {
		return null;
	}

	public List<Coupon> getCouponsByPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Coupon> getCouponsByType() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Coupon> getCouponsByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Coupon getCoupon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CouponClientFacade purchaseCoupon(String string, Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}
}