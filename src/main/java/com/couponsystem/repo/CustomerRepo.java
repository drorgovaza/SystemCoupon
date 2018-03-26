/**
 * 
 */
package com.couponsystem.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.couponsystem.entities.Customer;

/**Repository for Customer
 * @author DrorGovaza
 *
 */
@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {
	

	/***
	 * Insert into Customer Coupon
	 * @param customerId
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO customer_coupons (customers_id, coupons_id) VALUES (:customerId, :couponId)", nativeQuery = true) 
	void insertCustomerCoupon(@Param("customerId") int  customerId, @Param("couponId") int couponId);

	/***
	 * Updating Coupon's amount while purchasing it
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon coup SET coup.amount = amount-1 WHERE coup.id = :couponId")
	void updateCouponAmount(@Param("couponId") int couponId);
	
	/***
	 * Updating Customer set only password by its ID
	 * @param password
	 * @param customerId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Customer cust SET cust.password = :password WHERE cust.id = :customerId")
	void updateCustomer(@Param("password") String password, @Param("customerId") int customerId);

	/***
	 * Get Customer by ID
	 * @param customerId
	 * @return Customer
	 */
	Customer findByid(int customerId);
	
	/***
	 * Get Customer by its name
	 * @param custName
	 * @return Customer
	 */
	Customer findBycustName(String custName);

	/***
	 * Get Customer by its name & password
 	 * @param custName
	 * @return Customer
	 */
	Customer findBycustNameAndPassword(String custName , String password);

	/***
	 * Updating amount -1
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon coup SET coup.amount=amount-1 WHERE coup.id =:couponId")
	void updatingAmountForPurchase(@Param("couponId") int couponId);
	
	
	
}
