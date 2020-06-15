import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.work.springboot.mongo.MyContactBootApplication;
import com.work.springboot.mongo.model.Address;
import com.work.springboot.mongo.model.Contact;
import com.work.springboot.mongo.model.Name;
import com.work.springboot.mongo.model.Phone;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyContactBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyContactBootApplicationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllContacts() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl()
				+ "/contacts", HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testCreateContact() {
		Contact contact = new Contact();

		Name name = new Name();
		name.setFirst("firstName");
		name.setLast("lastName");
		name.setMiddle("middle");

		Address address = new Address();
		address.setCity("city");
		address.setState("state");
		address.setZip("zip");

		Phone phone = new Phone();
		phone.setNumber("number");
		phone.setType("type");

		List<Phone> phones = new ArrayList<Phone>();
		phones.add(phone);

		contact.setAddress(address);
		contact.setId(1);
		contact.setEmail("email@gmail.com");
		contact.setName(name);
		contact.setPhone(phones);

		ResponseEntity<Contact> postResponse = restTemplate.postForEntity(
				getRootUrl() + "/contacts", contact, Contact.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateContact() {
		int id = 1;

		Contact contact = restTemplate.getForObject(getRootUrl() + "/contacts/"
				+ id, Contact.class);
		contact.setEmail("gmail@gamil.com");
		restTemplate.put(getRootUrl() + "/contacts/" + id, contact);

		@SuppressWarnings("unused")
		Contact updatedContact = restTemplate.getForObject(getRootUrl()+ "/contacts/" + id, Contact.class);
	}

	@Test
	public void testGetContactById() {
		Contact contact = restTemplate.getForObject(getRootUrl()
				+ "/contacts/1", Contact.class);
		assertNotNull(contact);
	}

	@Test
	public void testDeleteContact() {
		int id = 22;
		Contact contact = restTemplate.getForObject(getRootUrl() + "/contacts/"
				+ id, Contact.class);
		System.out.println(contact);
		restTemplate.delete(getRootUrl() + "/contacts/" + id);
		try {
			contact = restTemplate.getForObject(
					getRootUrl() + "contacts/" + id, Contact.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}