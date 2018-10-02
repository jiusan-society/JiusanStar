package gov.jiusan.star.org.model;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;

/**
 * @author Marcus Lin
 */
public class OrgDTO implements Comparable<OrgDTO>, Serializable {

    private String code;
    private String name;

    public OrgDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(OrgDTO o) {
        Collator collator = Collator.getInstance(Locale.CHINA);
        return collator.compare(this.name, o.getName());
    }
}
