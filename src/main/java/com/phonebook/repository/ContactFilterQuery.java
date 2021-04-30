package com.phonebook.repository;

import com.phonebook.controller.ContactFilter;
import com.phonebook.model.Contact;
import com.phonebook.model.Contact_;
import org.springframework.data.jpa.domain.Specification;

public class ContactFilterQuery {

	private ContactFilter filter;

	public ContactFilterQuery(ContactFilter filter) {
		this.filter = filter;
	}

	public Specification<Contact> buildFilter() {

		return Specification
				.where(enableFetchOrderByPriority())
				.and(matchEmail())
				.and(matchFirstName())
				.and(matchLastName());
	}

	private Specification<Contact> matchEmail() {
		return (root, query, cb) -> {
			if (filter.getEmail() == null || filter.getEmail().isEmpty()) {
				return null;
			}

			return cb.like(cb.lower(root.get(Contact_.email)), "%" + filter.getEmail().toLowerCase() + "%");
		};
	}

	private Specification<Contact> matchFirstName() {
		return (root, query, cb) -> {
			if (filter.getFirstName() == null || filter.getFirstName().isEmpty()) {
				return null;
			}

			return cb.like(cb.lower(root.get(Contact_.firstName)), "%" + filter.getFirstName().toLowerCase() + "%");
		};
	}

	private Specification<Contact> matchLastName() {
		return (root, query, cb) -> {
			if (filter.getLastName() == null || filter.getLastName().isEmpty()) {
				return null;
			}

			return cb.like(cb.lower(root.get(Contact_.lastName)), "%" + filter.getLastName().toLowerCase() + "%");
		};
	}

	private Specification<Contact> enableFetchOrderByPriority() {
		return (root, query, cb) -> {
			query.orderBy(
					cb.asc(root.get(Contact_.firstName)),
					cb.asc(root.get(Contact_.lastName)),
					cb.asc(root.get(Contact_.id)));
			return null;
		};
	}

}
