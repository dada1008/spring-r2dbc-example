package com.opensource.dada.r2db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {

	@Autowired
	//private ReservationRepositoryClass reservationRepository;
	private ReservationRepositoryInterface reservationRepository;
	
	@Test
	public void all() {
		Flux<Void> deleteAll = this.reservationRepository.findAll().flatMap(r -> this.reservationRepository.deleteById(r.getId()));
		
		StepVerifier.create(deleteAll)
			.expectNextCount(0)
			.verifyComplete()
			
		;
		
		Flux<Reservation> reservationFlux = Flux.just("one", "two", "three")
		.map(name -> new Reservation(null, name))
		.flatMap(r -> this.reservationRepository.save(r));
		
		StepVerifier.create(reservationFlux)
		.expectNextCount(3)
		.verifyComplete()
		
		;
		
		Flux<Reservation> findAll = this.reservationRepository.findAll();
		
		StepVerifier.create(findAll)
			.expectNextCount(3)
			.verifyComplete()
			
		;
		
	}

}

