package com.phonebook.service;

import com.phonebook.model.Contact;
import com.phonebook.model.ContactTestBuilder;
import com.phonebook.model.Phone;
import com.phonebook.model.PhoneTestbuilder;
import com.phonebook.repository.ContactRepository;
import com.phonebook.utils.exception.Exception;
import com.phonebook.utils.exception.Exception.EmptyPhonesException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

	@Test
	public void shouldDeleteContact() {
		doNothing().when(repository).deleteById(CONTACT_ID);
		service.deleteContact(CONTACT_ID);
		verify(repository).deleteById(CONTACT_ID);
	}

	@Test
	public void editContactShouldSucceed() {
		Phone phone1 = PhoneTestbuilder
				.init()
				.withDefaultValues()
				.id(1L)
				.build();

		Phone phone2 = PhoneTestbuilder
				.init()
				.withDefaultValues()
				.id(2L)
				.build();

		Phone phone3 = PhoneTestbuilder
				.init()
				.withDefaultValues()
				.id(3L)
				.build();

		Phone phone2Edited = PhoneTestbuilder
				.init()
				.withDefaultValues()
				.id(2L)
				.number("11 11111111")
				.build();

		List<Phone> phonesSaved = Arrays.asList(phone1, phone2, phone3);
		List<Phone> phonesEdited = Arrays.asList(phone1, phone2Edited, phone3);

		Contact contactSaved = ContactTestBuilder
				.init()
				.withDefaultValues()
				.id(CONTACT_ID)
				.phone(phonesSaved)
				.build();

		Contact contactEdited = ContactTestBuilder
				.init()
				.withDefaultValues()
				.id(CONTACT_ID)
				.firstName("Edited")
				.firstName("Edited")
				.email("edited@hotmail.com")
				.phone(phonesEdited)
				.build();

		when(repository.getOne(contactEdited.getId())).thenReturn(contactSaved);
		when(repository.save(contactSaved)).thenReturn(contactEdited);

		Contact contact = service.editContact(contactEdited);

		assertEquals(contactEdited.getId(), contact.getId());
		assertEquals(contactEdited.getLastName(), contact.getLastName());
		assertEquals(contactEdited.getFirstName(), contact.getFirstName());
		assertEquals(contactEdited.getPhone(), contact.getPhone());
		assertEquals(contactEdited.getEmail(), contact.getEmail());
	}

	@Test(expected = Exception.EmptyPhonesException.class)
	public void editContactShouldFail() {
		Contact contactEdited = ContactTestBuilder
				.init()
				.withDefaultValues()
				.id(CONTACT_ID)
				.firstName("Edited")
				.firstName("Edited")
				.email("edited@hotmail.com")
				.build();

		service.editContact(contactEdited);
	}
}
