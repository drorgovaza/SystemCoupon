package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.example.demo.Facades.CustomerFacade;
import com.example.demo.entities.Coupon;
import com.example.demo.enums.CouponType;
import com.example.demo.exceptions.CouponNotExistException;
import com.example.demo.exceptions.CouponNotFoundException;
import com.example.demo.exceptions.CouponsNotExistException;
import com.example.demo.exceptions.CustomersNotExistException;

@CrossOrigin(origins = "*")
@RequestMapping(value = "Customer")
@RestController
public class Customerweb {

//	@Autowired
//	private CouponSystem couponSystem;
	
	/***
	 * Login
	 * @return Customer Facade
	 */
	private CustomerFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		CustomerFacade cf = (CustomerFacade) session.getAttribute("facade");
		return cf;
	}
	
	/***
	 * Fake Login
	 * @param request
	 * @param response
	 * @return
	 */
//	private CustomerFacade getFacade(HttpServletRequest request, HttpServletResponse response)
//	{
//		return (CustomerFacade) couponSystem.login("Enosh", "1234", ClientType.CUSTOMER);
//	}
	
	/***
	 * Purchase coupon
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/purchaseCoupon" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity purchaseCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		cf.purchaseCoupon(((HttpSession) cf).getId(), coupon);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
	}
	
	/***
	 * Get all purchased coupons
	 * @return
	 * @throws CouponsNotExistException 
	 * @throws CouponNotExistException 
	 * @throws CustomersNotExistException 
	 */
	@RequestMapping(value = "/getAllPurchasedCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllPurchasedCoupons(HttpServletRequest request , HttpServletResponse response) throws CustomersNotExistException, CouponNotExistException, CouponsNotExistException
	{
		//Login
		CustomerFacade cf = getFacade(request, response);
		
		List<Coupon> coupons = cf.getAllPurchasedCoupons();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
	}
	
	/***
	 * Get all purchased coupons by type
	 * @param type
	 * @return
	 * @throws CustomersNotExistException 
	 */
//	@RequestMapping(value = "/getAllPurchasedCoupons/type/{type}" , method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity getAllPurchasedCouponsByType(@PathVariable("type") String type , HttpServletRequest request , HttpServletResponse response) throws CustomersNotExistException
//	{
//		// Login
//		CustomerFacade cf = getFacade(request, response);
//			
//		try{
//		List<Coupon> coupons = cf.getCouponsByType(type);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
//		}catch(CouponNotExistException e){
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
//		}
//	}
	
	/***
	 * Get all purchased coupons by price
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchasedCoupons/price/{price}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllPurchasedCouponsByPrice(@PathVariable("price") double price , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		List<Coupon> coupons = cf.getCouponsByPrice( price);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
	}
	
	/***
	 * Get all Coupons for purchase
	 * @param request
	 * @param response
	 * @return
	 * @throws CouponsNotExistException 
	 * @throws CouponNotExistException 
	 */
	@RequestMapping(value = "/getAllCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCoupons(HttpServletRequest request, HttpServletResponse response) throws CouponNotExistException, CouponsNotExistException
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		try{
			List<Coupon> coupons = cf.getAllPurchasedCoupons();
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch(CustomersNotExistException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
}