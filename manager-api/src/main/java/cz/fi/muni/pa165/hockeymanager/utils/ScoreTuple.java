package cz.fi.muni.pa165.hockeymanager.utils;

import lombok.Data;
import java.util.Objects;

@Data
public class ScoreTuple {
    public final int homeScore;
    public final int visitingScore;

    public ScoreTuple(int homeScore, int visitingScore) {
        this.homeScore = homeScore;
        this.visitingScore = visitingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreTuple that = (ScoreTuple) o;
        return homeScore == that.homeScore && visitingScore == that.visitingScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeScore, visitingScore);
    }
}
