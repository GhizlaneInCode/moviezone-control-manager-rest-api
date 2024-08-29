package com.giantlink.introduction.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUp {
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String city;
	private String password;
}

