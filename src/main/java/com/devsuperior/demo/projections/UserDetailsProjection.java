package com.devsuperior.demo.projections;

public interface UserDetailsProjection {
	String getEmail();

	String getPassword();

	Long getRoleId();

	String getAuthority();
}
