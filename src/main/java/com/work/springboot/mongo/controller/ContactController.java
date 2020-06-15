package com.work.springboot.mongo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work.springboot.mongo.model.Contact;
import com.work.springboot.mongo.service.ContactService;

@RestController
public class ContactController {

	@Autowired
	ContactService contactService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Method to save contacts in the db.
	 * @param contact
	 * @return
	 */
	@PostMapping(value= "/contacts")
	public String create(@RequestBody List<Contact> contact) {
		logger.info("ContactController:: create: Saving contacts.");
		contactService.createContact(contact);
		return "contact records created.";
	}

	/**
	 * Method to fetch all contacts from the db.
	 * @return
	 */
	@GetMapping(value= "/contacts")
	public Collection<Contact> getAll() {
		logger.info("ContactController:: create: Getting all contacts.");
		return contactService.getAllContacts();
	}

	/**
	 * Method to fetch contact by id.
	 * @param id
	 * @return
	 */
	@GetMapping(value= "/contacts/{id}")
	public Optional<Contact> getById(@PathVariable(value= "id") int id) {
		logger.debug("ContactController:: create: Getting contact with id= {}.", id);
		return contactService.findContactById(id);
	}

	/**
	 * Method to update contact by id.
	 * @param id
	 * @param conact
	 * @return
	 */
	@PutMapping(value= "/contacts/{id}")
	public String update(@PathVariable(value= "id") int id, @RequestBody Contact conact) {
		logger.debug("ContactController:: create: Updating contact with id= {}.", id);
		conact.setId(id);
		contactService.updateContact(conact);
		return "Contact record for id= " + id + " updated.";
	}

	/**
	 * Method to delete contact by id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value= "/contacts/{id}")
	public String delete(@PathVariable(value= "id") int id) {
		logger.debug("ContactController:: create: Deleting contact with id= {}.", id);
		contactService.deleteContactById(id);
		return "Contact record for id= " + id + " deleted.";
	}
}