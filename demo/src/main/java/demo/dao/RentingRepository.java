package demo.dao;

import demo.model.Book;
import demo.model.Renting;
import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Long> {
    Optional<Renting> findByUserAndBook(User user, Book book);
    Optional<Renting> findByBook(Book book);
}
