package com.phonebook.controller;

import com.phonebook.model.Contact;
import com.phonebook.model.ContactTestBuilder;
import com.phonebook.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.phonebook.utils.JSONParser.toJSON;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PhoneBookControllerTest {

	private static final String URL = "/api/phone-book";

	private static final Long CONTACT_ID = 1L;

	@InjectMocks
	private PhoneBookController controller;

	@Mock
	private ContactService service;

	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.build();
	}

	@Test
	public void souldAddNewContact() throws Exception {
		Contact contact = ContactTestBuilder
				.init()
				.withDefaultValues()
				.id(CONTACT_ID)
				.build();

		when(service.addNewContact(contact)).thenReturn(contact);

		mvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(toJSON(contact)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id",
						is(contact.getId().intValue())));
	}

	@Test
	public void souldListContacts() throws Exception {
		Contact contact = ContactTestBuilder
				.init()
				.withDefaultValues()
				.id(CONTACT_ID)
				.build();

//		ContactFilter filter = new ContactFilter();
//
//		final Pageable pageable = PageRequest.of(0, 15);
//		final Page<Contact> page = new PageImpl<>(Collections.singletonList(contact));

		mvc.perform(get(URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldDeleteContact() throws Exception {

		doNothing().when(service).deleteContact(CONTACT_ID);

		mvc.perform(delete(URL + "/" + CONTACT_ID))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",
						is(String.format("Contact with id %d deleted", CONTACT_ID))));
	}

	@Test
	public void shouldEditcontact() throws Exception {
		Contact contact = ContactTestBuilder
				.init()
				.withDefaultValues()
				.id(CONTACT_ID)
				.build();

		when(service.editContact(contact)).thenReturn(contact);

		mvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON).content(toJSON(contact)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id",
						is(contact.getId().intValue())));
	}
}
