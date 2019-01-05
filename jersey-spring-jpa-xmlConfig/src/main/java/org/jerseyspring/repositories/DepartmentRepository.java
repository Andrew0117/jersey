package org.jerseyspring.repositories;

import org.jerseyspring.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */
@Repository(value = "departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Modifying
    @Transactional
    @Query("delete from Department where id = :id")
    void deleteDepartmentById(@Param("id") Long id);

}
