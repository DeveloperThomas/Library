package demo.service;

import demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


public class ApplicationUserDetailsService {

//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    public void configAuthentication()
//
//    public ApplicationUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
////        return userRepository.findByUsername(s);
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin1")
//                .roles("ADMIN")
//                .build();
//
//        return admin;
//    }

}
