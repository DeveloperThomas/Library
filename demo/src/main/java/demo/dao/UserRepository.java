package demo.dao;

import demo.model.Book;
import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findByUsername(String username);
    //Optional<User> findUsername(String username);
}
