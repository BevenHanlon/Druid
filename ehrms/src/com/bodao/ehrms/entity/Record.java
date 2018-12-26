package com.bodao.ehrms.entity;

import java.sql.Date;

/**
 * ������Ϣ
 * @author 59112
 *
 */
public class Record {
	private int id;//���ޱ��
	private User user;//�û�
	private Car car;//����
	private Date start_date;//�⳵ʱ��
	private Date return_date;//����ʱ��
	private double payment;//֧������ܶ�
	public Record() {
		super();
	}
	public Record(int id, User user, Date start_date, Date return_date, double payment, Car car) {
		super();
		this.id = id;
		this.user = user;
		this.start_date = start_date;
		this.return_date = return_date;
		this.payment = payment;
		this.car = car;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", user=" + user + ", start_date=" + start_date + ", return_date=" + return_date
				+ ", payment=" + payment + ", car=" + car + "]";
	}
	
	
	
	
}
