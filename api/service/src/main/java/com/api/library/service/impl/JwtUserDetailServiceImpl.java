package com.api.library.service.impl;

import com.api.library.dto.CustomerDto;
import com.api.library.service.contract.CustomerService;
import com.api.library.service.contract.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailServiceImpl implements JwtUserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        CustomerDto customerDto = customerService.findCustomerByEmail(username);

        if ( (customerDto != null) && (customerDto.getEmail()).equals(username)){
            return new User(customerDto.getEmail(), customerDto.getPassword(), new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }
    }
}
