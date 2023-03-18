package com.application.ordine.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.ConnectionString;

@Configuration
@Profile("local")
public class MongoDbConfigLocal {
	private static final Logger log = LoggerFactory.getLogger(MongoDbConfigLocal.class);

	@Bean
	public MongoDatabaseFactory mongoDbFactory() {
		ConnectionString cs = new ConnectionString("mongodb://localhost:27017/ordiniDb");
		log.info("Initialized MongoDB factory");
		return new SimpleMongoClientDatabaseFactory(cs);
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		log.info("Initialized MongoTemplate");
		return mongoTemplate;
	}
}
