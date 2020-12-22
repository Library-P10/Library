package com.api.library.controller;

import com.api.library.config.JwtTokenUtil;
import com.api.library.dto.CustomerDto;
import com.api.library.model.JwtRequest;
import com.api.library.model.JwtResponse;
import com.api.library.service.contract.CustomerService;
import com.api.library.service.contract.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//Exposez une API POST / authentifiez à l'aide de JwtAuthenticationController.
//        L'API POST obtient le nom d'utilisateur et le mot de passe dans le corps.
//        À l'aide du Spring Authentication Manager, nous authentifions le nom d'utilisateur et le mot de passe.
//        Si les informations d'identification sont valides,
//        un jeton JWT est créé à l'aide de JWTTokenUtil et est fourni au client.

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    // ----------------- Injections de dépendances ----------------- //

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private CustomerService customerService;

    // ---------------------------- //

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {

            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            throw new Exception("INVALID_CREDENTIALS", e);

        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("toto");
        }
    }

    @RequestMapping(value = "/customerInfo", method = RequestMethod.GET)
    public CustomerDto getCustomerInfo(HttpServletRequest httpServletRequest){

        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String jwtToken;

        jwtToken = requestTokenHeader.substring(7);

        CustomerDto customerDto = customerService.findCustomerByEmail(jwtTokenUtil.getUsernameFromToken(jwtToken));

        return customerDto;
    }
}
