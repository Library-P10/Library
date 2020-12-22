package com.api.library.controller;

import com.api.library.config.JwtTokenUtil;
import com.api.library.dto.CustomerDto;
import com.api.library.dto.EmpruntDto;
import com.api.library.service.contract.CustomerService;
import com.api.library.service.contract.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class EmpruntController {

    // ----------------- Injections de dépendances ----------------- //
    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // -----------------------------------------------------  //

    /**
     * Récupère la liste des prêts de l'utilisateur
     * @param id
     * @return
     */
    @GetMapping(value = "emprunts/{id}")
    public List<EmpruntDto> displayEmpruntByCustomer(@PathVariable("id") Long id){

        return empruntService.getEmpruntByIdCustomer(id);
    }

    /**
     * Ajoute un prêt
     * @param idBook
     * @param format
     * @param nameLibrary
     * @return
     */
    @PostMapping(value = "emprunt/add/{idBook}")
    public EmpruntDto createEmprunt(@PathVariable("idBook") Long idBook,
                                    @RequestParam(name = "format") String format,
                                    @RequestParam(name = "nameLibrary") String nameLibrary,
                                    HttpServletRequest httpServletRequest){

        final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        String jwtToken;

        jwtToken = requestTokenHeader.substring(7);

        CustomerDto customerDto = customerService.findCustomerByEmail(jwtTokenUtil.getUsernameFromToken(jwtToken));

        Long idCustomer = customerDto.getId();

        return empruntService.addEmprunt(idBook, format, nameLibrary, idCustomer);
    }

    /**
     * Prolonge le pret
     * @param httpSession
     * @param idEmprunt
     */
    @GetMapping(value = "emprunt/extended/{idEmprunt}")
    public void extendLoan(HttpSession httpSession,
                    @PathVariable("idEmprunt") Long idEmprunt ){
        empruntService.extendLoan(idEmprunt);
    }

    /**
     * Supprime un prêt après rendu du livre (remet disponible l'exemplaire)
     * @param idEmprunt
     */
    @GetMapping(value = "emprunt/delete/{idEmprunt}")
    public void deleteEmprunt(@PathVariable(name = "idEmprunt") Long idEmprunt){
        empruntService.deleteEmprunt(idEmprunt);
    }

    /**
     * Récupère les prêts expirés
     */
    @GetMapping(value = "empruntDelay")
    public List<EmpruntDto> getEmpruntExpiredLoanDate(){
        return empruntService.getEmpruntExpiredLoanDate();
    }
}
