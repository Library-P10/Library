package com.api.library.service.contract;

import com.api.library.dto.AuthenticationDto;
import com.api.library.dto.CustomerDto;

public interface CustomerService {

    // Inscrit un nouvel utilisateur
    CustomerDto saveCustomer(CustomerDto customerDto);
    // Retourne l'utilisateur
    CustomerDto findCustomerByEmail(String email);
    // Récupère l'utilisateur par son id
    CustomerDto findCustomerById(Long idCustomer);
    // Update le profil de l'utilisateur
    CustomerDto updateCustomer(CustomerDto customerDto);
    //Update le mot de passe de l'utilisateur
    void updatePassword(Long idCustomer, String password);
}
