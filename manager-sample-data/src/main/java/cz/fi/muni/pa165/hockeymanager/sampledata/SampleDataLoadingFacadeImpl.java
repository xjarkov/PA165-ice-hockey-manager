package cz.fi.muni.pa165.hockeymanager.sampledata;

import cz.fi.muni.pa165.hockeymanager.entity.HockeyPlayer;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import cz.fi.muni.pa165.hockeymanager.entity.Team;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.enums.Championship;
import cz.fi.muni.pa165.hockeymanager.enums.Role;
import cz.fi.muni.pa165.hockeymanager.service.HockeyPlayerService;
import cz.fi.muni.pa165.hockeymanager.service.MatchService;
import cz.fi.muni.pa165.hockeymanager.service.TeamService;
import cz.fi.muni.pa165.hockeymanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private HockeyPlayerService hockeyPlayerService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private TeamService teamService;

    @Override
    @SuppressWarnings("unused")
    public void loadSampleData() {


        Team cska = team("CSKA Moscow", Championship.KHL);
        Team ska = team("SKA Petersburg", Championship.KHL);

        User pepa = user("Pepa Novák","pepa@novak.cz", "heslo123", Role.PLAYER, cska);
        User honza = user("Honza Novák","honza@novak.cz", "heslo321", Role.PLAYER, ska);
        User admin = user("Fero Novák","fero@novak.cz", "heslo654", Role.ADMIN, null);

        Match m1 = match(cska, ska, LocalDateTime.of(2021, Month.JANUARY, 5, 19, 0, 0));
        Match m2 = match(cska, ska, LocalDateTime.of(2021, Month.JANUARY, 6, 19, 0, 0));
        Match m3 = match(ska, cska, LocalDateTime.of(2021, Month.JANUARY, 15, 19, 0, 0));
        Match m4 = match(ska, cska, LocalDateTime.of(2021, Month.JANUARY, 16, 19, 0, 0));

        HockeyPlayer p1 = hockeyPlayer("Alexander", "Oveckin", cska, 95, 93);
        HockeyPlayer p2 = hockeyPlayer("Jevgeni", "Malkin", cska, 94, 90);
        HockeyPlayer p3 = hockeyPlayer("Andrej", "Vasilevskij", cska, 50, 99);
        HockeyPlayer p4 = hockeyPlayer("Fero", "Mrkvička", ska, 98, 99);
        HockeyPlayer p5 = hockeyPlayer("Jozef", "Hruška", cska, 87, 78);
        HockeyPlayer p6 = hockeyPlayer("Vladimír", "Putin", cska, 99, 99);
    }

    private Team team(String name, Championship championship) {
        Team t = new Team();
        t.setName(name);
        t.setChampionship(championship);
        teamService.create(t);

        return t;
    }

    private User user(String name, String email, String password, Role role, Team team) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setTeam(team);
        u.setRole(role);
        userService.create(u);

        return u;
    }

    private Match match(Team homeTeam, Team visitingTeam, LocalDateTime dateTime) {
        Match m = new Match(homeTeam, visitingTeam, dateTime);
        matchService.create(m);

        return m;
    }

    private HockeyPlayer hockeyPlayer(String firstName, String lastName, Team team, Integer off, Integer def) {
        HockeyPlayer p = new HockeyPlayer();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setTeam(team);
        p.setOffensiveStrength(off);
        p.setDefensiveStrength(def);
        hockeyPlayerService.create(p);

        return p;
    }

}