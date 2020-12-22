package com.api.library.repository;

import com.api.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.email= :email")
    Customer findByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE c.id = :idSession")
    Customer findCustomerById(@Param("idSession")Long idSession);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.password= :password WHERE c.id =:idCustomer")
    void updatePassword(@Param("idCustomer") Long idCustomer,
                        @Param("password") String password);
}
