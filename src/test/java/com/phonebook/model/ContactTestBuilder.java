package com.phonebook.model;

import com.phonebook.model.Contact.ContactBuilder;

public class ContactTestBuilder {

	private static String DEFAULT_FIRST_NAME = "Danielly";

	private static String DEFAULT_LAST_NAME = "Garcia Jardim";

	private static String DEFAULT_EMAIL = "daniellygj@gmail.com";

	private ContactBuilder builder = Contact.builder();

	public static ContactTestBuilder init() {
		return new ContactTestBuilder();
	}

	public ContactBuilder withDefaultValues() {
		return builder
				.firstName(DEFAULT_FIRST_NAME)
				.lastName(DEFAULT_LAST_NAME)
				.email(DEFAULT_EMAIL);
	}
}
