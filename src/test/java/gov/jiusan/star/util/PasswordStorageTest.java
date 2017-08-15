package gov.jiusan.star.util;

import org.junit.Test;

public class PasswordStorageTest {

    @Test
    public void testEncrypt() throws Exception {
        String password = "linfai88";
        String hash = PasswordStorage.createHash(password);
        System.out.println(hash);
        System.out.println(PasswordStorage.verifyPassword(password, hash));
    }

}