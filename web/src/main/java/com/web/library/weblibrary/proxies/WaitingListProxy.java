package com.web.library.weblibrary.proxies;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "library", url = "localhost:8181")
public interface WaitingListProxy {

    @GetMapping(value = "/waitingList/available")
    Boolean insertAvailable (@RequestParam("idBook") Long idBook,
                             @RequestParam("idCustomer") Long idCustomer);
}
