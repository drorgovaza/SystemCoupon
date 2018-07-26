package com.couponsystem.Web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.Facade.AdminFacade;
import com.couponsystem.FactorySystem.FacotryOfSystem;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CustomerExistException;
import com.couponsystem.exceptions.CustomerNotExistException;

/***
 * Web Service for Admin
 * @author grimbergs
 *
 */
@RestController // -> WebService 
@RequestMapping("Admin") // -? the URL path
@CrossOrigin("*")
public class AdminWebService {
	
	// Core
	@Autowired
	private AdminFacade af;
	@Autowired
	private FacotryOfSystem cs;
	
	/***
	 * Fake Login for testing
	 * @return AdminFacade
	 */
	private AdminFacade facade()
	{
		return (AdminFacade) cs.login("admin1", "1234", ClientType.ADMIN);
	}
	
	/****
	 * Creating new Company
	 * @param company
	 * @return Response entity
	 */
	@RequestMapping(value = "/createCompany" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createCompany(@RequestBody Company company)
	{
		// Fake login
		af = facade();
		
		try {
			
			af.createCompany(company);
			// Success
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Company " + company.getCompName() + " was created successfully.");
		} catch (CompanyExistException e) {
			// Fail
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	

	@RequestMapping(value = "/DELETECompany" , method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity removeCompany(@RequestBody Company google1, int companyId) {
		af=facade();
		try 
		{
		af.removeCompany(companyId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Company was created successfully.");

	}catch(CompanyNotExistException e) 
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());

		}
		}
	
	/***
	 * Get Company by id
	 * @param id
	 * @return Response Entity
	 */
	@RequestMapping(value = "getCompany/{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCompany(@PathVariable("id") int id)
	{
		// Fake login
		af = facade();
		
		try {
			Company company = af.getCompany(id);
			// Suuccess 
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(company);
		} catch (CompanyNotExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
		

			/////////////////////////Customer/////////////////////////
	
	 
		@RequestMapping(value = "createCustomer" , method = RequestMethod.POST)
		public ResponseEntity createCustomer(@RequestBody Customer customer , HttpServletRequest request , HttpServletResponse response) throws CustomerExistException
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
			af.createCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customer);
		
			}
			
		
		private AdminFacade getFacade(HttpServletRequest request, HttpServletResponse response) {
				// TODO Auto-generated method stub
				return null;
			}

		@RequestMapping(value="/removeCoustomer" , method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody ResponseEntity removeCustomer(@RequestBody Customer customer1)
		{
			af=facade();
		 try {
			 af.removeCustomer(2); 
			
			 return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Customer"+removeCustomer(customer1)+"was delete");
		} 
		 catch (CustomerNotExistException e) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
		 
		}
		@RequestMapping(value = "getCustomer/{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody ResponseEntity getCustomer(@PathVariable("id") int id)
		{
			// Fake login
			af = facade();
			
			try {
				Customer customer = af.getCustomer(1);
				// Suuccess 
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customer);
			} catch (CustomerNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
			}
		}
					
}
