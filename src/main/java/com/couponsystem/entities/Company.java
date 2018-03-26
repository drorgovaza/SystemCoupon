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
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * A Table for Company
 * @author DrorGovaza
 *
 */
@NoArgsConstructor
@Entity
public class Company {
	// Object's members

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private int id;
	
	@Column
	@Getter
	@Setter
	private String compName;
	
	@Column
	@Getter
	@Setter
	private String password;

	@Column
	@Getter
	@Setter
	private String email;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Coupon> coupons = new ArrayList<>();

	/***
	 * CTR with no ID / Coupons in it.
	 * @param compName
	 * @param password
	 * @param email
	 */
	public Company(String compName, String password, String email) {
		super();
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	/***
	 * ToString with no Coupons in it.
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
	}
	

	
}
