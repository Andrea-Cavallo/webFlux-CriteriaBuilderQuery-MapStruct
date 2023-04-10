package com.application.products.utils;

public class Constants {

	private Constants() {
	}
	public static final String PRODUCTS = "products";
	public static final String MAX_PRICE = "maxPrice";
	public static final String MIN_PRICE = "minPrice";
	public static final String PRODUCTS_PRODUCT_ID = "products/{productId}";
	public static final String PRODUCT_NAME = "productName";
	public static final String PRICE = "price";
	public static final String ORDERS = "orders";
	public static final String TRANSCODING = "transcoding";
	public static final String LOG_INFO_SERVICE = "calling the criteria builder with parameters: productName={}, minPrice={}, maxPrice={}";
	public static final String PRODUCT_NOT_FOUND = "No products document matching the given criteria, please provide other request ";
	public static final String ORDERS_ORDER_ID = "orders/{orderId}";
	public static final String INITIALIZED_REACTIVE_MONGO_TEMPLATE = "Initialized ReactiveMongoTemplate";
	public static final String INITIALIZED_REACTIVE_MONGO_DB_FACTORY = "Initialized Reactive MongoDB factory";
	public static final String MY_LOCAL_HOST = "mongodb://localhost:27017/productsDB";
	public static final String DB_NAME = "productsDB";

}
