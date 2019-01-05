package org.jerseyspring.service.interfaces;

import org.jerseyspring.vm.interfaces.VMInterface;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */
public interface EmployeeInterface<T extends VMInterface> extends ServiceInterface<T> {

    @Transactional(readOnly = true)
    List<T> getAllAge(int from, int to);
}
