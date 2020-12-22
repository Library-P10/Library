package com.web.library.weblibrary.controller;

import com.web.library.weblibrary.beans.AuthenticationCustomer;
import com.web.library.weblibrary.beans.Customer;
import com.web.library.weblibrary.proxies.CustomerProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class CustomerController {

    // ----- Injections des dépendances ----- //
    @Autowired
    private CustomerProxy customerProxy;

    // ---------- //

    /**
     *
     * @param model
     * @return la page d'inscription utilisateur
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(Model model){

        model.addAttribute("customer", new Customer());

        return new ModelAndView("registrationUser");
    }

    /**
     * Inscription d'un utilisateur
     * @param newCustomer
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
        public String createUser(@ModelAttribute("customer") Customer newCustomer,
                                 @RequestParam(name = "confirmPassword", required = false) String confirmPassword,
                                 Model model){

        Map<String, String> messageErreur= null;
        Customer customer = customerProxy.getCustomerByEmail(newCustomer.getEmail());

        if (!newCustomer.getPassword().equals(confirmPassword)) {

            messageErreur.put("erreurConfirmPassword", "Les mots de passe ne sont pas identique");

            if (customer != null) {
                messageErreur.put("erreurMail","Le mail est déjà utilisé");
            }
            model.addAttribute("erreurMessages", messageErreur);

            return "registration";
        }
        customerProxy.createCustomer(newCustomer);

        return "redirect:/books";
    }

    /**
     *
     * @return la page d'authentification
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    public ModelAndView authentificationUser(Model model){

        model.addAttribute("authenticationCustomer", new AuthenticationCustomer());

        return new ModelAndView("authentication");
    }

    /**
     *
     * @param authenticationCustomer
     * @return la validité de l'authentification
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public String authenticationCustomer(@ModelAttribute("authenticationCustomer")AuthenticationCustomer authenticationCustomer,
                                         HttpSession httpSession,
                                         HttpServletResponse response,
                                         Model model){
        try {
            ResponseEntity<?> responseEntity = customerProxy.validationAuthentication(authenticationCustomer);

            Cookie cookie = new Cookie("Token", ((Map<String, String>) responseEntity.getBody()).get("token"));
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            Customer customer = customerProxy.getCustomerByEmail(authenticationCustomer.getUsername());
            httpSession.setAttribute("customer", customer);

            return "redirect:/books";
        } catch (FeignException.Unauthorized unauthorized){

            model.addAttribute("erreur", "L'utilisateur ou le mot de passe n'existe pas");

            return "authentication";
        }

    }

    /**
     *
     * @return la page de profil
     */
    @RequestMapping(value = "/profil/{idSession}", method = RequestMethod.GET)
    public String displayProfil(@PathVariable("idSession") Long idSession,
                                      HttpSession httpSession,
                                      Model model){

        model.addAttribute(httpSession.getAttribute("customer"));

        return "profil";
    }

    /**
     * Affiche la page de modification du profil
     * @param idCustomer
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateCustomer/{idCustomer}",method = RequestMethod.GET)
    public String dsiplayUpdateCustomer(@PathVariable("idCustomer") Long idCustomer,
                                 Model model){

        model.addAttribute("customer", customerProxy.getCustomerById(idCustomer));

        return "updateCustomer";
    }

    /**
     * Modifie le profil de l'utilisateur
     * @param customer
     * @return
     */
    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public String updateCustomer(@ModelAttribute("customer") Customer customer,
                                 HttpSession httpSession,
                                 Model model){

        customerProxy.updateCustomer(customer);
        httpSession.setAttribute("customer", customer);
        model.addAttribute(httpSession.getAttribute("customer"));

        return "profil";
    }

    /**
     * On affiche la page de modification de mot de passe
     * @param idCustomer
     * @return
     */
    @RequestMapping(value = "/updatePassword/{idCustomer}", method = RequestMethod.GET)
    public String updatePassword(@PathVariable("idCustomer") Long idCustomer,
                                 HttpSession httpSession,
                                 Model model){

        customerProxy.getCustomerById(idCustomer);

        model.addAttribute("customer", customerProxy.getCustomerById(idCustomer));

        return "updatePassword";
    }

    /**
     * Modifie le mot de passe de l'utilisateur
     * @param password
     * @param idCustomer
     * @param confirmPassword
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public String updatePassword(@RequestParam(name = "password") String password,
                                 @RequestParam(name = "idCustomer") Long idCustomer,
                                 @RequestParam(name = "confirmPassword") String confirmPassword,
                                 HttpSession httpSession,
                                 Model model){

        model.addAttribute("customer", customerProxy.getCustomerById(idCustomer));

        if (!password.equals(confirmPassword)){
            model.addAttribute("erreur", "Les mots de passe ne sont pas identiques");
            model.addAttribute("customer", customerProxy.getCustomerById(idCustomer));
            return "updatePassword";
        }

        customerProxy.updatePassword(idCustomer, password);


        return "/profil";
    }

    /**
     * Déconnexion de l'utilisateur
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    public void disconnect(HttpSession httpSession,
                             HttpServletResponse response){

        httpSession.removeAttribute("customer");
        httpSession.invalidate();

        Cookie cookie = new Cookie("Token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        try {
            response.sendRedirect("/books");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
