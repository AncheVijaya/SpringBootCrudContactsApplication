package com.work.springboot.mongo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.springboot.mongo.dao.ContactDao;
import com.work.springboot.mongo.model.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactDao contactDao;

	@Override
	public void createContact(List<Contact> contact) {
		contactDao.saveAll(contact);
	}

	@Override
	public Collection<Contact> getAllContacts() {
		return contactDao.findAll();
	}

	@Override
	public Optional<Contact> findContactById(int id) {
		return contactDao.findById(id);
	}

	@Override
	public void deleteContactById(int id) {
		contactDao.deleteById(id);
	}

	@Override
	public void updateContact(Contact contact) {
		contactDao.save(contact);
	}
}