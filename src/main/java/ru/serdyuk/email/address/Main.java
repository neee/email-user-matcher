package ru.serdyuk.email.address;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.serdyuk.email.address.matchers.EmailAddressMatcher;
import ru.serdyuk.email.address.matchers.Matcher;

/**
 * Created by Sergey Serdyuk on 09/12/2018.
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String inputData = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru\n"
                + "user2 -> foo@gmail.com, ups@pisem.net\n"
                + "user3 -> xyz@pisem.net, vasya@pupkin.com\n"
                + "user4 -> ups@pisem.net, aaa@bbb.ru\n"
                + "user5 -> xyz@pisem.net";

        Scanner scanner = new Scanner(inputData);
        Matcher emailAddressMatcher = new EmailAddressMatcher();
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            log.debug("input: {}", input);
            emailAddressMatcher.match(input);
        }
        log.info("result: {}", emailAddressMatcher.getResult());
    }
}