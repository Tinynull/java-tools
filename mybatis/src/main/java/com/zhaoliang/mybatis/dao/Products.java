package com.zhaoliang.mybatis.dao;

import java.math.BigDecimal;

public class Products {
	private String prod_id;
	private String vend_id;
	private String prod_name;
	private BigDecimal pro_price;
	private String pro_desc;

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getVend_id() {
		return vend_id;
	}

	public void setVend_id(String vend_id) {
		this.vend_id = vend_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public BigDecimal getPro_price() {
		return pro_price;
	}

	public void setPro_price(BigDecimal pro_price) {
		this.pro_price = pro_price;
	}

	public String getPro_desc() {
		return pro_desc;
	}

	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}

	@Override
	public String toString() {
		return "Products [prod_id=" + prod_id + ", vend_id=" + vend_id
				+ ", prod_name=" + prod_name + ", pro_price=" + pro_price
				+ ", pro_desc=" + pro_desc + "]";
	}

}
