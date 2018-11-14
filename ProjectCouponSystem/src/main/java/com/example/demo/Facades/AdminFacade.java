package com.example.demo.Facades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbdao.CompanyDBDAO;
import com.example.demo.dbdao.CustomerDBDAO;
import com.example.demo.entities.Company;
import com.example.demo.entities.Coupon;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.CompaniesNotExistException;
import com.example.demo.exceptions.CompanyExistException;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CompanyNotFoundException;
import com.example.demo.exceptions.CustomerExistException;
import com.example.demo.exceptions.CustomerNotFoundExceptin;
import com.example.demo.exceptions.CustomersNotExistException;

@Component
public class AdminFacade  implements CouponClientFacade {

	// Login attributes
	private final String NAME = "admin";
	private final String PASSWORD = "1234";

	// DBDAO
	@Autowired
	private CompanyDBDAO compdb;
	@Autowired
	private CustomerDBDAO custdb;

	/***
	 * Login method for AdminFacade
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
		return this;
	}

	// !!!!!!-----Company------!!!!!!!

	/***
	 * Creating new Company if not exist
	 * 
	 * @param company
	 * @throws CompanyExistException
	 */
	public void createCompany(Company company) throws CompanyExistException {
		// Checking if exist
		Company check = compdb.getCompanyByName(company.getCompName());
		if (check != null) {
			throw new CompanyExistException("Company " + company.getCompName() + " already exist");
		}
		// Success - creating Company
		compdb.createCompany(company);
	}

	/***
	 * Removing Company if exist
	 * 
	 * @param companyId
	 * @throws CompanyNotExistException
	 * @throws CompanyNotFoundException 
	 */
	public void removeCompany(int companyId) throws CompanyNotExistException, CompanyNotFoundException {
		// Checking if exist
		Company check = compdb.getCompany(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		// Success - remove Company
		compdb.removeCompany(companyId);
	}

	/***
	 * Update Company set only password & email by ID
	 * 
	 * @param password
	 * @param email
	 * @param companyId
	 * @throws CompanyNotExistException
	 * @throws CompanyNotFoundException 
	 */
	public void updateCompany(String password, String email, int companyId) throws CompanyNotExistException, CompanyNotFoundException {
		// Checking if exist
		Company check = compdb.getCompany(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		else {
		// Success - update Company
		compdb.updateCompany(email, password, companyId);
	}
	}
	/***
	 * Get Company by ID
	 * 
	 * @param companyId
	 * @return Company
	 * @throws CompanyNotExistException
	 * @throws CompanyNotFoundException 
	 */
	public Company getCompany(int companyId) throws CompanyNotExistException, CompanyNotFoundException {
		// Checking if exist
		Company check = compdb.getCompany(companyId);
		if (check == null) {
			throw new CompanyNotExistException("Company with the ID:" + companyId + " does not exist");
		}
		// Success - return Company
		return check;
	}

	/***
	 * Get all Companies
	 * 
	 * @return ArrayList<Company>
	 * @throws CompaniesNotExistException
	 */
	public ArrayList<Company> getAllCompanies() throws CompaniesNotExistException {
		// Checking ArrayList not null
		ArrayList<Company> allCompanies = (ArrayList<Company>) compdb.getAllCompanies();
		if (allCompanies == null) {
			throw new CompaniesNotExistException("Companies ArrayList = null");
		}

		// Success - return all Companies
		return allCompanies;

	}

	// !!!!!!-----Customer------!!!!!!!

	/***
	 * Creating new Customer if not exist
	 * 
	 * @param customer
	 * @throws CustomerExistException
	 */
	public void createCustomer(Customer customer) throws CustomerExistException {
		// Checking if exist
		Customer check = custdb.getCustomerByName(customer.getCustName());
		if (check != null) {
			throw new CustomerExistException("Customer " + customer.getCustName() + " already exist");
		}
		// Success - creating Customer
		custdb.createCustomer(customer);
	}

	/***
	 * Removing Customer if exist
	 * 
	 * @param customerId
	 * @throws CustomerNotFoundExceptin 
	 * @throws CustomerNotExistException
	 */
	public void removeCustomer(int customerId) throws CustomersNotExistException, CustomerNotFoundExceptin {
		// checking if exist
		Customer check = custdb.getCustomer(customerId);
		if (check == null) {
			System.out.println("Customer with the ID: " + customerId + "does not exist");
		}
		// Success - remove Customer
		custdb.removeCustomer(customerId);
	}

	/***
	 * Updating Customer password using Customer id
	 * 
	 * @param password
	 * @param id
	 * @throws CustomerNotFoundExceptin 
	 * @throws CustomerNotExistException
	 */
	public void updateCustomer(String password, int id) throws CustomersNotExistException, CustomerNotFoundExceptin {
		// checking if exist
		Customer check = custdb.getCustomer(id);
		if (check == null) {
			System.out.println("Customer with the ID: " + id + "does not exist");
		}
		// Success - update Customer
		custdb.updateCustomer(password, id);
	}

	/***
	 * Get Customer by ID
	 * 
	 * @param customerId
	 * @return
	 * @throws CustomerNotFoundExceptin 
	 * @throws CustomerNotExistException
	 */
	public Customer getCustomer(int customerId) throws CustomersNotExistException, CustomerNotFoundExceptin {
		// checking if exist
		Customer check = custdb.getCustomer(customerId);
		if (check == null) {
			System.out.println("Customer with the ID: " + customerId + " does not exist");
		}
		// Success - get Customer
		return check;
	}

	/***
	 * Get all Customers
	 * 
	 * @return
	 * @throws CustomersNotExistException
	 */
	public ArrayList<Customer> getAllCustomer() throws CustomersNotExistException {
		// Checking ArrayList not null
		ArrayList<Customer> allCustomer = (ArrayList<Customer>) custdb.getAllCustomers();
		if (allCustomer == null) {
			throw new CustomersNotExistException("Customers ArrayList = null");
		}
		// Success - return all Customers
		return allCustomer;
	}

	@Override
	public CouponClientFacade purchaseCoupon(int CouponId, int CompanyID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CouponClientFacade purchaseCoupon(String string, Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}
}