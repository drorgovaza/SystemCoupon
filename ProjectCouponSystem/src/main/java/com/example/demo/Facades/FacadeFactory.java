package com.example.demo.Facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FacadeFactory {
	
	// Object's members
	@Autowired
	private AdminFacade adminf;
	@Autowired
	private CompanyFacade compf;
	@Autowired
	private CustomerFacade custf;

	/***
	 * Login method
	 * @param name
	 * @param password
	 * @param type
	 * @return CouponClientFacade
	 */
	public CouponClientFacade login(String name , String password , ClientType type)
	{
		switch(type)
		{
		case ADMIN:
			return adminf.login(name, password, type);
		case COMPANY:
			return compf.login(name, password, type);
		case CUSTOMER:
			return custf.login(name, password, type);
			default:
				return null;
		}
	}
}