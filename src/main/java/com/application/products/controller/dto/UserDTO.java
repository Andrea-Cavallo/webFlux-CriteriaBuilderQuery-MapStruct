package com.application.products.controller.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8020617615244006171L;
	private String firstName;
	private String lastName;
	private String email;
}