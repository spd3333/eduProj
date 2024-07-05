package ru.sberbank.jd;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.repository.PersonRepository;
import ru.sberbank.jd.repository.PersonRepositoryImpl;
import ru.sberbank.jd.security.model.Person;

/**
 * Unit test for create Person
 */
public class AppTest {

@Test
public void createPersonTest01() {

   PersonRepository personRepository = new PersonRepositoryImpl();
   Person person1 = new Person();
   person1.setFirstName("John");
   person1.setLogin("login99");
   person1.setPassword("pswd99");
   personRepository.save(person1);

   Person person2 = new Person();
   person2.setFirstName("Nil2");
   person2.setLogin("login9928");
   person2.setPassword("pswd992");
   personRepository.save(person2);

   Assert.assertNotNull(personRepository.get("login9928"));
 }

   @Test
   public void ReadPersonTest02() {

      PersonRepository personRepository = new PersonRepositoryImpl();
      Person person3 = new Person();
      person3.setFirstName("Oleg");
      person3.setLogin("oleg7");
      person3.setPassword("99422");
      personRepository.save(person3);

      Person person4 = new Person();
      person4.setFirstName("Petr");
      person4.setLogin("lpetr99");
      person4.setPassword("193844");
      personRepository.save(person4);

      Assert.assertNotNull(personRepository.list());
   }
}