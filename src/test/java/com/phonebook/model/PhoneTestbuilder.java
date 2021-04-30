package com.phonebook.model;

import com.phonebook.model.Phone.PhoneBuilder;

public class PhoneTestbuilder {

	private static String DEFAULT_NUMBER = "(12) 1234-5678";

	private PhoneBuilder builder = Phone.builder();

	public static PhoneTestbuilder init() {
		return new PhoneTestbuilder();
	}

	public PhoneBuilder withDefaultValues() {
		return builder
				.number(DEFAULT_NUMBER);
	}
}
