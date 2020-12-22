package com.web.library.weblibrary.proxies;

import com.web.library.weblibrary.beans.Copy;
import com.web.library.weblibrary.beans.CopyByBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "library", url = "localhost:8181")
public interface CopyProxy {

    @GetMapping(value = "/copies/{idBook}")
    List<CopyByBook> getCopyByIdBook(@PathVariable("idBook")Long id);

    @GetMapping(value="/copy/{idCopy}")
    Copy getCopyById(@PathVariable("idCopy") Long idCopy);
}
