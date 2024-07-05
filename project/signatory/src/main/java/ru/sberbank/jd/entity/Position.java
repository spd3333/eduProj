package ru.sberbank.jd.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность: справочник Должность.
 *
 */
@Entity
@Getter
@Setter
public class Position {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String plans;       //Должность - код
    private String name;        //Должность - название
    private LocalDate begda;    //Начало срока действия
    private LocalDate endda;    //Конец срока действия
    private boolean histo;      //Индикатор записи в историю
    private String uname;       //Имя исполнителя изменившего объект
    private LocalDateTime createDateTime; //ДатаВремя создания записи
    private LocalDateTime updateDateTime; //ДатаВремя изменения записи

    //ссылка на список сотрудников
    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    private List<Employee> employees;
}