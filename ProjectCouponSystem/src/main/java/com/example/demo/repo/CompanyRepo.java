package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Company;

@Repository
public interface CompanyRepo extends CrudRepository<Company, Integer> {
	
	/***
	 * Update Company set only password & email by its ID
	 * @param password
	 * @param email
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Company comp SET comp.password = :password , comp.email = :email WHERE comp.id = :companyId")
	void updateCompany(@Param("password") String password ,@Param("email") String email ,@Param("companyId") int companyId);

	/***
	 * Get Company by its id
	 * @param id
	 * @return Company
	 */
	Company findByid(int id);
	
	/***
	 * Get Company by name & password
	 * @param compName
	 * @param password
	 * @return Company
	 */
	Company findBycompNameAndPassword(String compName , String password);

	/***
	 * Get Company by name 
	 * @param compName
	 * @return Company
	 */
	Company findBycompName(String compName);
	
	 
}