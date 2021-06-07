package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dto.MatchCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

public interface BeanMappingService {
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    <T> T mapTo(Object u, Class<T> mapToClass);
    Mapper getMapper();
  
//    List<MatchDto> mapMatchesToMatchDtos(Collection<Match> matches);
//    MatchDto mapMatchToMatchDto(Match match);
//
//    List<MatchCreateDto> mapMatchesToMatchCreateDtos(Collection<Match> matches);
//    MatchCreateDto mapMatchToMatchCreateDto(Match match);
}
