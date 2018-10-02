package gov.jiusan.star.org;

import gov.jiusan.star.org.model.OrgDTO;

/**
 * @author Marcus Lin
 */
public class OrgUtil {

    public static OrgDTO convert(Org org) {
        OrgDTO dto = new OrgDTO();
        dto.setCode(org.getCode());
        dto.setName(org.getName());
        return dto;
    }
}
