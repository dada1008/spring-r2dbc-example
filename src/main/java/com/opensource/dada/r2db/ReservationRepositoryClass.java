package com.opensource.dada.r2db;

import org.springframework.stereotype.Repository;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ReservationRepositoryClass {

	private final ConnectionFactory connectionFactory;

	public ReservationRepositoryClass(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	Mono<Void> deleteById(Integer id) {
		return this.connection()
				.flatMapMany( connection -> connection.createStatement("delete from reservation where id = $1")
											.bind("$1", id)
											.execute()).then();
						
	}

	Flux<Reservation> findAll() {
		return this.connection()
				.flatMapMany(connection -> Flux.from(connection.createStatement("select * from reservation").execute())
						.flatMap(r -> r.map((row, rowMetadata) -> new Reservation(row.get("id", Integer.class),
																					row.get("name", String.class)
																				)

											)

						)
				);
	}

	Flux<Reservation> save(Reservation reservation) {
		Flux<? extends Result> flatManyMapResult = this.connection()
				.flatMapMany( connection -> connection.createStatement("insert into reservation( name) values ($1)")
											.bind("$1", reservation.getName())
											.add()
											.execute()
						
						
				);
		return flatManyMapResult.switchMap(r -> Flux.just(new Reservation(reservation.getId(), reservation.getName())));
	}

	private Mono<Connection> connection() {
		return Mono.from(this.connectionFactory.create());
	}
}
