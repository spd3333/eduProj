package ru.sberbank.jd.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.jd.entity.GroupEvent;

/**
 * Модель обмена данными, выходные данные.
 */
@Data
@AllArgsConstructor
@Builder
@Slf4j
public class EventGroup {
    public static final String ULOGIN = "LOGIN-FS";

    private String id;
    private String code; //Код группы ("D000000103")
    private String type; // Тип группы
    private String name; //Наименование группы командировок
    private String info; //Информация о мероприятии
    private String country; //Страна
    private String city; //Место проведения мероприятия(город командировки)
    private String region; //Регион
    private String sbt; //Ответственное подразделение
    private LocalDate begda; //Дата начала мероприятия
    private LocalDate endda; //Дата завершения мероприятия
    private LocalDate deadline; //Крайний срок создания командировки на мероприятие(дедлайн)
    private String signerId; //Подписант мероприятия(Id)
    private String signer; //Подписант мероприятия(табельный номер)
    private Boolean activ; //Активировано(запрет на изменения)
    private String organizer; //Имя исполнителя создавшего объект
    private String uname; //Имя исполнителя изменившего объект
    private LocalDateTime createDateTime; //ДатаВремя создания записи
    private LocalDateTime updateDateTime; //ДатаВремя изменения записи

    public static EventGroup create(GroupEvent groupEvent) {
        log.info("[create.EventGroup] groupEvent = {}", groupEvent);
        return new EventGroupBuilder()
                .id(groupEvent.getId())
                .code(groupEvent.getCode())
                .type(groupEvent.getType())
                .name(groupEvent.getName())
                .info(groupEvent.getInfo())
                .country(groupEvent.getCountry())
                .city(groupEvent.getCity())
                .region(groupEvent.getRegion())
                .sbt(groupEvent.getSbt())
                .begda(groupEvent.getBegda())
                .endda(groupEvent.getEndda())
                .deadline(groupEvent.getDeadline())
                .signerId(groupEvent.getSignerId())
                .activ(groupEvent.getActiv())
                .organizer(groupEvent.getOrganizer())
                .createDateTime(groupEvent.getCreateDateTime())
                .updateDateTime(groupEvent.getUpdateDateTime())
                .uname(groupEvent.getUname())
                .build();
    }
}