#### Поправити/Вивчити

* Специфіка статичних полів, як і коли їх використовувати. Позбудься таких стачних полів по аплікації
  * atm.json.AdminJson.file, atm.json.AdminJson.admin
  * atm.json.UserJson.file, atm.json.UserJson.users
  * private Integer atmBalance = adminJSON.atmBalance; - це взагалі жах, шо поле оголошене і сетатється деінде а тут просто пересетується.
  * atm.service.UserService, atm.service.AdminService не повинні містити ніяких локальних філдів. Можна обійтись без них. Коли йому щось треба можна викликати з інших класів.
  * Той firstMethod є нічим іншим ніж конструктором, тому і має бути конструктором.
* які то поля Final і як їх використовувати.
* Краще користуватись геттерами/сеттерами.
* atm.json.UserJson and atm.json.AdminJson мають купу всього спільного, має бути абстракний клас зі спільною функціональністю. А вони нащадками.
* Розбивання проекту на структуру. Java application layers. Створювати пакети по функціональностях. http://tidyjava.com/layered-architecture-good/ 
* Maven introduction. Variable creation.
* SQL must have.
  * http://sql-ex.ru/
  * https://www.tutorialspoint.com/sql/index.htm
  * https://www.w3schools.com/sql/

* https://dou.ua/lenta/digests/junior-digest-26/#Grid%20Dynamics
* [Logger](https://howtodoinjava.com/log4j2/log4j-2-xml-configuration-example/)