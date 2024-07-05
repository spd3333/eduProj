package ru.sberbank.jd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность: Запись в БД сотрудника Employee.
 *
 */
@Entity
@Getter
@Setter
public class Employee {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String pernr; //Табельный номер
    private String sname; //Фамилия
    private String ename; //Имя
    private String pname; //Отчество
    private LocalDate birthDay; //Дата рождения
    private String orgeh; //Организационная единица
    private String bukrs; //Балансовая единица
    private String werks; //Раздел персонала
    private String persk; //Категория сотрудников
    private String kostl; //Место возникновения затрат
    private LocalDate begda; //Начало срока действия
    private LocalDate endda; //Конец срока действия
    private boolean histo; //Индикатор записи в историю
    private String uname; //Имя исполнителя изменившего объект
    private LocalDateTime createDateTime; //ДатаВремя создания записи
    private LocalDateTime updateDateTime; //ДатаВремя изменения записи

    //ссылка на Должность
    @ManyToOne(fetch = FetchType.EAGER)
    private Position position;
}