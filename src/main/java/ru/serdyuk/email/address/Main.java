package ru.serdyuk.email.address;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.serdyuk.email.address.matchers.EmailAddressMatcher;
import ru.serdyuk.email.address.matchers.Matcher;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by Sergey Serdyuk on 09/12/2018.
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите список адерсов пользователей в формате: 'user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru'\nПосле ввода нажмите enter\n"
                + "\nДля примера можно скопировать данные из задачи:"
                + "\nuser1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru"
                + "\nuser2 -> foo@gmail.com, ups@pisem.net"
                + "\nuser3 -> xyz@pisem.net, vasya@pupkin.com"
                + "\nuser4 -> ups@pisem.net, aaa@bbb.ru"
                + "\nuser5 -> xyz@pisem.net");
        Matcher emailAddressMatcher = new EmailAddressMatcher();
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (isNullOrEmpty(input)) {
                break;
            }
            log.debug("input: {}", input);
            emailAddressMatcher.match(input);
            System.out.println("Введите новые данные, для заверешения нажмите enter еще раз");
        }
        log.info("result: {}", emailAddressMatcher.getResult());
    }
}