package learn.hibernate.spring.rest.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class TestUserControllerJerseyClientWay2 {
	
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
		ClientResponse cr = wr.type("application/json").get(ClientResponse.class);
		//ResponseEntity re = cr.getEntity(ResponseEntity.class);
		//assertTrue(cr.getStatusCode()==HttpStatus.OK);
		System.out.println(cr.getResponseStatus());
		System.out.println(cr.getEntity(List.class));
				
        
    }
	
	

}
