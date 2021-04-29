package utils;

import cz.fi.muni.pa165.hockeymanager.utils.ScoreTuple;

import java.util.Objects;

public class ScoreTupleImpl implements ScoreTuple {
    public final int homeScore;
    public final int visitingScore;

    public ScoreTupleImpl(int homeScore, int visitingScore) {
        this.homeScore = homeScore;
        this.visitingScore = visitingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreTupleImpl that = (ScoreTupleImpl) o;
        return homeScore == that.homeScore && visitingScore == that.visitingScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeScore, visitingScore);
    }
}
