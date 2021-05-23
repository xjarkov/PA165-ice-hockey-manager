package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;
import cz.fi.muni.pa165.hockeymanager.facade.UserFacade;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Petr Šopf (506511)
 */
@Controller
@RequestMapping("/auth")
public class LoginController {
    @Inject
    private UserFacade userFacade;

    @GetMapping(value = "login")
    public String getLogin(Model model, HttpSession session) {
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
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        UserDto userDTO = userFacade.findUserByEmail(user.getEmail());
        if (userDTO == null) {
            redirectAttributes.addFlashAttribute("login_failure", "User with this email does not exist");
            return "redirect:/auth/login";
        }

        if (!userFacade.authenticate(user)) {
            redirectAttributes.addFlashAttribute("login_failure", "Invalid password");
            return "redirect:/auth/login";
        }

        session.setAttribute("authenticatedUser", userDTO);
        if (user.getTeam() == null) {
            return "redirect:/team/list";
        }
        return "redirect:/";
    }
}
