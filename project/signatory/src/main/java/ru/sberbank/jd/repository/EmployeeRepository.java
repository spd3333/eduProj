package ru.sberbank.jd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.entity.Employee;

/**
 * Интерфейс репозитория Подписанта.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
