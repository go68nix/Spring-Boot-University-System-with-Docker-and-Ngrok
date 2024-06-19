package de.tum.in.ase.eist;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import de.tum.in.ase.eist.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testAddPerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        personService.save(person);

        assertEquals(1, personRepository.findAll().size());
        var parent = new Person();
        person.setFirstName("Maxus");
        person.setLastName("arfa");
        person.setBirthday(LocalDate.now());
        personService.save(parent);
        personService.addParent(person,parent);


    }

    @Test
    void testDeletePerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        person = personRepository.save(person);

        personService.delete(person);

        assertTrue(personRepository.findAll().isEmpty());
    }
    @Test
    void testAddParent() {
        var person = new Person();
        person.setFirstName("Maxou");
        person.setLastName("Mustermannam");
        person.setBirthday(LocalDate.now());
        var child =new Person();
        child.setFirstName("domar");
        child.setLastName("Musnnam");
        person.setBirthday(LocalDate.now());
        person= personRepository.save(person);
        child= personRepository.save(child);
        personService.addParent(child,person);
        assertEquals(2,personRepository.findAll().size());


    }
    @Test
    void testAddThreeParents() {
        var person = new Person();
        person.setFirstName("Maxou");
        person.setLastName("Mustermannam");
        person.setBirthday(LocalDate.now());
        var child =new Person();
        child.setFirstName("domar");
        child.setLastName("Musnnam");
        person.setBirthday(LocalDate.now());
        var parent =new Person();
        child.setFirstName("walter");
        child.setLastName("white");
        person.setBirthday(LocalDate.now());
        person= personRepository.save(person);
        child= personRepository.save(child);
        parent=personRepository.save(parent);
        personService.addParent(child,parent);
        personService.addParent(child,person);
        assertEquals(2,personRepository.findAll().size());


    }


    // TODO: Add more test cases here



}
