package com.web.library.weblibrary.proxies;

import com.web.library.weblibrary.beans.Categorie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "library", url = "localhost:8181")
public interface CategorieProxy {

    @GetMapping(value = "categorie")
    List<Categorie> getCategorie();

}
