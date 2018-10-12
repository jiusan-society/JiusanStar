package gov.jiusan.star.score;

import com.fasterxml.jackson.core.type.TypeReference;
import gov.jiusan.star.score.model.ScoreDTO;
import gov.jiusan.star.util.JacksonUtil;

import java.util.Map;

public class ScoreUtil {

    static ScoreDTO convert(Score score) {
        var dto = new ScoreDTO();
        var sADetails = JacksonUtil.toObj(score.getsADetails(), new TypeReference<Map<Long, Integer>>() {
        }).get();
        dto.setsADetails(sADetails);
        dto.setsATotalScore(score.getsATotalScore());
        var aADetails = JacksonUtil.toObj(score.getaADetails(), new TypeReference<Map<Long, Integer>>() {
        }).get();
        dto.setaADetails(aADetails);
        dto.setaATotalScore(score.getaATotalScore());
        dto.setFinalScore(score.getFinalScore());
        return dto;
    }

    public static boolean isFinished(Score score) {
        return score.issAFinished() && score.isaAFinished();
    }
}
