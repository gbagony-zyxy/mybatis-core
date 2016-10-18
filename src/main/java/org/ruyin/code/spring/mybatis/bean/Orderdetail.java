package org.ruyin.code.spring.mybatis.bean;
/**
 *  订单明细
 */
public class Orderdetail {

	private String id;
	private String ordersId;
	private String itemsId;
	private int itemsNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public int getItemsNum() {
		return itemsNum;
	}

	public void setItemsNum(int itemsNum) {
		this.itemsNum = itemsNum;
	}

}
