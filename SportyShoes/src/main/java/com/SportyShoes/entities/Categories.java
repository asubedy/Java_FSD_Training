package com.SportyShoes.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {
	
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Categories(int cat_id, String cat_name) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cat_id;
	private String cat_name;
	
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	
	@Override
	public String toString() {
		return "Categories [cat_id=" + cat_id + ", cat_name=" + cat_name + "]";
	}
	
	
	
	
	
}
