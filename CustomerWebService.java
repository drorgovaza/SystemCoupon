package com.couponsystem.Web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.Facade.CustomerFacade;
import com.couponsystem.FactorySystem.FacotryOfSystem;
import com.couponsystem.entities.Coupon;
import com.couponsystem.entities.Customer;
import com.couponsystem.enums.ClientType;
import com.couponsystem.enums.CouponType;
import com.couponsystem.exceptions.CouponExistException;
import com.couponsystem.exceptions.CouponNotExistException;
import com.couponsystem.exceptions.CustomerNotExistException;

@RestController
@CrossOrigin("*")
@RequestMapping("CUSTOMER")

@Component
public class CustomerWebService {

	// Core
	@Autowired
	private CustomerFacade cf;
	@Autowired
	private FacotryOfSystem custf;
	
	private CustomerFacade facade()
	{
		return (CustomerFacade) cf.login("dror", "12345", ClientType.CUSTOMER);
	}
	

@RequestMapping(value = "/purchaseCouopn" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity purchaseCouopn(@RequestBody Customer customer)
{
	// Fake login
	cf = facade();
			cf.purchaseCoupon(1, 2);
			// Success
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("THE Coupom Purchased");
			
}
@RequestMapping(value = "/getPurchasedCouopn" , method = RequestMethod.GET , consumes = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity getPurchasedCouopn( Customer customer) throws CustomerNotExistException 
{
	//fake login
	cf = facade();
	try {
		cf.getPurchasedCouopn(1);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("THE Coupom Purchased");
	}	catch(CouponNotExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("NotFOUND!");
			
	}
}
@RequestMapping(value = "/getCouponsByType" , method = RequestMethod.GET , consumes = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity getCouponsByType( CouponType type) throws CouponNotExistException, CustomerNotExistException 
{
	//fake login
	cf = facade();
	try {
		cf.getCouponsByType(type);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("THE Coupom Purchased");
	}	
	catch(CouponNotExistException e) {
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body("NotFOUND!");
			
	}
}

}
