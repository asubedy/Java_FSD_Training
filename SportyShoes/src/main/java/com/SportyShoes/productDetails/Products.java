package com.SportyShoes.productDetails;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Products {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int prod_id;
	private String prod_name;
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "cat_id",referencedColumnName = "cat_id")
	private Categories category;
	
	public Products(int prod_id, String prod_name, double price, Categories category) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.price = price;
		this.category = category;
	}
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Products [prod_id=" + prod_id + ", prod_name=" + prod_name + ", price=" + price + ", category="
				+ category + "]";
	}
	
}
