package learn.hibernate.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import learn.hibernate.dao.UserDao;
import learn.hibernate.domain.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/create")
	  @ResponseBody
	  public String create() {
	    String userId = "";
	    try {
	      User user = new User();
	      user.setFirstName("Amandeep");
	      user.setLastName("Amandeep");
	      user.setAddress("858, Sector 22B");
	      user.setOccupation("IT Consultant");
	      user.setSex("Male");
	      userDao.save(user);
	      userId = String.valueOf(user.getId());
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "User succesfully created with id = " + userId;
	  }
	
	@RequestMapping("/getData")
	  @ResponseBody
	  public List<User> getData() {
	      List<User> users = userDao.findAll();
	      return users;
	  }

}
