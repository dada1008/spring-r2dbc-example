package com.opensource.dada.r2db;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	@Id
	private Integer id;
	private String name;
}
