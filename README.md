﻿Задача:
Создать приложение с 2 экранами.

Приложение "Погода"

На 1 экране(отображение) должно быть:
1. Возможность выбрать город (3-4 города)
2. Возможность выбрать сезон года
3. В зависимости от п.1 и п.2 - отображение средней температуры за сезон в городе
4. В зависимости от п.1 - отображать тип города (малый, средний, большой)

На 2 экране(настройки) должно быть:
1. Управление списком городов (город, тип)
2. Управление температурой по месяцам.

Приложение на первом экране должно отображать информацию, введенную пользователем на втором экране. Например, на втором экране вводим :
город "Бишкек", тип "средний", температура июнь "23" июль "28" август "25"
на первом экране в списке городов, должен отобразится Бишкек, тип "средний" и температура за сезон "лето" "25,3"(среднее арифметическое 3х месяцев).   

Использовать паттерны:
"Lazy singleton": На усмотрение
"Factory": Получать тип города в зависимости от его названия
"Decorator": При запросе средней температуры за сезон в городе - должна быть возможность получить строку для логгирования
"Observer": Дополнительно выводить сообщение, о температуре, через Snackbar
"Strategy": В зависимости от стратегии - выводить температуру в необходимом формате (градус Цельсия, градус Фаренгейта, Кельвин)

Условия:
Без использования сети
БД: SQLite
Мы ожидаем выполнения задания без применения сторонних библиотек (Кроме Android Support Library и Android Architecture Components)
Результат выложить на Github.com