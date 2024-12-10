package estudos.mac.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import estudos.mac.Entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
