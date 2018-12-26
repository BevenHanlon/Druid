package com.bodao.ehrms.entity;
/**
 * 类别
 * @author 59112
 *
 */
public class Category {
	private int id;//类别编号
	private String ename;//类别名称
	public Category() {
		super();
	}
	public Category(int id, String ename) {
		super();
		this.id = id;
		this.ename = ename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", ename=" + ename + "]";
	}

}
