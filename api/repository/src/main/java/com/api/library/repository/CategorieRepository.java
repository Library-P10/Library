package com.api.library.repository;

import com.api.library.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    // Récupère toutes les catégories
    @Query("SELECT DISTINCT c FROM Categorie c")
    List<Categorie> getCategorie();
}
