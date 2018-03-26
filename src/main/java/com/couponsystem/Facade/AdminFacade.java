package com.couponsystem.Facade;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couponsystem.DBDAO.CompanyDBDAO;
import com.couponsystem.DBDAO.CustomerDBDAO;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;

import lombok.ToString;

@Component
@ToString
public class AdminFacade implements CouponClientFacade {

	// Login attributes
	private final String NAME = "admin";
	private final String PASSWORD = "1234";

	// DBDAO
	@Autowired
	private CompanyDBDAO compdb;
	@Autowired
	private CustomerDBDAO custdb;

	/***
	 * Login method for Admin Facade
	 */
	@Override
	public AdminFacade login(String name, String password, ClientType type) {
		// Checking type first
		if (!type.equals(ClientType.ADMIN)) {
			return null;
		}
		// Checking name & password
		String que = name + password;
		String ans = NAME + PASSWORD;
		if (!que.equals(ans)) {
			return null;
		}
		// Success
		return this;
	}

	// ------------- Company------------------------
	/***
	 * Create Company if not exist
	 * 
	 * @param company
	 * @throws CompanyExistException
	 */
	public synchronized void createCompany(Company company) throws CompanyExistException {
		// Checking if exist
		Company check = compdb.getCompanyByName(company.getCompName());
		if (check != null) {
			throw new CompanyExistException("Company " + company.getCompName() + " already exist");
		}
		// Success - create Company
		compdb.createCompany(company);
	}

	/***
	 * Removing Company & its Coupons
	 * 
	 * @param companyId
	 * @throws CompanyNotExistException
	 */
	public void removeCompany(int companyId) throws CompanyNotExistException {
		// Checking if exist
		Company check = compdb.getCompanyByID(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}

		// Success r- removing company & its Coupons
		compdb.removeAllCompanyCoupons(companyId);
		compdb.removeCompany(companyId);
	}

	/**
	 * Updating Company set only password & email by ID
	 * 
	 * @param password
	 * @param email
	 * @param companyId
	 * @throws CompanyNotExistException
	 */
	public void updateCompany(String password, String email, int companyId) throws CompanyNotExistException {
		// Checking if exist
		Company check = compdb.getCompanyByID(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		// Success - updating Company
		compdb.updateCompany(password, email, companyId);
	}

	/***
	 * Get Company by ID
	 * 
	 * @param companyId
	 * @return Company
	 * @throws CompanyNotExistException
	 */
	public Company getCompany(int companyId) throws CompanyNotExistException {
		// Checking if exist
		Company check = compdb.getCompanyByID(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		// Success - return Company
		return check;
	}

	/**
	 * Get all Companies
	 * 
	 * @return ArrayList<Company>
	 * @throws CompaniesNotExistException
	 */
	public ArrayList<Company> getAllCompanies() throws CompaniesNotExistException {
		// Checking if null
		ArrayList<Company> allCompanies = compdb.getAllCompanies();
		if (allCompanies == null) {
			throw new CompaniesNotExistException("Companies ArrayList = null");
		}

		// Success - returning Companies
		return allCompanies;
	}

	// ------------- Customer------------------------

	/***
	 * Creating Customer
	 * 
	 * @param customer
	 * @throws CustomerExistException
	 */
	public synchronized void createCustomer(Customer customer) throws CustomerExistException {
		// Checking if exist
		Customer check = custdb.getCustomerByName(customer.getCustName());
		if (check != null) {
			throw new CustomerExistException("Customer " + customer.getCustName() + " already exist");
		}
		// Success - creating Customer
		custdb.createCustomer(customer);

	}

	/***
	 * Removing Customer by ID
	 * 
	 * @param customerId
	 * @throws CustomerNotExistException
	 */
	public void removeCustomer(int customerId) throws CustomerNotExistException {
		// Checking if exist
		Customer check = custdb.getCustomerByID(customerId);
		if (check == null) {
			throw new CustomerNotExistException("Customer with the ID:" + customerId + " does not exist");
		}

		// Success - remove Customer
		custdb.removeCustomer(customerId);
	}

	/***
	 * Update Customer set only password by ID
	 * 
	 * @param password
	 * @param customerId
	 * @throws CustomerNotExistException
	 */
	public void updateCustomer(String password, int customerId) throws CustomerNotExistException {
		// Checking if exist
		Customer check = custdb.getCustomerByID(customerId);
		if (check == null) {
			throw new CustomerNotExistException("Customer with the ID:" + customerId + " does not exist");
		}
		// Success - updating Customer
		custdb.updateCustomer(password, customerId);
	}

	/***
	 * Get Customer by ID
	 * @param customerId
	 * @return Customer
	 * @throws CustomerNotExistException
	 */
	public Customer getCustomer(int customerId) throws CustomerNotExistException {
		// Checking if exist
		Customer check = custdb.getCustomerByID(customerId);
		if (check == null) {
			throw new CustomerNotExistException("Customer with the ID:" + customerId + " does not exist");
		}
		// Success - returning Customer
		return check;
	}
	
	/***
	 * Get all Customers
	 * @return ArrayList<Customer>
	 * @throws CustomersNotExistException
	 */
	public ArrayList<Customer> getAllCustomers() throws CustomersNotExistException
	{
		// Checking if not null
		ArrayList<Customer> allCustomers = custdb.getAllCustomers();
		if(allCustomers == null)
		{
			throw new CustomersNotExistException("Customers ArrayList = null");
		}
		
		// Success - returning Customers
		return allCustomers;
	}

	@Override
	public CouponClientFacade purchaseCoupon(int CouponId, int CompanyID) {
		// TODO Auto-generated method stub
		return null;
	}

}
