package learn.hibernate.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.hibernate.domain.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	public List<User> findByLastName(String lastName);
}
