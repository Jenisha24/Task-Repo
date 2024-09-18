package com.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Tables {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int tableId;
	private String tableName;
	private int seatingCapacity;
	
	public Tables() {
	}

	public Tables(int tableId, String tableName, int seatingCapacity) {
		this.tableId = tableId;
		this.tableName = tableName;
		this.seatingCapacity = seatingCapacity;
	}
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	
	
}
