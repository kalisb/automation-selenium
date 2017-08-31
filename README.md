# ТЕМА:  Създаване на тестови сценарии и тестване на puffin посредством Java/Selenium WebDriver

### 1. Условие

> Уеб базирано приложение за дефиниране на тестови сценарии с примери за puffin

Приложението трябва създава проекти с тестове и да ги изпълнява. Всеки може да добави проект. Всеки проект има свои функционални изисквания, които трябва лесно да се добавят от потребителя. Потребителят трябва да опише функционалните изисквания стъпка по стъпка и напише необходимата имплементация за тяхното тестване използвайки предоствен му **Selenium Web Driver**. Щом функционалното изискване е запаметено, то може да бъде променяно и изпълнявано. За всяко изпълнение се генерира **html** доклад и потребителя се уведомява за резултата от тестовете.


### 2. Въведение

**Selenium Web Driver** е инструмент за писане на автоматизирани тестове за уеб страници. То имитира действията на истински потребител. За да улесни тестването приложението ще пропусне досадната част по подготовка на драйвъра като предлага на потребителя готов драйвър, който сам стартира преди теста и се изключва след него. Приложението е интегрирано и с Cucumber-JVM налагайки BDD разработка. 

### 3. Теория

Един типичен бизнес проект би започнал с даването на примерни сценарии на това, което трябва да прави системата от заинтересованите лица. Всички усилия се хвърлят в изпълнението на тези сценарии. Примерите се превръщат в критерия за валидация посредством автоматизирани тестове. Резултатът от тези тестове уверява заинтересованите лица, че желаните изисквания са покрити. В идеалния случай, доклада от тестовете е лесен за разбиране от всеки. BDD ни позволява да “съживим“ документацията, като тя може да бъде променяна в процеса на тестване. На практика, behavior-driven development е подобно на test-driven development, където всички заинтересовани лица имат знания и умения за програмиране. Но това не винаги е ситуацията в една организация, BDD позволява да включим по голяма част от заинтересованите лица и потребители, които имат малък или никакъв опит с програмиране. 

### 4. Използвани технологии

Selenium Web Driver - инструмент за писане на автоматизирани тестове за уеб страници. То имитира действията на истински потребител. Cucumber – софтуерен инструмент за тестване на софтуер. Изпълнява тестове написани в BDD стил.

### 6. Кратко ръководство на потребителя

** 1. Създаване на проект **

![alt text](https://raw.githubusercontent.com/kalisb/automation-selenium/master/images/projects.png)

** 2. Създаване на тестови сценарии **

![alt text](https://raw.githubusercontent.com/kalisb/automation-selenium/master/images/features.png)

** 3. Изпълнение на тестови сценарии **

![alt text](https://raw.githubusercontent.com/kalisb/automation-selenium/master/images/results.png)