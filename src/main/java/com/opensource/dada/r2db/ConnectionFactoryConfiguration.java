package com.opensource.dada.r2db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
public class ConnectionFactoryConfiguration {

	@Bean
	ConnectionFactory connectionFactory() {
	//ConnectionFactory connectionFactory(@Value("${spring.datasource.url}") String url) {
		PostgresqlConnectionConfiguration configuration = PostgresqlConnectionConfiguration.builder()
				.database("orders")
				.password("sa")
				.username("postgres")
				.host("localhost")
				.build();
		
		return new PostgresqlConnectionFactory(configuration);
		
	}
	
	/*@Bean
	DatabaseClient databaseClient() {
		return DatabaseClient.create(connectionFactory());
	}*/
	 
}
