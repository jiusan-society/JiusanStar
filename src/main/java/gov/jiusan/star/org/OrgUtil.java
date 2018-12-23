package gov.jiusan.star.org;

import gov.jiusan.star.org.model.OrgDTO;

/**
 * @author Marcus Lin
 */
public class OrgUtil {

    public static OrgDTO convert(Org org) {
        OrgDTO dto = new OrgDTO();
        dto.setSeq(org.getSeq());
        dto.setCode(org.getCode());
        dto.setName(org.getName());
        if (org.getFiles() != null && !org.getFiles().isEmpty()) {
            dto.setFileExisted(true);
        }
        return dto;
    }
}
