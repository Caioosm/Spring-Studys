package estudos.mac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import estudos.mac.Entities.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long>{
}
