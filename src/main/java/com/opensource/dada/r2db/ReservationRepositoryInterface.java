package com.opensource.dada.r2db;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepositoryInterface extends ReactiveCrudRepository<Reservation, Integer>{

}
