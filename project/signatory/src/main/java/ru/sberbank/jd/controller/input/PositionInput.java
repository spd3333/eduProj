package ru.sberbank.jd.controller.input;

import java.time.LocalDate;

/**
 * Структура Должности при создании.
 *
 * @param plans
 * @param name
 * @param begda
 * @param endda
 * @param histo
 */
public record PositionInput(

    String plans,       //Должность - код
    String name,        //Должность - название
    LocalDate begda,    //Начало срока действия
    LocalDate endda,    //Конец срока действия
    Boolean histo       //Индикатор записи в историю
) {
}