package learn.hibernate.spring.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import learn.hibernate.domain.User;
import learn.hibernate.repository.UserSearchRepository;
@RestController
public class UserSearchController {
	
	@Autowired
	private UserSearchRepository usr;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@RequestMapping("/searchUser/{term}")
	@ResponseBody
	public List<User> searchUser(@PathVariable String term){
		return usr.searchUser(term);
	}
	@RequestMapping("/buildIndex")
	@ResponseStatus(value=HttpStatus.OK)
	public void buildIndex(){
		Session session = sessionFactory.openSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(session != null)
				session.close();
		}
	}
}
