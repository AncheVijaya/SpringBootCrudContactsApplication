package com.work.springboot.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.work.springboot.mongo.model.Contact;

@Repository
public interface ContactDao extends MongoRepository<Contact, Integer> {

}