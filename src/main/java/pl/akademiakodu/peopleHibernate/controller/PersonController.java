package pl.akademiakodu.peopleHibernate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.peopleHibernate.model.Person;
import pl.akademiakodu.peopleHibernate.repository.PersonRepository;

@Controller
public class PersonController {

  /*

  You need to add your database account in application.properties:
  spring.datasource.username=
  spring.datasource.password=
  And create Db "people"
  spring.datasource.url=jdbc:mysql://localhost:3306/people

  I MySQL Workbench

   */


    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/people/new")
    public String add(){
        return "add";
    }

    @PostMapping("/people")
    public String create(@ModelAttribute Person person, ModelMap modelMap){
        personRepository.save(person);
        modelMap.addAttribute("person",person);
        return "created";
    }
    @GetMapping("/people")
    public String displayAll(ModelMap modelMap){
        modelMap.addAttribute("people",personRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("person",personRepository.findById(id).get());
        personRepository.deleteById(id);
        return "delete";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable Long id,ModelMap modelMap){
        modelMap.addAttribute("person",personRepository.findById(id).get());
        return "edit";
    }

    @GetMapping("/people/{id}")
    public String findperson(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("person",personRepository.findById(id).get());
        return "showPerson";
    }

    @GetMapping("/searchname/{id}")
    public String searchName(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("name",personRepository.findById(id).get());
        return "searchName";
    }

    /**
    @GetMapping("/searchname")
    public String searchName(){
        return "searchName";
    }
*/
    @PostMapping("/name")
    public String searchName (@RequestParam String name, ModelMap modelMap){
        modelMap.addAttribute("person",personRepository.findByName(name));
        return "name";
    }


    @GetMapping("/searchsurname/{id}")
    public String searchSurname(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("name",personRepository.findById(id).get());
        return "searchSurname";
    }

    @PostMapping("/surname")
    public String searchSurname (@RequestParam String surname, ModelMap modelMap){
        modelMap.addAttribute("person",personRepository.findBySurname(surname));
        return "surname";
    }

}
