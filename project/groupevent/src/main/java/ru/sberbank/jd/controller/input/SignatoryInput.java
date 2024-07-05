package ru.sberbank.jd.controller.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Структура Подписанта при вводе нового значения.
 *
 * @param id
 * @param pernr
 * @param sname
 * @param ename
 * @param pname
 * @param birthDay
 * @param orgeh
 * @param plans
 * @param bukrs
 * @param werks
 * @param persk
 * @param kostl
 * @param begda
 * @param endda
 * @param histo
 * @param uname
 * @param createDateTime
 * @param updateDateTime
 */
public record SignatoryInput(
        String id,
        String pernr, //Табельный номер
        String sname, //Фамилия
        String ename, //Имя
        String pname, //Отчество
        LocalDate birthDay, //Дата рождения
        String orgeh, //Организационная единица
        String plans, //Должность
        String bukrs, //Балансовая единица
        String werks, //Раздел персонала
        String persk, //Категория сотрудников
        String kostl, //Место возникновения затрат
        LocalDate begda, //Начало срока действия
        LocalDate endda, //Конец срока действия
        boolean histo, //Индикатор записи в историю
        String uname, //Имя исполнителя изменившего объект
        LocalDateTime createDateTime,
        LocalDateTime updateDateTime //ДатаВремя изменения записи
) {
}