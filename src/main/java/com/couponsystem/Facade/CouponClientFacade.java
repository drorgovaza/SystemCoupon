package com.couponsystem.Facade;

import org.springframework.stereotype.Component;

import com.couponsystem.enums.ClientType;

@Component
public interface CouponClientFacade {

	/***
	 * Abstract method for login
	 * @param name
	 * @param password
	 * @param type
	 * @return Facade
	 */
	CouponClientFacade login(String name , String password , ClientType type);
	
	CouponClientFacade purchaseCoupon(int CouponId, int CompanyID);
}
