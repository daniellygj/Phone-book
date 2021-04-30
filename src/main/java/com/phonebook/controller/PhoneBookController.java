package com.phonebook.controller;

import com.phonebook.model.Contact;
import com.phonebook.service.ContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/phone-book")
public class PhoneBookController {

	@Autowired
	private ContactService service;

	@PostMapping
	@ApiOperation(value = "Adicionar novo contato")
	public Contact newContact(@Valid @RequestBody Contact contact) {
		return service.addNewContact(contact);
	}

	@GetMapping
	@ApiOperation(value = "Listar contatos em ordem alfabética")
	public Page<Contact> getContacts(ContactFilter filter, Pageable pageable) {
		return service.listContacts(filter, pageable);
	}

	@DeleteMapping("/{contactId}")
	@ApiOperation(value = "Excluir contato")
	public String deleteContact(@PathVariable("contactId") Long contactId) {
		service.deleteContact(contactId);
		return String.format("Contact with id %d deleted", contactId);
	}

	@PutMapping
	@ApiOperation(value = "Editar contato")
	public Contact editContact(@Valid @RequestBody Contact contact) {
		return service.editContact(contact);
	}
}
