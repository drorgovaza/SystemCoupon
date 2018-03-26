package com.couponsystem.DAO;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;

/** Data Access Object for Company
 * @author DrorGovaza
 *
 */
@Service
public interface CompanyDAO {

	/***
	 * Creating new Company
	 * @param company
	 * @throws CompanyExistException
	 */
	void createCompany(Company company)throws CompanyExistException;
	
	/***
	 * Removing Company by its ID
	 * @param companyId
	 * @throws CompanyNotExistException
	 */
	void removeCompany(int companyId)throws CompanyNotExistException;
	
	/***
	 * Updating Company set only password & email by its ID
	 * @param password
	 * @param email
	 * @param id
	 * @throws CompanyNotExistException
	 */
	void updateCompany(String password , String email , int companyId)throws CompanyNotExistException;
	
	/***
	 * Getting Company by its ID
	 * @param id
	 * @return Company
	 * @throws CompanyNotExistException
	 */
	Company getCompanyByID(int companyId)throws CompanyNotExistException;
	
	/****
	 * Get All Companies
	 * @return ArrayList<Company>
	 * @throws CompaniesNotExistException
	 */
	ArrayList<Company> getAllCompanies()throws CompaniesNotExistException;
	
	/***
	 * Get All Company's Coupons by its ID
	 * @param companyId
	 * @return ArrayList<Coupon>
	 * @throws CompanyNotExistException
	 * @throws CouponsNotExistException
	 */
	ArrayList<Coupon> getCompanyCoupons(int companyId)throws CompanyNotExistException , CouponsNotExistException;
	
	///////////////coupons////////////////////
	
	Company RemoveCoupon(int CouponId)throws CouponsNotExistException;
	
	/***
	 * Login method for Company
	 * @param companyName
	 * @param password
	 * @return true/false by login parameters
	 */
	boolean login(String companyName , String password);
}
