package com.api.library.repository;

import com.api.library.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt,Long> {

    // Prêt de l'utilisateur en session ( exemplaire, le nom du livre et l'auteur )
    @Query("SELECT e FROM Emprunt e WHERE e.customer.id = :id")
    List<Emprunt> getEmpruntByIdCustomer(@Param("id") Long id);

    // Récupère un prêt selon son id
    @Query("SELECT e FROM Emprunt e WHERE e.id = :idEmprunt")
    Emprunt getEmpruntById(@Param("idEmprunt")Long idEmprunt);

    // Récupère les prêts expirés
    @Query("SELECT e FROM Emprunt e WHERE e.returnDate <= CURRENT_DATE ")
    List<Emprunt> getEmpruntExpiredLoanDate();

    // Récupère un prêt selon son idCopy
    @Query("SELECT e FROM Emprunt e WHERE e.copy.id = :idCopy")
    Emprunt getEmpruntByIdCopy(@Param("idCopy")Long idCopy);

    // Récupère l'emprunt selon l'id customer et id Book
    @Query("SELECT e FROM Emprunt e WHERE (e.customer.id = :idCustomer) AND (e.copy.book.id = :idBook)")
    Emprunt getEmpruntByIdCustomerByIdBook(@Param("idCustomer") Long idCustomer,
                                              @Param("idBook") Long idBook);

    // Récupère l'emprunt du prochain retour d'un livre
    Emprunt findFirstByCopy_Book_IdOrderByReturnDateAsc(@Param("idBook") Long idBook);
}
