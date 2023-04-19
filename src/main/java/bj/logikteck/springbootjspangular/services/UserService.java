package bj.logikteck.springbootjspangular.services;

import bj.logikteck.springbootjspangular.entities.Users;
import bj.logikteck.springbootjspangular.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final int PAGE_ELEMENT_SIZE_ADMIN = 12;

    @Autowired
    private UserRepository rep;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Users save(Users user) {
        user.setNomPrenoms(user.getNom() + " " + user.getPrenoms());
        //user.setPassword(bCryptPasswordEncoder.encode("toto"));

        return rep.save(user);
    }

    public Users findByUsername(String username) {
        return rep.findByUsername(username);
    }

    public Optional<Users> findById(Long id) {
        return rep.findById(id);
    }

    public List<Users> findAll() {
        return rep.findAll();
    }

    public Page<Users> findAllByPage(Integer pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_ELEMENT_SIZE_ADMIN);
        return rep.findAll(pageRequest);
    }
}
