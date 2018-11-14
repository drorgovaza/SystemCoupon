package com.example.demo.Facades;

import com.example.demo.entities.Coupon;

public interface CouponClientFacade {
/***
 * Login method for Facades
 * @param name
 * @param password
 * @param type
 * @return Facade
 */
CouponClientFacade login(String name , String password ,  ClientType type);

CouponClientFacade purchaseCoupon(int CouponId, int CompanyID);

CouponClientFacade purchaseCoupon(String string, Coupon coupon);
}
