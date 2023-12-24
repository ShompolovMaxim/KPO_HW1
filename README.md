# KPO_HW1
Данные о фильмах хранятся в директории KPO_HW1\films

Данные о сеансах хранятся в директории KPO_HW1\displays

Выбран формат хранения JSON, так как csv неудобен для хранения табличных данных, а так же с учётом связи Kotlin с JS

При запуске программа предлагает 13 вариантов использования (для выбора одного из них необходимо ввести его номер в консоль):
1) Добавить фильм: при выборе будет предложено ввести название фильма и его описание, если фильм с таким названием уже есть, то сохранить не удастся и об этом будет сообщено
2) Получить данные о фильме: необходимо ввести название фильма, будет выведено его описание; если такого фильма нет, об этом будет сообщено
3) Изменить данные фильма: необходимо ввести название фильма, будет предложено ввести новое имя и новое описание если такого фильма нет, об этом будет сообщено
4) Удалить фильм: необходимо ввести название фильма, он будет удалён если такого фильма нет, об этом будет сообщено
5) Добавить сеанс: необходимо ввести время сеанса и название фильма; если время занято или такого фильма нет, об этом будет сообщено; изначально все места свободные
6) Просмотреть данные о сеансе: необходимо ввести время сеанса, будет выведено название фильма и информация о свободных и занятых местах (в левом верхнем углу место (0,0), в левом нижнем - (3,0), в правом нижнем - (3,5), 1 - место занято, 0 - свободно); если введённое время свободно, об этом будет сообщено
7) Изменить данные сеанса: необходимо ввести время показа, будет выведено название фильма и предложено ввести новое время показа и название фильма; если в это время нет сеанса или фильма с введённым названием не существует, об этом будет сообщено
8) Удалить сеанс: необходимо ввести время сеанса, сеанс будет удалён; если введённое время свободно - об этом будет сообщено
9) Будут выведены данные о всех фильмах: название и описание
10) Будут выведены данные о всех сеансах: время и название фильма
11) Продать билет: необходимо ввести время сеанса, номер ряда (row), номер места (colon), имя клиента; если место занято или данные некорректны, об этом будет сообщено
12) Вернуть билет: необходимо ввести время сеанса, номер ряда (row), номер места (colon); если данные некорректны или место свободно, об этом будет сообщено
13) Работа программы будет прекращена, все данные сохранены
