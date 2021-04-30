package com.phonebook.service;

import com.phonebook.model.Contact;
import com.phonebook.model.ContactTestBuilder;
import com.phonebook.model.Phone;
import com.phonebook.model.PhoneTestbuilder;
import com.phonebook.repository.ContactRepository;
import com.phonebook.utils.exception.Exception.EmptyPhonesException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

	private static final Long CONTACT_ID = 1L;

	@InjectMocks
	private ContactService service;

	@Mock
	private ContactRepository repository;

	@Test
	public void shouldAddNewContact() {
		Phone phone = PhoneTestbuilder
				.init()
				.withDefaultValues()
				.build();

		Contact contact = ContactTestBuilder
				.init()
				.withDefaultValues()
				.phone(Collections.singletonList(phone))
				.build();

		Contact contactWithId = ContactTestBuilder
				.init()
				.withDefaultValues()
				.phone(Collections.singletonList(phone))
				.id(CONTACT_ID)
				.build();

		when(repository.save(contact)).thenReturn(contactWithId);

		Contact contactSaved = service.addNewContact(contact);

		assertEquals(contactWithId.getId(), contactSaved.getId());
		assertEquals(contactWithId.getFirstName(), contactSaved.getFirstName());
		assertEquals(contactWithId.getLastName(), contactSaved.getLastName());
		assertEquals(contactWithId.getPhone().get(0), contactSaved.getPhone().get(0));
	}

	@Test(expected = EmptyPhonesException.class)
	public void addNewContactShouldFail() {
		Contact contact = ContactTestBuilder
				.init()
				.withDefaultValues()
				.build();

		service.addNewContact(contact);
	}
}
