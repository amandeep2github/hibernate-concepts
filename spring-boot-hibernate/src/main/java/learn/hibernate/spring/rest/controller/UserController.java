package learn.hibernate.spring.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import learn.hibernate.domain.User;
import learn.hibernate.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<User> create(@RequestBody User user) {
	      /*User user = new User();
	      user.setFirstName("Amandeep");
	      user.setLastName("Amandeep");
	      user.setAddress("858, Sector 22B");
	      user.setOccupation("IT Consultant");
	      user.setSex("Male");*/
	      return new ResponseEntity<User>(userRepo.save(user), HttpStatus.CREATED);
	  }
	
	@RequestMapping("/users/{lastName}")
	  @ResponseBody
	  public ResponseEntity<User> getUserByLastName(@PathVariable String lastName) {
	      List<User> users = userRepo.findByLastName(lastName);
	      return new ResponseEntity<User>(users.get(0), HttpStatus.OK);
	  }
	
	@RequestMapping("/getData")
	  @ResponseBody
	  public List<User> getData() {
	      List<User> users = userRepo.findAll();
	      return users;
	  }

}
