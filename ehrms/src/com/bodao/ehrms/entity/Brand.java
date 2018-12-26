package com.bodao.ehrms.entity;
/**
 * 品牌
 * @author 59112
 *
 */
public class Brand {
	private int id;//品牌编号
	private String sname;//品牌名称
	public Brand() {
		super();
	}
	public Brand(int id, String sname) {
		super();
		this.id = id;
		this.sname = sname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "Brand [id=" + id + ", sname=" + sname + "]";
	}

}
