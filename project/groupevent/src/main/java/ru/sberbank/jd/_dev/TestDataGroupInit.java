package ru.sberbank.jd._dev;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.entity.GroupEvent;
import ru.sberbank.jd.repository.GroupEventRepository;

/**
 * Инициализация тестовых данных.
 */
@Component
@AllArgsConstructor
@Slf4j
public class TestDataGroupInit {

    private final GroupEventRepository groupEventRepository;

    @PostConstruct
    public void initData() {

        log.info("[Start].initGroupData()......");

        GroupEvent groupEvent1 = new GroupEvent();
        groupEvent1.setId("991138");
        groupEvent1.setCode("002070");
        groupEvent1.setType("G");
        groupEvent1.setName("Спортивное мероприятие в Новосибирске");
        groupEvent1.setInfo("Спартакиада шахматистов(Сбер)");
        groupEvent1.setCountry("Россия");
        groupEvent1.setCity("Новосибирск");
        groupEvent1.setRegion("Западная Сибирь");
        groupEvent1.setSbt("SBT-29");
        groupEvent1.setBegda(LocalDate.now());
        groupEvent1.setEndda(LocalDate.of(2024,8,17));
        groupEvent1.setDeadline(LocalDate.of(2024,7,27));
        groupEvent1.setSignerId("221177");
        groupEvent1.setActiv(true);
        groupEvent1.setOrganizer("LOGIN-SF");
        groupEventRepository.save(groupEvent1);
        groupEventRepository.flush();

        GroupEvent groupEvent2 = new GroupEvent();
        groupEvent2.setId("301726");
        groupEvent2.setCode("001011");
        groupEvent2.setType("D");
        groupEvent2.setName("Учебное мероприятие в Кемерово");
        groupEvent2.setInfo("Курсы бухгалтеров(Сбер)");
        groupEvent2.setCountry("Россия");
        groupEvent2.setCity("Кемерово");
        groupEvent2.setRegion("Западная Сибирь");
        groupEvent2.setSbt("SBT-32");
        groupEvent2.setBegda(LocalDate.now());
        groupEvent2.setEndda(LocalDate.of(2024,9,25));
        groupEvent2.setDeadline(LocalDate.of(2024,8,20));
        groupEvent2.setSignerId("221177");
        groupEvent2.setActiv(true);
        groupEvent2.setOrganizer("LOGIN-SF");
        groupEventRepository.save(groupEvent2);
        groupEventRepository.flush();

        log.info("[Done]  .initGroupData()");
    }
}