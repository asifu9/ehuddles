package com.pro.emp.domain;

public class Department {

	private String key;
	private int id;
	private String name;
	private int orderDep;
	
	
	
	
	public int getOrderDep() {
		return orderDep;
	}
	public void setOrderDep(int orderDep) {
		this.orderDep = orderDep;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
