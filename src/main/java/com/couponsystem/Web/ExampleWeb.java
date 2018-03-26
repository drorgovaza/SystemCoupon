package com.couponsystem.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.Facade.AdminFacade;
import com.couponsystem.exceptions.CompanyNotExistException;

@RestController
public class ExampleWeb {

	@Autowired
	AdminFacade af;

	@RequestMapping(value = "/getCompany/{id}" , method = RequestMethod.GET)
	public String getMethod(@PathVariable("id") int id)
	{
		try {
			return af.getCompany(id).toString();
		} catch (CompanyNotExistException e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/post/{number}" , method = RequestMethod.POST)
	public String postMethod(@PathVariable("number")int number)
	{
		return "POST " + number;
	}
	
	@RequestMapping(value = "/put" , method = RequestMethod.PUT)
	public String putMethod()
	{
		return "PUT";
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
	public String deleteMethod()
	{
		return "DELETE";
	}
}

