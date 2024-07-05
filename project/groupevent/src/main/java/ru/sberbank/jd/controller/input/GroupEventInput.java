package ru.sberbank.jd.controller.input;

import java.time.LocalDate;

/**
 * Входящая структура Группового мероприятия.
 *
 * @param code
 * @param type
 * @param name
 * @param info
 * @param country
 * @param city
 * @param region
 * @param sbt
 * @param begda
 * @param endda
 * @param deadline
 * @param signerId
 * @param activ
 * @param organizer
 */
public record GroupEventInput(
        String code,        //Код группы ("D000000103")
        String type,        // Тип группы
        String name,        //Наименование группы командировок
        String info,        //Информация о мероприятии
        String country,     //Страна
        String city,        //Место проведения мероприятия(город командировки)
        String region,      //Регион
        String sbt,         //Ответственное подразделение
        LocalDate begda,    //Дата начала мероприятия
        LocalDate endda,    //Дата завершения мероприятия
        LocalDate deadline, //Крайний срок создания командировки на мероприятие(дедлайн)
        String signerId,      //Подписант мероприятия(Id)
        Boolean activ,      //Активировано(запрет на изменения)
        String organizer    //Имя исполнителя создавшего объект
) {
}