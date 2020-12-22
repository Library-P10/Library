package com.api.library.controller;

import com.api.library.dto.AuthenticationDto;
import com.api.library.dto.CustomerDto;
import com.api.library.mapper.CustomerMapper;
import com.api.library.service.contract.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    // ----------------- Injections de dépendances ----------------- //
    @Autowired
    private CustomerService customerService;

    // -----------------------------------------------------  //

    @PostMapping("login")
    public CustomerDto login(@RequestBody CustomerDto customerDto){

        return customerDto;
    }

    /**
     *
     * @param email
     * @return l'utilisateur si il existe sinon null
     */
    @PostMapping("user")
    public CustomerDto getCustomerByEmail(@RequestParam(name = "email") String email){

        CustomerDto customerDto = customerService.findCustomerByEmail(email);

        return customerDto;
    }

    /**
     * Ajout un utilisateur en base
     * @param customerDto
     * @return l'utilisateur rajouter
     */
    @PostMapping("users")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto){

        return customerService.saveCustomer(customerDto);
    }

    /**
     * Récupérer l'utilisateur selon son id
     * @param idCustomer
     * @return
     */
    @GetMapping(value = "user/{idCustomer}")
    public CustomerDto getCustomerById(@PathVariable("idCustomer") Long idCustomer){
        return customerService.findCustomerById(idCustomer);
    }

    /**
     * Modifie le profil utilisateur
     * @param customerDto
     * @return
     */
    @PostMapping("updateCustomer")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto){

        return customerService.updateCustomer(customerDto);
    }

    /**
     * Modifie le mot de passe utilisateur
     * @param idCustomer
     * @param password
     */
    @PostMapping("updatePassword")
    public void updatePassword(@RequestParam(name = "idCustomer") Long idCustomer,
                               @RequestParam(name = "password") String password){

        customerService.updatePassword(idCustomer, password);
    }
}
