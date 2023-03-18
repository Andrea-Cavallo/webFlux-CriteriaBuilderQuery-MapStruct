package com.application.ordine.documents.enums;

public enum OrderType {
	DRESS("Dress"), TECHNOLOGY("Technology"), GAMING("Gaming"), HOME("Home"), BOOKS("Books"), SPORTS("Sports");

	private final String name;

	OrderType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}