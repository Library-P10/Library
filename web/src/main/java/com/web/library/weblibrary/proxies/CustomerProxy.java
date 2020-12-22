package com.web.library.weblibrary.proxies;

import com.web.library.weblibrary.beans.AuthenticationCustomer;
import com.web.library.weblibrary.beans.Customer;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "library", url = "localhost:8181")
public interface CustomerProxy {

    @PostMapping(value = "/user")
    Customer getCustomerByEmail(@RequestParam(name = "email") String email);

    @PostMapping(value = "/users")
    Customer createCustomer(@ModelAttribute("user") Customer customer);

    @PostMapping(value = "/authenticate")
    ResponseEntity<?> validationAuthentication(@ModelAttribute("authentication") AuthenticationCustomer authenticationCustomer) throws FeignException.Unauthorized;

    @GetMapping(value = "/customerInfo")
    Customer getInfoCustomer(@RequestHeader(name = "Authorization") String headerAuthorization);

    @GetMapping(value = "/user/{idCustomer}")
    Customer getCustomerById(@PathVariable("idCustomer") Long idCustomer);

    @PostMapping(value = "/updateCustomer")
    Customer updateCustomer(@ModelAttribute("customer") Customer customer);

    @PostMapping(value = "/updatePassword")
    void updatePassword(@RequestParam(name = "idCustomer") Long idCustomer,
                        @RequestParam(name = "password") String password);
}
