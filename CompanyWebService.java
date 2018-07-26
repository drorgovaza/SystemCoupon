package com.couponsystem.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.Facade.CompanyFacade;
import com.couponsystem.FactorySystem.FacotryOfSystem;
import com.couponsystem.entities.Company;
import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.ClientType;
import com.couponsystem.exceptions.CompanyExistException;
import com.couponsystem.exceptions.CompanyNotExistException;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;

@Controller
@RestController
@RequestMapping("Company") 
@CrossOrigin("*")
public class CompanyWebService {

	// Core
	@Autowired
	private CompanyFacade COMf;
	@Autowired
	private FacotryOfSystem cs;
	
	/***
	 * Fake Login for testing
	 * @return AdminFacade
	 */
	private CompanyFacade facade()
	{
		return (CompanyFacade) COMf.login("google", "4321", ClientType.COMPANY);
	}
	
	/****
	 * Creating new Coupon
	 * @param coupon
	 * @return Response entity
	 * @throws CompanyNotExistException 
	 * @throws CouponExistException 
	 */
	@RequestMapping(value = "/createCoupon" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createCompany(@RequestBody Coupon google) throws CouponExistException, CompanyNotExistException
	{
		// Fake login
		COMf = facade();
		
		try {
			
			COMf.createCoupon(google);
			// Success
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Company was created successfully.");
		} catch (CouponExistException e) {
			// Fail
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
}
//	@RequestMapping(value = "/DELETECoupon" , method = RequestMethod.DELETE , consumes = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody ResponseEntity removeCoupon(@RequestBody Coupon couponId1) {
//		COMf=facade();
//		try 
//		{
//			COMf.removeCoupon(couponId);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("coupon was created successfully.");
//
//	}catch(CouponNotExistException e) 
//		{
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
//
//		}
//	}
//}
//		
//		


