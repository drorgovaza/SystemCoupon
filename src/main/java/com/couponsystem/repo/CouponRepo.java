/**
 * 
 */
package com.couponsystem.repo;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.couponsystem.entities.Coupon;
import com.couponsystem.enums.CouponType;

/**Repository for Coupon
 * @author DrorGovaza
 *
 */
@Repository
public interface CouponRepo extends CrudRepository<Coupon, Integer> {

	
	/***
	 * Removing Company Coupon
	 * @param couponId
	 * @param companyId
	 * @return 
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon coup WHERE coup.id = :couponId AND coup.company.id = :companyId")
	void removeCoupon(@Param("couponId") int couponId, @Param("companyId") int companyId);

	/***
	 * Removing Company Coupons
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon coup WHERE  coup.company.id = :companyId")
	void removeAllCompanyCoupons(@Param("companyId") int companyId);

	/***
	 * Update Coupon set only endDate & price by Coupon's ID & Company's ID
	 * @param endDate
	 * @param price
	 * @param couponId
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon coup SET coup.endDate = :endDate , coup.price = :price WHERE coup.id = :couponId AND coup.company.id = :companyId")
	void updateCoupon(@Param("endDate") Date endDate , @Param("price") double price , @Param("couponId") int couponId , @Param("companyId") int companyId);

	/***
	 * Get Coupon by ID
	 * @param id
	 * @return Coupon
	 */
	Coupon findByid(int couponId);
	
	/***
	 * Get Coupon by title
	 * @param title
	 * @return Coupon
	 */
	Coupon findBytitle(String title);

	/***
	 * Get Coupon by title & Company ID
	 * @param title
	 * @param companyId
	 * @return Coupon
	 */
	Coupon findBytitleAndCompanyId(String title ,int companyId);
	/***
	 * Get Coupon by title & Customer Id
	 * @param title
	 * @param customerId
	 * @return Coupon
	 */
	Coupon findBytitleAndCustomersId(String title , int customerId);
	
	/***
	 * Get all Coupons by type
	 * @param type
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> findBytype(CouponType type);
	
	/***
	 * Get all Company's Coupons
	 * @param companyId
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> findByCompanyId(int companyId);
	
	/***
	 * Get Company Coupon
	 * @param couponId
	 * @param companyId
	 * @return Coupon
	 */
	Coupon findByidAndCompanyId(int couponId , int companyId);

	/***
	 * Get Customer Coupon
	 * @param couponId
	 * @param customerId
	 * @return Coupon
	 */
	Coupon findByidAndCustomersId(int couponId , int customerId);

	/***
	 * Get all Customer's Coupons
	 * @param customerId
	 * @return ArrayList<Coupon>
	 */
	ArrayList<Coupon> findByCustomersId(int customerId);
	/***
	 * 
	 * @param price
	 * @param customerId
	 * @return
	 */

	 ArrayList<Coupon> getCustomerCouponsByPrice(double price, int customerId);
/***
 * 
 * @param companyId
 * @param type
 * @return
 */
	ArrayList<Coupon> findByCompanyIdAndType(int companyId, CouponType type);
}
