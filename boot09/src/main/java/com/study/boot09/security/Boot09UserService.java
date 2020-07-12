package com.study.boot09.security;

import com.study.boot09.persistence.MemberRepositroy;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log
public class Boot09UserService implements UserDetailsService {
    @Autowired
    MemberRepositroy repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return
                repo.findById(username).filter(m-> m != null)
                .map(m->new com.study.boot09.security.Boot09SecurityUser(m)).get();
    }
}
