package com.example.demo.web;

import java.util.Date;
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

import com.example.demo.Facades.CompanyFacade;
import com.example.demo.entities.Coupon;
import com.example.demo.enums.CouponType;
import com.example.demo.exceptions.CompanyNotExistException;
import com.example.demo.exceptions.CompanyNotFoundException;
import com.example.demo.exceptions.CouponExistException;
import com.example.demo.exceptions.CouponNotExistException;
import com.example.demo.exceptions.CouponNotFoundException;
import com.example.demo.exceptions.CouponsNotExistException;

@CrossOrigin(origins = "*")
@RequestMapping(value = "Company")
@RestController
public class Companyweb {

//	@Autowired
//	private CouponSystem cs;				
	
	/***
	 * Login
	 * @return Company Facade
	 */
	private CompanyFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		CompanyFacade cf = (CompanyFacade) session.getAttribute("facade");
		return cf;
	}
	
	
	/***
	 * Fake Login
	 */
//	private CompanyFacade getFacade(HttpServletRequest request , HttpServletResponse response)
//	{
//		return (CompanyFacade) cs.login("Aroma","1234", ClientType.COMPANY);
//	}
	
	
	/***
	 * Creating new coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 * @throws CompanyNotFoundException 
	 * @throws CompanyNotExistException 
	 */
	@RequestMapping(value = "/createCoupon" , method = RequestMethod.POST)
	public ResponseEntity createCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response) throws CompanyNotExistException, CompanyNotFoundException
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try {
			
			cf.createCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
		} catch (CouponExistException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Updateing coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 * @throw s CompanyNotFoundException 
	 * @throws CompanyNotExistException 
	 */
	@RequestMapping(value = "/updateCoupon" , method = RequestMethod.PUT)
	public ResponseEntity updateCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response) throws CompanyNotExistException, CompanyNotFoundException
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try
		{
		cf.createCoupon(coupon);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("new price:" + coupon.getPrice() + " and new end date:" + coupon.getEndDate() + " was updated successfully");
		
		}catch(CouponExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Removing a coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 * @throws CompanyNotFoundException 
	 * @throws CouponNotExistException 
	 * @throws CompanyNotExistException 
	 */
	@RequestMapping(value = "/removeCoupon/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity removeCoupon(@RequestBody @PathVariable("id") int id , HttpServletRequest request , HttpServletResponse response) throws CompanyNotExistException, CouponNotExistException, CompanyNotFoundException
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try{
		cf.removeCoupon(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Coupon was removed from the data base");
		}catch(CouponNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get this Company
	 * @return
	 */
	@RequestMapping(value = "getThisCompany" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getThisCompany(HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		return (ResponseEntity) ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)	;
	}
	
	/***
	 * Get all Coupons
	 * @return
	 * @throws CouponsNotExistException 
	 * @throws CompanyNotExistException 
	 */
	@RequestMapping(value = "getAllCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCoupons(HttpServletRequest request , HttpServletResponse response) throws CompanyNotExistException, CouponsNotExistException
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try{
		List<Coupon> coupons = cf.getCompanyCoupons();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch( CouponNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get coupon by id
	 * @param id
	 * @return
//	 */
//	@RequestMapping(value = "/getCoupon/{id}" , method = RequestMethod.GET)
//	public @ResponseBody ResponseEntity getCoupon(@PathVariable("id") int id ,HttpServletRequest request , HttpServletResponse response)
//	{
//		// Login
//		CompanyFacade cf = getFacade(request, response);
//		
//		
//		try{
//		Coupon coupon = cf.getCoupon();
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
//		}}
	
	/***
	 * Get Coupons by type
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "getCouponsByType/{type}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCouponsByType(@PathVariable("type") String type , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		List<Coupon> coupons = cf.getCouponsByType();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
	}
	
	/***
	 * Get Coupons by price
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "/getCouponsByPrice/{price}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCouponsByPrice(@PathVariable("price") double price , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		List<Coupon> coupons = cf.getCouponsByPrice();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
	}

	@RequestMapping(value = "/getCouponsByDate/{timeStamp}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCouponsByDate(@PathVariable("timeStamp")  long timeStamp , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		Date date = new Date(timeStamp);
			
		List<Coupon> coupons = cf.getCouponsByDate();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		
	}
}

