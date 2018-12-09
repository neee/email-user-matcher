package ru.serdyuk.email.address.matchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sergey Serdyuk on 09/12/2018.
 */
public class EmailAddressMatcherTest {

    private final static String testDataUser1 = "user1 -> test1@ya.ru, test2@ya.ru, test3@ya.ru";
    private final static String testDataUser2 = "user2 -> test4@ya.ru, test5@ya.ru, test6@ya.ru";
    private final static String testDataUser3 = "user3 -> test4@ya.ru, test7@ya.ru, test8@ya.ru";
    private final static String testDataUser4 = "user4 -> test1@ya.ru, test2@ya.ru, test3@ya.ru";

    @Test
    public void successMatchAllDifferentEmails() {
        EmailAddressMatcher emailAddressMatcher = new EmailAddressMatcher();
        emailAddressMatcher.match(testDataUser1);
        emailAddressMatcher.match(testDataUser2);

        Map<String, Set<String>> expectedResult = new HashMap<>();
        expectedResult.put("user1", new HashSet<>(Arrays.asList("test1@ya.ru", "test2@ya.ru", "test3@ya.ru")));
        expectedResult.put("user2", new HashSet<>(Arrays.asList("test4@ya.ru", "test5@ya.ru", "test6@ya.ru")));

        Assert.assertEquals(expectedResult, emailAddressMatcher.getResult());
    }

    @Test
    public void successMatchAllEqualsEmails() {
        EmailAddressMatcher emailAddressMatcher = new EmailAddressMatcher();
        emailAddressMatcher.match(testDataUser1);
        emailAddressMatcher.match(testDataUser4);

        Map<String, Set<String>> expectedResult = new HashMap<>();
        expectedResult.put("user1", new HashSet<>(Arrays.asList("test1@ya.ru", "test2@ya.ru", "test3@ya.ru")));

        Assert.assertEquals(expectedResult, emailAddressMatcher.getResult());
    }

    @Test
    public void successMatchNotAllEqualsEmails() {
        EmailAddressMatcher emailAddressMatcher = new EmailAddressMatcher();
        emailAddressMatcher.match(testDataUser1);
        emailAddressMatcher.match(testDataUser2);
        emailAddressMatcher.match(testDataUser3);

        Map<String, Set<String>> expectedResult = new HashMap<>();
        expectedResult.put("user1", new HashSet<>(Arrays.asList("test1@ya.ru", "test2@ya.ru", "test3@ya.ru")));
        expectedResult.put("user2", new HashSet<>(Arrays.asList("test4@ya.ru", "test5@ya.ru", "test6@ya.ru", "test7@ya.ru", "test8@ya.ru")));

        Assert.assertEquals(expectedResult, emailAddressMatcher.getResult());
    }
}