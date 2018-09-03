package gov.jiusan.star.score;

import com.fasterxml.jackson.core.type.TypeReference;
import gov.jiusan.star.score.model.ScoreDTO;
import gov.jiusan.star.util.JacksonUtil;

import java.util.Map;

public class ScoreUtil {

    static ScoreDTO convert(Score score) {
        ScoreDTO dto = new ScoreDTO();
        if (!(score.getsADetails() == null || score.getsADetails().isEmpty())) {
            Map<Long, Integer> sADetails = JacksonUtil.toObj(score.getsADetails(), new TypeReference<Map<Long, Integer>>() {
            }).get();
            dto.setsADetails(sADetails);
        }
        if (!(score.getaADetails() == null || score.getaADetails().isEmpty())) {
            Map<Long, Integer> aADetails = JacksonUtil.toObj(score.getaADetails(), new TypeReference<Map<Long, Integer>>() {
            }).get();
            dto.setaADetails(aADetails);
        }
        return dto;
    }
}
