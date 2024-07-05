package ru.sberbank.jd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность: Запись в БД Групповое мероприятие GroupEvent.
 */
@Entity
@Getter
@Setter
public class GroupEvent {
    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String code; //Код группы ("D000000103")

    // Тип группы (D - Мероприятие, G - Групповая командировка, M - массовое мероприятие )
    private String type;
    private String name; //Наименование группы командировок
    private String info; //Информация о мероприятии
    private String country; //Страна
    private String city; //Место проведения мероприятия(город командировки)
    private String region; //Регион
    private String sbt; //Ответственное подразделение
    private LocalDate begda; //Дата начала мероприятия
    private LocalDate endda; //Дата завершения мероприятия
    private LocalDate deadline; //Крайний срок создания командировки на мероприятие(дедлайн)
    private String signerId; //Подписант мероприятия
    private Boolean activ; //Активировано(запрет на изменения)
    private String organizer; //Имя исполнителя создавшего объект
    private String uname; //Имя исполнителя изменившего объект
    private LocalDateTime createDateTime; //ДатаВремя создания записи
    private LocalDateTime updateDateTime; //ДатаВремя изменения записи
}