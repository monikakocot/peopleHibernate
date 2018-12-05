package pl.akademiakodu.peopleHibernate.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.peopleHibernate.model.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,Long> {


    List<Person> findByName(String name);
    List<Person> findBySurname(String surname);
}
