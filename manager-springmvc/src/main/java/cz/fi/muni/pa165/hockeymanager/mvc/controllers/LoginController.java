package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;


import org.slf4j.Logger;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Petr Šopf (506511)
 */
@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserFacade userFacade;

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "login")
    public String getLogin(Model model, HttpSession session) {
        logger.info("Login page - GET");
        if (session.getAttribute("authenticatedUser") != null) {
            return "redirect:/";
        }

        model.addAttribute("user", new UserDto());
        return "/auth/login";
    }

    @PostMapping(value = "login")
    public String postLogin(Model model, HttpSession session,
                            @Valid @ModelAttribute("user") UserDto user,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        logger.info("Login page - POST");
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        UserDto userDTO = userFacade.findUserByEmail(user.getEmail());
        if (userDTO == null) {
            redirectAttributes.addFlashAttribute("login_failure", "User with this email does not exist");
            logger.info("Login page - POST - User with this email does not exist");
            return "redirect:/auth/login";
        }

        if (!userFacade.authenticate(user)) {
            redirectAttributes.addFlashAttribute("login_failure", "Invalid password");
            logger.info("Login page - POST - Invalid password");
            return "redirect:/auth/login";
        }

        session.setAttribute("authenticatedUser", userDTO);
        logger.info("Login page - POST - Login successful");
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        logger.info("Logout page - GET");
        if (session.getAttribute("authenticatedUser") == null) {
            return "redirect:/";
        }

        session.removeAttribute("authenticatedUser");
        logger.info("Logout page - GET - logout successful");
        return "redirect:/auth/login";
    }
}
