package ru.sberbank.jd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.entity.GroupEvent;

/**
 * Интерфейс работы с БД Групповых мероприятий.
 */
public interface GroupEventRepository extends JpaRepository<GroupEvent, String> {
}