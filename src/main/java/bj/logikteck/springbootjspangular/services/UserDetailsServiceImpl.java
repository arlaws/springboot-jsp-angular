package bj.logikteck.springbootjspangular.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bj.logikteck.springbootjspangular.entities.Users;
import bj.logikteck.springbootjspangular.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Users user = userRepository.findByUsername(username);

            return UserDetailsImpl.build(user);
        } catch (Exception e) {
            return (UserDetails) new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}