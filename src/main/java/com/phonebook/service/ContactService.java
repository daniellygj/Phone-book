package com.phonebook.service;

import com.phonebook.model.Contact;
import com.phonebook.repository.ContactRepository;
import com.phonebook.utils.exception.Exception.EmptyPhonesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

	@Autowired
	private ContactRepository repository;

	public Contact addNewContact(Contact contact) {
		if (contact.getPhone() == null || contact.getPhone().isEmpty()) {
			throw new EmptyPhonesException();
		}
		return repository.save(contact);
	}
}
