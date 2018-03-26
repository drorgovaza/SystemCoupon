package com.couponsystem.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.couponsystem.enums.CouponType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * A Table for Coupon
 * @author DrorGovaza
 *
 */
@Entity
@NoArgsConstructor
public class Coupon {
	
	// Object's members

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private int id;
	
	@Column
	@Getter
	@Setter
	private String title;

	@Column
	@Getter
	@Setter
	private Date startDate;

	@Column
	@Getter
	@Setter
	private Date endDate;
	
	@Column
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private CouponType type;
	
	@Column
	@Getter
	@Setter
	private int amount;
	
	@Column
	@Getter
	@Setter
	private String message;
	
	@Column
	@Getter
	@Setter
	private double price;
	
	@Column
	@Getter
	@Setter
	private String image;
	
	@Setter
	@ManyToOne
	@JoinColumn(name = "Company_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Company company;
	
	@ManyToMany(mappedBy = "coupons")
	private List<Customer> customers = new ArrayList<>();

	/***
	 * CTR with no ID / Company / Customers
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @param amount
	 * @param message
	 * @param price
	 * @param image
	 */
	public Coupon(String title, Date startDate, Date endDate, CouponType type, int amount, String message, double price,
			String image) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.amount = amount;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	/***
	 * ToString with no Customers or Company in it.
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", type="
				+ type + ", amount=" + amount + ", message=" + message + ", price=" + price + ", image=" + image + "]";
	}
	
	
}
