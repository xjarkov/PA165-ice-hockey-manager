package cz.fi.muni.pa165.hockeymanager.mvc.controllers;

import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.dto.TeamDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.facade.MatchFacade;
import cz.fi.muni.pa165.hockeymanager.facade.TeamFacade;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchFacade matchFacade;

    @GetMapping("/list")
    public String list(Model model) {
        List<MatchDto> matches = matchFacade.findAllMatches();
        Collections.sort(matches, new Comparator<MatchDto>() {
            public int compare(MatchDto o1, MatchDto o2) {
                return o1.getDateTimeDto().compareTo(o2.getDateTimeDto());
            }
        });
        model.addAttribute("matches", matches);
        return "match/list";
    }
}
