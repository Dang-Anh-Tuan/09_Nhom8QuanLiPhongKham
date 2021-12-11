package qlpk.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import qlpk.entity.User;
import qlpk.repo.UserRepo;


public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = repo.findByUserName(userName);
        if(user==null){
            throw new UsernameNotFoundException(userName);
        }
        return new CustomUserDetails(user);
    }
}
