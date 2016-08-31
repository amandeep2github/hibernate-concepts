package learn.hibernate.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.hibernate.domain.User;

@Transactional
public interface UserDao extends JpaRepository<User, Long>{
	public User findByLastName(String lastName);
}
