package com.springmvc.model.form.user;

import javax.validation.constraints.NotNull;

public class UserUpdateForm extends UserForm {
	
	@NotNull
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
