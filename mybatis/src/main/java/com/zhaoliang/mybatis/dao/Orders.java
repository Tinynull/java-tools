package com.zhaoliang.mybatis.dao;

import java.sql.Date;

public class Orders {

	private int order_num;
	private Date order_date;
	private String cust_id;

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	@Override
	public String toString() {
		return "Orders [order_num=" + order_num + ", order_date=" + order_date
				+ ", cust_id=" + cust_id + "]";
	}

}
