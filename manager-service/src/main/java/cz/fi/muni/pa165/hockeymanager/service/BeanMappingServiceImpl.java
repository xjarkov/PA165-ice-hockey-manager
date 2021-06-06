package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dto.MatchCreateDto;
import cz.fi.muni.pa165.hockeymanager.dto.MatchDto;
import cz.fi.muni.pa165.hockeymanager.entity.Match;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }

    public MatchDto mapMatchToMatchDto(Match match) {
        MatchDto matchDto = dozer.map(match, MatchDto.class);
        LocalDateTime dt = match.getDateTime();
        matchDto.setDateTime(LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond()));
        return matchDto;
    }

    public List<MatchDto> mapMatchesToMatchDtos(Collection<Match> matches) {
        List<MatchDto> matchDtos = new ArrayList<>();
        for (var match : matches) {
            MatchDto matchDto = dozer.map(match, MatchDto.class);
            LocalDateTime dt = match.getDateTime();
            matchDto.setDateTime(LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond()));
            matchDtos.add(matchDto);
        }
        return matchDtos;
    }

    public MatchCreateDto mapMatchToMatchCreateDto(Match match) {
        MatchCreateDto matchCreateDto = dozer.map(match, MatchCreateDto.class);
        LocalDateTime dt = match.getDateTime();
        matchCreateDto.setDateTime(LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond()));
        return matchCreateDto;
    }

    public List<MatchCreateDto> mapMatchesToMatchCreateDtos(Collection<Match> matches) {
        List<MatchCreateDto> matchCreateDtos = new ArrayList<>();
        for (var match : matches) {
            MatchCreateDto matchCreateDto = dozer.map(match, MatchCreateDto.class);
            LocalDateTime dt = match.getDateTime();
            matchCreateDto.setDateTime(LocalDateTime.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond()));
            matchCreateDtos.add(matchCreateDto);
        }
        return matchCreateDtos;
    }
}
