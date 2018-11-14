package com.example.demo.db;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.exceptions.CompaniesNotExistException;
import com.example.demo.exceptions.CompanyExistException;
import com.example.demo.exceptions.CompanyNotFoundException;
import com.example.demo.exceptions.CouponsNotExistException;

@Service
public interface CompanyDAO {
	

	void createCompany(Company company)throws CompanyExistException;
	
	void removeCompany(int id)throws CompanyNotFoundException;
	
	void updateCompany(String password , String email , int id)throws CompanyNotFoundException;
	
	Company getCompany(int id)throws CompanyNotFoundException;
	
	List<Company> getAllCompanies() throws CompaniesNotExistException;
	
	List<Coupon> getAllCoupons(int compId)throws CouponsNotExistException;
	
	boolean login(String compName , String password);
}
