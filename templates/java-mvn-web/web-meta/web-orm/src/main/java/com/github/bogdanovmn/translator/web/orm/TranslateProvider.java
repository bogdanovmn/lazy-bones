package com.github.bogdanovmn.${projectKey}.web.orm;

import javax.persistence.Entity;

@Entity
public class TranslateProvider extends BaseEntityWithUniqueName {
	public TranslateProvider(String name) {
		super(name);
	}

	public TranslateProvider() {
	}
}
