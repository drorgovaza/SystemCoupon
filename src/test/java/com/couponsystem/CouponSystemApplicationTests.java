package com.couponsystem;


import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.couponsystem.DBDAO.CompanyDBDAO;
import com.couponsystem.DBDAO.CouponDBDAO;
import com.couponsystem.DBDAO.CustomerDBDAO;
import com.couponsystem.Facade.AdminFacade;
import com.couponsystem.Facade.CompanyFacade;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CompaniesNotExistException;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CouponsNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;
import com.couponsystem.exceptions.CustomersNotExistException;
import com.couponsystem.sidekicks.DateMaker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests {
	
	// DBDAO
	@Autowired
	private CompanyDBDAO companyDb;
	@Autowired
	private CustomerDBDAO customerDb;
	@Autowired
	private CouponDBDAO couponDb;
	
	// Facades
	@Autowired
	private AdminFacade af;
	@Autowired
	private CompanyFacade compf;

	/***
	 * Last line for visual reasons
	 */
	@AfterClass
	public static void afterEverything()
	{
		System.out.println("###############################");
	}
	
	@Test
	public void contextLoads() {
	}
	
	/***
	 * Test for DBDAO methods
	 */
	@Test
	@Ignore
	public void dbdaoTest()
	{
		// Company
		
		// Create
		try {
			companyDb.createCompany(new Company("IBM", "1234", "ibm@gmail.com"));
			companyDb.createCompany(new Company("Aroma", "1234", "aroma@gmail.com"));
			companyDb.createCompany(new Company("remove", "1234", "ibm@gmail.com"));
		} catch (CompanyExistException e) {
		}
		
		// Remove
		try {
			companyDb.removeCompany(3);
		} catch (CompanyNotExistException e) {
		}
		
		// Update
		try {
			companyDb.updateCompany("123", "aroma@walla.co.il", 2);
		} catch (CompanyNotExistException e) {
		}
		
		// Get 
		try {
			Company aroma = companyDb.getCompanyByID(2);
			System.out.println(aroma);
		} catch (CompanyNotExistException e) {
		}
		
		// Get All 
		try {
			ArrayList<Company> allcompanies = companyDb.getAllCompanies();
			System.out.println(allcompanies);
		} catch (CompaniesNotExistException e) {
		}
		
		// Customer
		
		// Create
		
		try {
			customerDb.createCustomer(new Customer("Avi", "1234"));
			customerDb.createCustomer(new Customer("Itsik", "1234"));
			customerDb.createCustomer(new Customer("remove", "1234"));
		} catch (CustomerExistException e) {
		}
		
		// Remove
		try {
			customerDb.removeCustomer(6);
		} catch (CustomerNotExistException e) {
		}
		
		// Update
		try {
			customerDb.updateCustomer("updated", 5);
		} catch (CustomerNotExistException e) {
		}
		
		// Get
		try {
			Customer cust = customerDb.getCustomerByID(5);
			System.out.println(cust);
		} catch (CustomerNotExistException e) {
		}
		
		// Get All 
		try {
			ArrayList<Customer> allCustomers = customerDb.getAllCustomers();
			System.out.println(allCustomers);
		} catch (CustomersNotExistException e) {
		}
		
		// Coupon
		
		// Create 
		Coupon coffe = new Coupon("Coffe break", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), CouponType.FOOD, 100, "hi", 19, "coffe.com/j.jpg");
		Coupon computers = new Coupon("Computers", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), CouponType.ELECTRICITY, 100, "hi", 19, "computers.com/j.jpg");
		Coupon remove = new Coupon("remove", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), CouponType.ELECTRICITY, 100, "hi", 19, "computers.com/j.jpg");
		try {
			couponDb.createCoupon(coffe, 2);
			couponDb.createCoupon(computers, 1);
			couponDb.createCoupon(remove, 1);
		} catch (CouponExistException | CompanyNotExistException e) {
		}
		
		
		// Remove
		try {
			couponDb.removeCoupon(9	, 1);
		} catch (CouponNotExistException | CompanyNotExistException e) {
		}
		
		// Update
		try {
			couponDb.updateCoupon(DateMaker.fixDate(2099, 1, 1), 199, 7	, 2);
		} catch (CouponNotExistException | CompanyNotExistException e) {
		}
		
		// Get Coupon
		try {
			Coupon coup = couponDb.getCoupon(7);
			System.out.println("coupon :" + coup);
		} catch (CouponNotExistException e) {
		}
		
		// Get all Coupons
		try {
			ArrayList<Coupon> allcoupons = couponDb.getAllCoupons();
			System.out.println(allcoupons);
		} catch (CouponsNotExistException e) {
		}
		
		// Get Company Coupons
		try {
			ArrayList<Coupon> compCoupos = companyDb.getCompanyCoupons(1);
			System.out.println("Company coupons:" + compCoupos);
		} catch (CompanyNotExistException | CouponsNotExistException e) {
		}
		
		// Purchase Coupon
		try {
			customerDb.purchaseCoupon(8, 4);
		} catch (CouponNotExistException | CustomerNotExistException e) {
		}
		
		// Get Customer Coupons
		try {
			ArrayList<Coupon> custCoups = customerDb.getCustomerCoupons(4);
			System.out.println("customer coupons: " + custCoups);
		} catch (CustomerNotExistException | CouponsNotExistException e) {
		}
	}

	/***
	 * Test for Admin Facade
	 */
	@Ignore
	@Test
	public void adminFacadeTest()
	{
	
		// Login
		af = af.login("admin", "1234", ClientType.ADMIN);
		System.out.println(af);
		
		// Creating Company
		
		try {
			af.createCompany(new Company("ForTreveller", "12344", "ft@gmail.com"));
		} catch (CompanyExistException e) {
			System.out.println(e.getMessage());
		}
		
		// Removing Company
		try {
			af.removeCompany(1);
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}
		
		// Update Company
		try {
			af.updateCompany("1234", "coffe@maafe.com", 2);
			af.updateCompany("1234", "ft@gmail.com", 10);
			af.updateCompany("1234", "coffe@maafe.com", 209);
		} catch (CompanyNotExistException e) {
			System.out.println(e.getMessage());
		}
		
		// Get Company by id
//		try {
//			Company comp = af.getCompany(2);
//			System.out.println(comp);
//			Company comp2 = af.getCompany(299999999);
//		} catch (CompanyNotExistException e) {
//			System.out.println(e.getMessage());
//		}
		
		// Get all Companies
		try {
			ArrayList<Company> allCompanies = af.getAllCompanies();
			System.out.println(allCompanies);
		} catch (CompaniesNotExistException e) {
		}
		
		// Create Customer
		try {
			af.createCustomer(new Customer("Sarel", "1234"));
			af.createCustomer(new Customer("remove", "1234"));
			af.createCustomer(new Customer("Sarel", "1234"));
		} catch (CustomerExistException e) {
			System.out.println(e.getMessage());
		}
		
		// Removing Customer
		try {
			af.removeCustomer(12);
			af.removeCustomer(166666);
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}
		
		// Updating Customer
		try {
			af.updateCustomer("hagever", 11);
			af.updateCustomer("0", 121212);
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}
		
		// Getting Custoer
		try {
			Customer fromAdmin = af.getCustomer(11);
			System.out.println(fromAdmin);
		//	Customer fromAdminos = af.getCustomer(11909090);
		} catch (CustomerNotExistException e) {
			System.out.println(e.getMessage());
		}
		
		// Getting all Customers
		try {
			ArrayList<Customer> allCustomers = af.getAllCustomers();
			System.out.println(allCustomers);
		} catch (CustomersNotExistException e) {
		}
	}
	
	/***
	 * Test for CompanyFacade
	 */
	@Test
	public void companyFacadeTest()
	{
		// Login
		compf = compf.login("ForTraveller", "1234", ClientType.COMPANY);
		//System.out.println(compf.getLoginCompany());
	
		// Cteating Coupon
//		Coupon toast = new Coupon("Toast for the road", DateMaker.fixDate(2018, 1, 1), DateMaker.fixDate(2019, 1, 1), CouponType.FOOD, 100, "hi", 27, "t.com/p.jpg");
//		try {
//			compf.createCoupon(toast);
//		} catch (CouponExistException | CompanyNotExistException e) {
//			System.out.println(e.getMessage());
//		}
		
	}
}
