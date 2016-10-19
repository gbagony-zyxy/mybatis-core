package org.ruyin.code.spring.mybatis.bean;

/**
 * 订单明细
 */
public class Orderdetail {

	private int id;
	private String ordersId;
	private String itemsId;
	private int itemsNum;

	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
