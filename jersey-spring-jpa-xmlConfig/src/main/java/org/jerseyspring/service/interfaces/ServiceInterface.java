package org.jerseyspring.service.interfaces;

import org.jerseyspring.vm.interfaces.VMInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */
public interface ServiceInterface<T extends VMInterface> extends Serializable {

    @Transactional(readOnly = true)
    List<T> getAll();

    @Transactional(readOnly = true)
    Page<T> getAllPage(Pageable pageable);

    @Transactional(readOnly = true)
    <S extends T> S findById(Long id);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    <S extends T> S save(S s);

    @Transactional(isolation = Isolation.READ_COMMITTED)
    void delete(Long id);
}
