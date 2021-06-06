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

import java.time.Month;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kristian Kosorin (456620)
 */
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

    private final PasswordEncoder encoder = new Argon2PasswordEncoder();

    @Override
    @SuppressWarnings("unused")
    public void loadSampleData() {
        Team cska = team("CSKA Moscow", Championship.KHL);
        Team ska = team("SKA Petersburg", Championship.KHL);
        Team tps = team("TPS Turku", Championship.LIIGA);
        Team lukko = team("Lukko", Championship.LIIGA);

        // Do not encode here... it is done in user service
        User adminWithoutTeam = user("admin", "admin@muni.cz", "admin123", Role.ADMIN, null);
        User userWithoutTeam = user("user", "user@muni.cz", "user123", Role.PLAYER, null);
        User adminWithTeam = user("adminTeam", "adminTeam@muni.cz", "adminTeam123", Role.ADMIN, cska);
        User userWithTeam = user("userTeam", "userTeam@muni.cz", "userTeam123", Role.PLAYER, ska);

        Match m1 = match(cska, ska, LocalDateTime.of(2021, Month.FEBRUARY, 5, 19, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),1,2);
        Match m2 = match(cska, ska, LocalDateTime.of(2021, Month.JANUARY, 6, 19, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),0,4);
        Match m3 = match(ska, cska, LocalDateTime.of(2021, Month.JANUARY, 15, 19, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),5,2);
        Match m4 = match(ska, cska, LocalDateTime.of(2021, Month.JANUARY, 16, 19, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),3,3);

        HockeyPlayer p1 = hockeyPlayer("Alexander", "Oveckin", null, 95, 93);
        HockeyPlayer p2 = hockeyPlayer("Jevgeni", "Malkin", null, 94, 90);
        HockeyPlayer p3 = hockeyPlayer("Andrej", "Vasilevskij", null, 50, 99);
        HockeyPlayer p4 = hockeyPlayer("Fero", "Mrkvička", null, 98, 99);
        HockeyPlayer p5 = hockeyPlayer("Jozef", "Hruška", null, 87, 78);
        HockeyPlayer p6 = hockeyPlayer("Vladimír", "Putin", null, 99, 99);
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
        u = userService.create(u);
        if (team != null) {
            team.setManager(u);
        }

        return u;
    }

    private Match match(Team homeTeam, Team visitingTeam, Long dateTime, int homeScore, int visitingScore) {
        Match m = new Match(homeTeam, visitingTeam, dateTime);
        m.setHomeTeamScore(homeScore);
        m.setVisitingTeamScore(visitingScore);
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
