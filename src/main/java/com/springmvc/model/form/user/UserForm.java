package com.springmvc.model.form.user;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

	@NotEmpty
	private String name;

	@NotEmpty
	private String nickname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
