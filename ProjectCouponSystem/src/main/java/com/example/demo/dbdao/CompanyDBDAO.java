package com.example.demo.dbdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.CompanyDAO;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CompaniesNotExistException;
import com.example.demo.exceptions.CompanyExistException;
import com.example.demo.exceptions.CompanyNotFoundException;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.repo.CompanyRepo;
import com.example.demo.enums.CouponType;

@Service
public class CompanyDBDAO implements CompanyDAO {
	
	// Object's members
	@Autowired
	CompanyRepo companyRepo;

	/***
	 * Empty CTR
	 * 
	 */
	public CompanyDBDAO() {
	}

	/***
	 * Creating new Company
	 */
	@Override
	public void createCompany(Company company) throws CompanyExistException {
		
		companyRepo.save(company);
		
	}

	/***
	 * Remove Company by id
	 */
	@Override
	public void removeCompany(int id) throws CompanyNotFoundException {

		companyRepo.deleteById(id);
		
	}

	@Override
	public void updateCompany(String password, String email, int id) throws CompanyNotFoundException {

		companyRepo.updateCompany(password, email, id);
		
	}

	@Override
	public Company getCompany(int id) throws CompanyNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> getAllCompanies() throws CompaniesNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getAllCoupons(int compId) throws CouponsNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String compName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public Company getCompanyByName(String compName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Coupon> getCouponsByType(int companyId, CouponType type) {
		// TODO Auto-generated method stub
		return null;
	}

	public Company getCompanyByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Coupon> getCompanyCoupons(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
