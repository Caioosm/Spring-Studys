package estudos.mac.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import estudos.mac.Entities.Person;
import estudos.mac.Exceptions.RequiredObjectIsNullException;
import estudos.mac.Exceptions.ResourcenNotFoundException;
import estudos.mac.controllers.PersonController;
import estudos.mac.data.DTO.v1.PersonDTO;
import estudos.mac.mapper.DozerMapper;
import estudos.mac.repositories.PersonRepository;

@Service
public class PersonService {

    private static final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;
    
    public List<PersonDTO> findAll(){
        logger.info("findind all persons!");
        
        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
        persons.stream().forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception ex) {
            }
        });
        return persons;
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one person");
    
        var entity = personRepository.findById(id).orElseThrow(() -> new ResourcenNotFoundException("No records found for this ID!"));
    
        var dto = DozerMapper.parseObject(entity, PersonDTO.class);
        try {
            dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        } catch (Exception ex) {
        }
        return dto;
    }

    public PersonDTO createPerson(PersonDTO person){
        logger.info("Creating one PersonDTO!");

        if(person == null) throw new RequiredObjectIsNullException();

        var entity = DozerMapper.parseObject(person, Person.class);
        var dto =  DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
        try {
            dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        } catch (Exception ex) {
        }
        return dto;
    }

    public PersonDTO updatePerson(PersonDTO person){
        logger.info("Updating a PersonDTO!");

        if(person == null) throw new RequiredObjectIsNullException();

        Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourcenNotFoundException("No record found for this ID!"));
        
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        
        var dto = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
        try {
            dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        } catch (Exception ex) {
        }
        return dto;
    }

    public void deletePerson(Long id){
        logger.info("Deleting a PersonDTO!");

        var entity = personRepository.findById(id).orElseThrow(() -> new ResourcenNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
    }
}
