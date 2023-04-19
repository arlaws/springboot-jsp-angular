package bj.logikteck.springbootjspangular.api;

import bj.logikteck.springbootjspangular.entities.Users;
import bj.logikteck.springbootjspangular.services.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserApi {

    @Autowired
    UserService usersService;

    //Toute la liste
    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    //Ajouter
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Users> add(@RequestBody Users users, HttpServletRequest request) {
        return new ResponseEntity<>(usersService.save(users), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit/{id}/", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Users> edit(@PathVariable("id") Long id,
            @RequestBody Users users) throws Throwable {

        Users user = usersService.findById(id).get();

        user.setNom(users.getNom());
        user.setPrenoms(users.getPrenoms());
        user.setContact(users.getContact());
        user.setEmail(users.getEmail());
        user.setActivated(true);
        user.setVerified(true);

        return new ResponseEntity<>(usersService.save(user), HttpStatus.CREATED);
    }
}
