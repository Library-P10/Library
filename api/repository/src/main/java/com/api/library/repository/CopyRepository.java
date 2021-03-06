package com.api.library.repository;

import com.api.library.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

    // Récupérer les exemplaires disponible ( Number of Copy, format Name, Library Name )
    @Query("SELECT c.book, c.library, c.format, COUNT(c.id) AS numberCopy FROM Copy c WHERE (c.book.id = :id) AND (c.status = 'Disponible') GROUP BY c.book, c.library, c.format")
    List<Object[]> getCopyByIdBook(@Param("id") Long id);

    // Récupère l'exemplaire selon l'id
    @Query("SELECT c FROM Copy c WHERE c.id = :idCopy")
    Copy getCopyById(@Param("idCopy") Long idCopy);

    //Récupère un exemplaire selon le format et le nom de la library
    @Query("SELECT c FROM Copy c WHERE (c.format Like :format) AND (c.library.nom Like :nameLibrary) AND (c.book.id = :idBook) " +
            "AND ((c.status = 'Disponible') OR (c.status = 'Waiting'))")
    Copy findFirstByFormatAndLibrary_Nom(@Param("format") String format,
                                         @Param("nameLibrary") String nameLibrary,
                                         @Param("idBook")Long idBook);

    // Update status en Indisponible
    @Transactional
    @Modifying
    @Query("UPDATE Copy c SET c.status = 'Indisponible' WHERE c.id = :idCopy")
    void updateStatusUnavailable(@Param("idCopy")Long idCopy);

    // Update status en Disponible
    @Transactional
    @Modifying
    @Query("UPDATE Copy c SET c.status = 'Disponible' WHERE c.id = :idCopy")
    void updateStatusAvailable(@Param("idCopy")Long idCopy);

    // Update status en "En attente de récupération"
    @Transactional
    @Modifying
    @Query("UPDATE Copy c SET c.status = 'Waiting' WHERE c.id = :idCopy")
    void updateStatusWaitingList(@Param("idCopy")Long idCopy);

    // Récupération du nombre d'occurence de book exsitant dans les librairies
    @Query("SELECT COUNT(c.book.id) AS numberCopy FROM Copy c WHERE (c.book.id = :idBook)")
    Integer getNumberCopyByBook(@Param("idBook") Long idBook);

    // Récupère la copy selon son status
    @Query("SELECT c FROM Copy c WHERE (c.book.id = :idBook) AND (c.status= :status)")
    Copy getCopyByStatus(@Param("idBook") Long idBook,
                         @Param("status") String status);

    // Récupération du nombre de copy disponible
    @Query("SELECT COUNT (c.book.id) AS numberOfCopyAvailbale FROM Copy c WHERE (c.book.id = :idBook)" +
            "AND (c.status = 'Disponible')")
    int getNumberCopyByBookAvailable(@Param ("idBook") Long idBook);

}
