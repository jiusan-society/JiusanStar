package gov.jiusan.star.util;

import org.junit.Test;

public class PasswordUtilTest {

    @Test
    public void testEncrypt() throws Exception {
        String password = "linfai88";
        String hash = PasswordUtil.createHash(password);
        System.out.println(hash);
        System.out.println(PasswordUtil.verifyPassword(password, hash));
    }

}