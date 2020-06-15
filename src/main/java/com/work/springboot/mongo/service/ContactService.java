package com.work.springboot.mongo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.work.springboot.mongo.model.Contact;

public interface ContactService {

	/**
	 * Method to create new Contact in the db using mongo-db repository.
	 * @param contact
	 */
	public void createContact(List<Contact> contact);

	/**
	 * Method to fetch all Contact from the db using mongo-db repository.
	 * @return
	 */
	public Collection<Contact> getAllContacts();

	/**
	 * Method to fetch Contact by id using mongo-db repository.
	 * @param id
	 * @return
	 */
	public Optional<Contact> findContactById(int id);

	/**
	 * Method to delete Contact by id using mongo-db repository.
	 * @param id
	 */
	public void deleteContactById(int id);

	/**
	 * Method to update Contact by id using mongo-db repository.
	 * @param id
	 */
	public void updateContact(Contact contact);
}