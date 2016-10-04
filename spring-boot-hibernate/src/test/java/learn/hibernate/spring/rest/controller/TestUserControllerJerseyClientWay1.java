package learn.hibernate.spring.rest.controller;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTestersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import ch.qos.logback.core.net.SyslogOutputStream;
import learn.hibernate.SpringBootWebApplication;
import learn.hibernate.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SpringBootWebApplication.class)
@WebAppConfiguration
@IntegrationTest
public class TestUserControllerJerseyClientWay1 {
	
	private static Client client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ClientConfig cc = new DefaultClientConfig();
		cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		client = Client.create(cc); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void userNotFound() throws Exception {
		WebResource wr = client.resource("http://localhost:8080/users/Singh");
		ClientResponse cr = wr.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		//ResponseEntity re = cr.getEntity(ResponseEntity.class);
		//assertTrue(cr.getStatusCode()==HttpStatus.OK);
		System.out.println(cr.getResponseStatus());
		User user = cr.getEntity(User.class);
		//list.forEach(user -> System.out.println(user.getFirstName()));
		System.out.println(user.getFirstName());
				
        
    }
	
	

}
