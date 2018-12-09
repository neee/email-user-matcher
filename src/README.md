# The program matching users by emails

Для запуска программы понадибтся gradle

1. Склонируем проект git clone
2. Переходим в папку с проектом
```
cd email-user-matcher
```
3. Собираем проект:
```
./gradle build
```
4. Запускаем программу:
```
java -jar build/libs/email-user-matcher-1.0.jar
```
5. Передаем входные параметры
```
user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru
user2 -> foo@gmail.com, ups@pisem.net
user3 -> xyz@pisem.net, vasya@pupkin.com
user4 -> ups@pisem.net, aaa@bbb.ru
user5 -> xyz@pisem.net
```
