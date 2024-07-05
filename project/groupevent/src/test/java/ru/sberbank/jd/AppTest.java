package ru.sberbank.jd;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.entity.GroupEvent;
import ru.sberbank.jd.model.EventGroup;

/**
 * Unit test for create GroupEvent
 */
public class AppTest {

    /**
     * Тест создания и перекладывания input данные в model
     */
    @Test
    public void createGroupEventTest01() {

        GroupEvent groupEvent1 = new GroupEvent();
        groupEvent1.setId(UUID.randomUUID().toString());
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
        Assert.assertNotNull(EventGroup.create(groupEvent1));

        GroupEvent groupEvent2 = new GroupEvent();
        groupEvent2.setId(UUID.randomUUID().toString());
        groupEvent2.setCode("315180");
        groupEvent2.setType("M");
        groupEvent2.setName("Обучение");
        groupEvent2.setInfo("Проект 21");
        groupEvent2.setCountry("Россия");
        groupEvent2.setCity("Новосибирск");
        groupEvent2.setRegion("Западная Сибирь");
        groupEvent2.setSbt("SBT-29");
        groupEvent2.setBegda(LocalDate.now());
        groupEvent2.setEndda(LocalDate.of(2024,8,17));
        groupEvent2.setDeadline(LocalDate.of(2024,7,27));
        groupEvent2.setSignerId("221177");
        groupEvent2.setActiv(true);
        groupEvent2.setOrganizer("LOGIN-SF");
        Assert.assertNotNull(EventGroup.create(groupEvent2));
    }

    @Test
    public void builderGroupEventTest02() {
        EventGroup eventGroup = EventGroup.builder()
                .id("edgrkl22")
                .code("code22")
                .type("M")
                .name("Name22")
                .info("info22")
                .country("country22")
                .city("city22")
                .region("region22")
                .sbt("sbt22")
                .organizer("7292")
                .uname("login33")
                .build();
        Assert.assertNotNull(eventGroup);
    }
}