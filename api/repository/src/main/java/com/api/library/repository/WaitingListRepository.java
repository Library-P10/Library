package com.api.library.repository;

import com.api.library.model.Book;
import com.api.library.model.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

    // Récupère la liste d'attente d'un book
    @Query("SELECT w FROM WaitingList w WHERE w.book.id = :idBook ORDER BY w.dateRequest asc ")
    List<WaitingList> getWaitingListByIdBookByDateRequest(@Param("idBook") Long idbook);

    // Récupère le nombre de personne dans la liste d'attente
    @Query("SELECT COUNT (w.customer.id) FROM WaitingList w WHERE w.book.id = :idBook")
    int getNumberCustomerInWaitingList(@Param("idBook") Long idBook);

    // Récupère le nombre d'occurence d'un livre dans la liste d'attente
    @Query("SELECT COUNT (w.book.id) AS numberOfBook FROM WaitingList w WHERE w.book.id = :idBook")
    Integer getNumberBookInWaitingList(@Param("idBook") Long idBook);

    // Récupération du premier à être sur la liste d'attente
    @Query("SELECT w FROM WaitingList w WHERE w.book.id = :idBook ORDER BY w.dateRequest asc")
    WaitingList findFirstByIdBook(@Param("idBook") Long idBook);

    // Change les données du premier à être sur la liste d'attente
    @Transactional
    @Modifying
    @Query("UPDATE WaitingList w SET w.dateSendingMail = :date, w.dateRecoveryLimit = :dateRecoveryLimit " +
            "WHERE w.id = :idWaitingList")
    void updateWaitingListAfterSendMail(@Param("idWaitingList") Long idWaitingList,
                                        @Param("date") Date date,
                                        @Param("dateRecoveryLimit") Date dateRecoveryLimit);

    // Récupère toutes les réservation dont la date de recovery Limit est dépassée
    @Query("SELECT w FROM WaitingList w WHERE w.dateRecoveryLimit <= CURRENT_TIMESTAMP ")
    List<WaitingList> getWaitingListByDateRecoveryLimitExceeded();

    // Récupère la réservation par son id
    @Query("SELECT w FROM WaitingList w WHERE w.id =:idWaitingList")
    WaitingList getWaitingListById(@Param("idWaitingList") Long idWaitingList);

    // Récupère la liste d'attente si elle existe selon l'utilisateur et le livre
    @Query("SELECT w FROM WaitingList w WHERE (w.customer.id = :idCustomer) AND (w.book.id = :idBook)")
    WaitingList getWaitingListByIdCustomerAndIdBook(@Param("idCustomer") Long idCustomer,
                                                    @Param("idBook") Long idBook);

    @Query("SELECT w FROM WaitingList w WHERE w.customer.id = :idCustomer")
    List<WaitingList> getWaitingListByIdCustomer(@Param("idCustomer") Long idCustomer);

    // Récupère la position dans la liste d'attente de l'utilisateur selon l'id
    @Query("SELECT COUNT (w.customer.id) FROM WaitingList w WHERE (w.book.id = :idBook) AND (w.dateRequest < :dateRequest)" +
            "ORDER BY w.dateRequest ASC ")
    int getNumberInWaitingList(@Param("idBook") Long idBook,
                               @Param("dateRequest") Date dateRequest);
}
