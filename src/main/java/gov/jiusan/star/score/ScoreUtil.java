package gov.jiusan.star.score;

import com.fasterxml.jackson.core.type.TypeReference;
import gov.jiusan.star.score.model.ScoreDTO;
import gov.jiusan.star.util.JacksonUtil;

import java.util.Map;

public class ScoreUtil {

    /**
     * 评级：不可用
     */
    public static final Integer RANK_NOT_AVAILABLE = -1;
    /**
     * 评级：不合格
     */
    public static final Integer RANK_UNQUALIFIED = 1;
    /**
     * 评级：合格
     */
    public static final Integer RANK_QUALIFIED = 2;
    /**
     * 评级：三星级
     */
    public static final Integer RANK_LV_3 = 3;
    /**
     * 评级：四星级
     */
    public static final Integer RANK_LV_4 = 4;
    /**
     * 评级：五星级
     */
    public static final Integer RANK_LV_5 = 5;

    static ScoreDTO convert(Score score) {
        ScoreDTO dto = new ScoreDTO();
        dto.setSeq(score.getSeq());
        dto.setOrgName(score.getOrg().getName());
        Map<Long, Integer> sADetails = JacksonUtil.toObj(score.getsADetails(), new TypeReference<Map<Long, Integer>>() {
        }).get();
        dto.setsADetails(sADetails);
        dto.setsATotalScore(score.getsATotalScore());
        dto.setsAFinished(score.issAFinished());
        Map<Long, Integer> aADetails = JacksonUtil.toObj(score.getaADetails(), new TypeReference<Map<Long, Integer>>() {
        }).get();
        dto.setaADetails(aADetails);
        dto.setaATotalScore(score.getaATotalScore());
        dto.setaAFinished(score.isaAFinished());
        if (isFinished(score)) {
            dto.setFinalScore(score.getFinalScore());
            dto.setRank(computeRank(score));
            dto.setFinished(true);
        }
        return dto;
    }

    /**
     * 判断 评分 是否最终完成（即自评、考评都完成）
     *
     * @param score
     * @return
     */
    public static boolean isFinished(Score score) {
        return score.issAFinished() && score.isaAFinished();
    }

    /**
     * 计算评分等级
     *
     * @param score
     * @return
     */
    public static int computeRank(Score score) {
        int rank = RANK_NOT_AVAILABLE;
        if (score.getFinalScore() != null) {
            double s = score.getFinalScore();
            if (s < 60) {
                rank = RANK_UNQUALIFIED;
            } else if (s < 70) {
                rank = RANK_QUALIFIED;
            } else if (s < 80) {
                rank = RANK_LV_3;
            } else if (s < 90) {
                rank = RANK_LV_4;
            } else {
                rank = RANK_LV_5;
            }
        }
        return rank;
    }
}
