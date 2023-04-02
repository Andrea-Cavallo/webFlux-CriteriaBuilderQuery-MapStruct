package com.application.products.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

import com.mongodb.ConnectionString;

@Configuration
@Profile("local")
public class MongoDbConfigLocal {
	private static final Logger log = LoggerFactory.getLogger(MongoDbConfigLocal.class);

	@Bean
	public ReactiveMongoDatabaseFactory reactiveMongoDbFactory() {
		ConnectionString cs = new ConnectionString("mongodb://localhost:27017/productsDB");
		log.info("Initialized Reactive MongoDB factory");
		return new SimpleReactiveMongoDatabaseFactory(cs);
	}

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		ReactiveMongoTemplate reactiveMongoTemplate = new ReactiveMongoTemplate(reactiveMongoDbFactory());
		log.info("Initialized ReactiveMongoTemplate");
		return reactiveMongoTemplate;
	}
}
