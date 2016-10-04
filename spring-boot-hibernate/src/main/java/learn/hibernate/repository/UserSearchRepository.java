package learn.hibernate.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import learn.hibernate.domain.User;

@Repository
public class UserSearchRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	public List<User> searchUser(String term){
		// get the full text entity manager
	    FullTextEntityManager fullTextEntityManager =
	      org.hibernate.search.jpa.Search.
	      getFullTextEntityManager(entityManager);
	    
	 // create the query using Hibernate Search query DSL
	    QueryBuilder queryBuilder = 
	      fullTextEntityManager.getSearchFactory()
	      .buildQueryBuilder().forEntity(User.class).get();
	    
	 // a very basic query by keywords
	    org.apache.lucene.search.Query query =
	      queryBuilder
	        .keyword()
	        .onFields("firstName", "lastName")
	        .matching(term)
	        .createQuery();
	 // wrap Lucene query in an Hibernate Query object
	    org.hibernate.search.jpa.FullTextQuery jpaQuery =
	      fullTextEntityManager.createFullTextQuery(query, User.class);
	    
	    @SuppressWarnings("unchecked")
		List<User> results = jpaQuery.getResultList();
		return results;
	}
}
