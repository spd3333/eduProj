package ru.sberbank.jd.controller.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.jd.entity.Position;

/**
 * Структура обмена данными Должность.
 * Подмножество полей из таблицы Position.
 */
@Data
@AllArgsConstructor
@Builder
@Slf4j
public class PositionOut {

    private String id;
    private String plans;       //Должность - код
    private String name;        //Должность - название
    private LocalDate begda;    //Начало срока действия
    private LocalDate endda;    //Конец срока действия
    private boolean histo;      //Индикатор записи в историю
    private String uname;       //Имя исполнителя изменившего объект
    private LocalDateTime createDateTime; //ДатаВремя создания записи
    private LocalDateTime updateDateTime; //ДатаВремя изменения записи

    public static PositionOut create(Position position) {

        log.info("[create.Position] employee = {}", position);
        return new PositionOutBuilder()
                .id(position.getId())
                .plans(position.getPlans())
                .name(position.getName())
                .begda(position.getBegda())
                .endda(position.getEndda())
                .histo(position.isHisto())
                .createDateTime(position.getCreateDateTime())
                .updateDateTime(position.getUpdateDateTime())
                .uname(position.getUname())
                .build();
    }
}