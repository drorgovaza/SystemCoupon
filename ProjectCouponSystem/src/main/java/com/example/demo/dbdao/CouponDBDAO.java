package com.example.demo.dbdao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.db.CouponDAO;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CompanyNotFoundException;
import com.example.demo.exceptions.CouponExistException;
import com.example.demo.exceptions.CouponNotFoundException;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.exceptions.CustomerNotFoundExceptin;
import com.example.demo.repo.CouponRepo;
import com.example.demo.enums.CouponType;
@Service
public class CouponDBDAO implements CouponDAO {
	CouponRepo couponRepo;
	public CouponDBDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCoupon(Coupon coupon, int compId) throws CouponExistException, CompanyNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCoupon(int coupId, int compId) throws CouponNotFoundException, CompanyNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCoupon(Date endDate, double price, int coupId, int compId)
			throws CompanyNotFoundException, CouponNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Coupon> getAllCoupns() throws CouponsNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getCouponsByType(CouponType couponType) throws CouponsNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(int id) throws CustomerNotFoundExceptin {
		// TODO Auto-generated method stub
		return null;
	}

	public Coupon getCoupon(int couponId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Coupon> getCouponsByTypeAndCustomerid(CouponType type, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public Coupon getCompanyCoupon(int couponId, int id) {
		// TODO Auto-generated method stub
		return null;

	}

	public Coupon getCouponByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
