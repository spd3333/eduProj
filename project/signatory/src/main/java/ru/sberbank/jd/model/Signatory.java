package ru.sberbank.jd.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.jd.entity.Employee;

/**
 * Структура обмена данными Подписант - Руководитель.
 * Подмножество полей из таблицы сотрудников Employee.
 */
@Data
@AllArgsConstructor
@Builder
@Slf4j
public class Signatory {
    public static final String ULOGIN = "LOGIN-FS";

    private final String id;
    private final String pernr; //Табельный номер
    private String sname; //Фамилия
    private String ename; //Имя
    private String pname; //Отчество
    private LocalDate birthDay; //Дата рождения
    private String orgeh; //Организационная единица
    private String plans; //Должность
    private String bukrs; //Балансовая единица
    private String werks; //Раздел персонала
    private String persk; //Категория сотрудников
    private String kostl; //Место возникновения затрат
    private LocalDate begda; //Начало срока действия
    private LocalDate endda; //Конец срока действия
    private boolean histo; //Индикатор записи в историю
    private String uname; //Имя исполнителя изменившего объект
    private final LocalDateTime createDateTime;
    private LocalDateTime updateDateTime; //ДатаВремя изменения записи

    public static Signatory create(Employee employee) {

        log.info("[create.Signatory] employee = {}", employee);
        return new SignatoryBuilder()
                .id(employee.getId())
                .pernr(employee.getPernr())
                .sname(employee.getSname())
                .ename(employee.getEname())
                .pname(employee.getPname())
                .birthDay(employee.getBirthDay())
                .orgeh(employee.getOrgeh())
                .plans(employee.getPosition().getPlans())
                .bukrs(employee.getBukrs())
                .werks(employee.getWerks())
                .persk(employee.getPersk())
                .kostl(employee.getKostl())
                .begda(employee.getBegda())
                .endda(employee.getEndda())
                .histo(employee.isHisto())
                .createDateTime(employee.getCreateDateTime())
                .updateDateTime(employee.getUpdateDateTime())
                .uname(employee.getUname())
                .build();
    }
}