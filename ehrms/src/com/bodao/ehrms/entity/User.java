package com.bodao.ehrms.entity;
/**
 * �˻���Ϣ
 * @author 59112
 *
 */
public class User {
	private int id;//�û����
	private String name;//�û���
	private String pwd;//����
	private int sex;//�Ա�
	private String idcard;//���֤
	private String tel;//�绰
	private String address;//��ַ
	private int ytpe;//�˻�����
	public User(){
		super();
	}
	
	public User(String name, String pwd, int sex, String idcard, String tel, String address, int ytpe) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.idcard = idcard;
		this.tel = tel;
		this.address = address;
		this.ytpe = ytpe;
	}

	public User(int id, String name, String pwd, int sex, String idcard, String tel,String address, int ytpe) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.idcard = idcard;
		this.tel = tel;
		this.address = address;
		this.ytpe = ytpe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getYtpe() {
		return ytpe;
	}
	public void setYtpe(int ytpe) {
		this.ytpe = ytpe;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", sex=" + sex + ", idcard=" + idcard + ", tel=" + tel
				+ ", address=" + address + ", ytpe=" + ytpe + "]";
	}
}
