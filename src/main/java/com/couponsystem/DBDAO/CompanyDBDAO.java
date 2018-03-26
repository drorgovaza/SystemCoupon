
package com.couponsystem.DBDAO;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsystem.DAO.CompanyDAO;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.repo.CompanyRepo;
import com.couponsystem.repo.CouponRepo;

/** Data Bas Data Access Object for Company
 * @author DrorGovaza
 *
 */
@Service
public class CompanyDBDAO implements CompanyDAO {

	// Object's members
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CouponRepo couponRepo;
	
	/***
	 * Creating new Company
	 * @param company
	 * @throws CompanyExistException
	 */
	@Override
	public void createCompany(Company company) throws CompanyExistException {
		companyRepo.save(company);
	}

	/***
	 * Removing Company by its ID
	 */
	@Override
	public void removeCompany(int companyId) throws CompanyNotExistException {
		companyRepo.deleteById(companyId);
	}

	/***
	 * Removing all Company Coupons
	 * @param companyId
	 */
	public void removeAllCompanyCoupons(int companyId)
	{
		couponRepo.removeAllCompanyCoupons(companyId);
	}
	/***
	 * Updating Company set only password & email by its ID
	 * @param password
	 * @param email
	 * @param companyId
	 * @throws CompanyNotExistException
	 */
	@Override
	public void updateCompany(String password, String email, int companyId) throws CompanyNotExistException {
		companyRepo.updateCompany(password, email, companyId);

	}

	/***
	 * Get Company by its ID
	 * @param id
	 * @return Company
	 * @throws CompanyNotExistException
	 */
	@Override
	public Company getCompanyByID(int companyId) throws CompanyNotExistException {
		return companyRepo.findByid(companyId);
	}
	
	/***
	 * Get Company by name
	 * @param compName
	 * @return COmpany
	 */
	public Company getCompanyByName(String compName)
	{
		return companyRepo.findBycompName(compName);
	}
	
	/***
	 * Get Company by name & password
	 * @param compName
	 * @param password
	 * @return
	 */
	public Company getCompanyByNameAndPassword(String compName , String password)
	{
		return companyRepo.findBycompNameAndPassword(compName, password);
	}

	/***
	 * Get All Companies
	 * @return ArrayList<Company> 
	 * @throws CompaniesNotExistException
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws CompaniesNotExistException {
		return (ArrayList<Company>) companyRepo.findAll();
	}

	public ArrayList<Coupon> getCouponsByType(int companyId , CouponType type)throws CompanyNotExistException , CouponsNotExistException
	{
		return couponRepo.findByCompanyIdAndType(companyId, type);
	}

	/***
	 * Get Company's Coupon
	 * @param couponId
	 * @param companyId
	 * @return Coupon
	 * @throws CouponNotExistException
	 * @throws CompanyNotExistException
	 */
	public Coupon getCompanyCoupon(int couponId , int companyId)throws CouponNotExistException , CompanyNotExistException
	{
		return couponRepo.findByidAndCompanyId(couponId, companyId);
	}
	/***
	 * 
	 * @param companyId
	 * @param couponId
	 * @return Coupon
	 * @throws CouponNotExistException
	 * @throws CompanyNotExistException
	 */
	public Coupon removeCouponbyId(int companyId, int couponId)throws CouponNotExistException,CompanyNotExistException
	{
		return couponRepo.findByid(couponId);
	}
	/***
	 * Get Company's Coupon by title
	 * @param title
	 * @param companyId
	 * @return Coupon
	 */
	public Coupon getCompanyCouponByTitle(String title , int companyId)
	{
		return couponRepo.findBytitleAndCompanyId(title, companyId);
	}

	/***
	 * Get Company's Coupons by ID
	 */
	@Override
	public ArrayList<Coupon> getCompanyCoupons(int companyId)
			throws CompanyNotExistException, CouponsNotExistException {
		return couponRepo.findByCompanyId(companyId);
	}

	@Override
	public Company RemoveCoupon(int CouponId) throws CouponsNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String companyName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
