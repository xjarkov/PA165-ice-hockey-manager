package cz.fi.muni.pa165.hockeymanager.service.facade;

import cz.fi.muni.pa165.hockeymanager.dto.HockeyPlayerDto;
import cz.fi.muni.pa165.hockeymanager.facade.HockeyPlayerFacade;
import cz.fi.muni.pa165.hockeymanager.service.HockeyPlayerService;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HockeyPlayerFacadeImpl implements HockeyPlayerFacade {

    final static Logger log = LoggerFactory.getLogger(HockeyPlayerFacadeImpl.class);

    @Inject
    private HockeyPlayerService hockeyPlayerService;

    @Override
    public long createHockeyPlayer(HockeyPlayerDto hockeyPlayerDto) {
        return 0;
    }

    @Override
    public void removeHockeyPlayer(long hockeyPlayerId) {

    }

    @Override
    public List<HockeyPlayerDto> getAllHockeyPlayers() {
        return null;
    }
}
