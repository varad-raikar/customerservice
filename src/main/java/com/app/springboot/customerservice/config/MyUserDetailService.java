package com.app.springboot.customerservice.config;

import com.app.springboot.customerservice.entity.Users;
import com.app.springboot.customerservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("not found " + userName));
        return new MyUserDetails(user.get());
    }
}
