package ru.sberbank.jd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.entity.Position;

/**
 * Интерфейс репозитория Должности.
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
}