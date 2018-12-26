package com.bodao.ehrms.entity;
/**
 * 汽车
 * @author 59112
 *
 */
public class Car {
	private Brand brand;//品牌
	private Category category;//类别
	private int id;//汽车编号
	private String car_number;//车牌号
	private String model;//汽车型号
	private String color;//汽车颜色
	private String t_comments;//汽车简介
	private double price;//汽车市场价
	private double rent;//汽车日租金
	private int statu;//汽车状态
	private int useable;//汽车是否上架
	public Car() {
		super();
	}
	
	public Car(Brand brand, Category category, String car_number, String model, String color, String t_comments,
			double price, double rent, int statu, int useable) {
		super();
		this.brand = brand;
		this.category = category;
		this.car_number = car_number;
		this.model = model;
		this.color = color;
		this.t_comments = t_comments;
		this.price = price;
		this.rent = rent;
		this.statu = statu;
		this.useable = useable;
	}



	public Car(Brand brand, Category category, int id, String car_number, String model, String color, String t_comments,
			double price, double rent, int statu, int useable) {
		super();
		this.brand = brand;
		this.category = category;
		this.id = id;
		this.car_number = car_number;
		this.model = model;
		this.color = color;
		this.t_comments = t_comments;
		this.price = price;
		this.rent = rent;
		this.statu = statu;
		this.useable = useable;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getT_comments() {
		return t_comments;
	}
	public void setT_comments(String t_comments) {
		this.t_comments = t_comments;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public int getUseable() {
		return useable;
	}
	public void setUseable(int useable) {
		this.useable = useable;
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", category=" + category + ", id=" + id + ", car_number=" + car_number
				+ ", model=" + model + ", color=" + color + ", t_comments=" + t_comments + ", price=" + price
				+ ", rent=" + rent + ", statu=" + statu + ", useable=" + useable + "]";
	}
	
	
}
