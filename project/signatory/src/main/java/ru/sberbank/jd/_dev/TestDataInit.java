package ru.sberbank.jd._dev;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.entity.Employee;
import ru.sberbank.jd.entity.Position;
import ru.sberbank.jd.repository.EmployeeRepository;
import ru.sberbank.jd.repository.PositionRepository;

/**
 * Инициализация тестовых данных.
 */
@Component
@AllArgsConstructor
@Slf4j
public class TestDataInit {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @PostConstruct
    public void initData() {

        log.info("[Start].initData()......");

        Position position1 = new Position();
        position1.setId("111");
        position1.setPlans("73590");
        position1.setName("Начальник отдела");
        position1.setBegda(LocalDate.now());
        position1.setEndda(LocalDate.of(2024,8,17));
        position1.setCreateDateTime(LocalDateTime.now());
        positionRepository.save(position1);
        positionRepository.flush();

        Position position2 = new Position();
        position2.setId("222");
        position2.setPlans("92751");
        position2.setName("Ведущий специалист");
        position2.setBegda(LocalDate.now());
        position2.setEndda(LocalDate.of(2024,11,10));
        position2.setCreateDateTime(LocalDateTime.now());
        positionRepository.save(position2);
        positionRepository.flush();

        Employee employee1 = new Employee();
        employee1.setId("12345");
        employee1.setPernr("4015");
        employee1.setSname("Петров");
        employee1.setEname("Семен");
        employee1.setPname("Ильич");
        employee1.setOrgeh("9023");
        employee1.setBukrs("9900");
        employee1.setWerks("2015");
        employee1.setPersk("03");
        employee1.setKostl("1027");
        employee1.setBirthDay(LocalDate.of(2024,10,10));
        employee1.setBegda(LocalDate.now());
        employee1.setEndda(LocalDate.of(2024,12,10));
        employee1.setPosition(position1);
        employee1.setCreateDateTime(LocalDateTime.now());
        employeeRepository.save(employee1);
        employeeRepository.flush();

        Employee employee2 = new Employee();
        employee2.setId("221177");
        employee2.setPernr("4019");
        employee2.setPosition(position2);
        employee2.setSname("Маслов");
        employee2.setEname("Иван");
        employee2.setPname("Николаевич");
        employee2.setOrgeh("9027");
        employee2.setBukrs("9900");
        employee2.setWerks("2011");
        employee2.setPersk("07");
        employee2.setKostl("7013");
        employee2.setBirthDay(LocalDate.of(2025,10,20));
        employee2.setBegda(LocalDate.now());
        employee2.setEndda(LocalDate.of(2025,12,21));
        employee2.setPosition(position1);
        employee2.setCreateDateTime(LocalDateTime.now());
        employeeRepository.save(employee2);
        employeeRepository.flush();

        Employee employee3 = new Employee();
        employee3.setId("337711");
        employee3.setPernr("5020");
        employee3.setSname("Карсон");
        employee3.setEname("Магнус");
        employee3.setPname("Иванович");
        employee3.setOrgeh("9027");
        employee3.setBukrs("9900");
        employee3.setWerks("2015");
        employee3.setPersk("03");
        employee3.setKostl("1028");
        employee3.setBirthDay(LocalDate.of(2026,11,5));
        employee3.setBegda(LocalDate.now());
        employee3.setEndda(LocalDate.of(2026,10,11));
        employee3.setPosition(position2);
        employee3.setCreateDateTime(LocalDateTime.now());
        employeeRepository.save(employee3);
        employeeRepository.flush();

        log.info("[Done]  .initData()");
    }
}