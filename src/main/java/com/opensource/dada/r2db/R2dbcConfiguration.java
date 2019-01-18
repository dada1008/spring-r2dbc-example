package com.opensource.dada.r2db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfiguration extends AbstractR2dbcConfiguration{

	private ConnectionFactory connectionFactory;
	
	public R2dbcConfiguration(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	@Override
	public ConnectionFactory connectionFactory() {
		return connectionFactory;
	}

}
