package com.couponsystem.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * A Table for Customer
 * @author DrorGovaza
 *
 */
@Entity
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private int id;
	
	@Column
	@Getter
	@Setter
	private String custName;

	@Column
	@Getter
	@Setter
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Coupon> coupons = new ArrayList<>();

	/***
	 * CTR with no ID / Coupons.
	 * @param custName
	 * @param password
	 */
	public Customer(String custName, String password) {
		super();
		this.custName = custName;
		this.password = password;
	}

	/***
	 * ToString with no Coupons attribute
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}
	
	
	
}
