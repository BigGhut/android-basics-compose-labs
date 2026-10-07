# Android Basics with Compose

Лабораторные работы по курсу [Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course), юниты 1 и 2.

Каждая папка ниже — отдельный проект Gradle. История DiceRoller и Lemonade подключена через `git subtree`, поэтому коммиты и скриншоты с телефона остались в общем журнале.

## Юнит 1

| Проект | Что это | Папка |
| --- | --- | --- |
| GreetingCard | Первая открытка: зелёный фон и текст «Hi, my name is Android!» | [unit-1/GreetingCard](unit-1/GreetingCard) |
| HappyBirthday | Открытка «Happy Birthday Sara!» с картинкой и подписью From Vitaly | [unit-1/HappyBirthday](unit-1/HappyBirthday) |
| BusinessCard | Визитка: имя, должность и контакты | [unit-1/BusinessCard](unit-1/BusinessCard) |
| ComposeArticle | Экран статьи «Jetpack Compose tutorial» | [unit-1/ComposeArticle](unit-1/ComposeArticle) |
| ComposeQuadrant | Четыре квадранта: Text, Image, Row и Column | [unit-1/ComposeQuadrant](unit-1/ComposeQuadrant) |
| TaskCompleted | Экран «All tasks completed» / «Nice work!» | [unit-1/TaskCompleted](unit-1/TaskCompleted) |
| App_Check | Проверка шаблона Compose: приветствие «Hello, Vitaly!» | [unit-1/App_Check](unit-1/App_Check) |
| MyApplication | Стартовый шаблон Android Studio: «Hello Android!» | [unit-1/MyApplication](unit-1/MyApplication) |

## Юнит 2

| Проект | Что это | Папка |
| --- | --- | --- |
| DiceRoller | Бросок кубика по кнопке, со скриншотами с телефона | [unit-2/DiceRoller](unit-2/DiceRoller) |
| Lemonade | Шаги приготовления лимонада по нажатию, со скриншотами с телефона | [unit-2/Lemonade](unit-2/Lemonade) |
| TipTime | Калькулятор чаевых | [unit-2/TipTime](unit-2/TipTime) |
| ArtSpace | Галерея картин с кнопками Previous и Next | [unit-2/ArtSpace](unit-2/ArtSpace) |
| kotlin-fundamentals-practice | Задачи по основам Kotlin: уведомления, билеты, температура, песни, профиль, телефоны, аукцион | [unit-2/kotlin-fundamentals-practice](unit-2/kotlin-fundamentals-practice) |
| smart-home | Умный дом: телевизор и лампа, классы и наследование | [unit-2/smart-home](unit-2/smart-home) |
| trick-or-treat | Лямбды: trick, treat и консоль вывода | [unit-2/trick-or-treat](unit-2/trick-or-treat) |

## Как открыть проект в Android Studio

1. Запустите Android Studio.
2. Выберите **File → Open**.
3. Укажите папку одного проекта, например `unit-1/HappyBirthday`. Корень репозитория открывать не нужно: это не один Gradle-проект, а набор лабораторных.
4. Дождитесь синхронизации Gradle. Файл `local.properties` студия создаст на вашей машине сама.
