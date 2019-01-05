package org.jerseyspring.service;

import org.jerseyspring.entity.Department;
import org.jerseyspring.repositories.DepartmentRepository;
import org.jerseyspring.service.interfaces.ServiceInterface;
import org.jerseyspring.vm.DepartmentVM;
import org.jerseyspring.vm.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */
@Service(value = "departmentService")
@Scope(value = "singleton")
public class DepartmentService implements ServiceInterface<DepartmentVM> {

    private static final long serialVersionUID = -3345796570401811694L;

    @Autowired
    @Qualifier(value = "departmentRepository")
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentVM> getAll() {
        List<Department> listDepartment = departmentRepository.findAll();
        List<DepartmentVM> listDepartmentVM = listDepartment.stream().map(DepartmentVM::new).collect(Collectors.toList());
        for (DepartmentVM depVM : listDepartmentVM) {
            for (Department dep : listDepartment) {
                if (depVM.getId().equals(dep.getId())) {
                    depVM.setEmployeesVM(dep.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));
                }
            }
        }
        return listDepartmentVM;
    }

    @Override
    public Page<DepartmentVM> getAllPage(Pageable pageable) {
        List<Department> listDepartment = departmentRepository.findAll(pageable).getContent();
        List<DepartmentVM> listDepartmentVM = listDepartment.stream().map(DepartmentVM::new).collect(Collectors.toList());
        for (DepartmentVM depVM : listDepartmentVM) {
            for (Department dep : listDepartment) {
                if (depVM.getId().equals(dep.getId())) {
                    depVM.setEmployeesVM(dep.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));
                }
            }
        }
        Page<DepartmentVM> pageDepartmentVM = new PageImpl<DepartmentVM>(listDepartmentVM, pageable, listDepartmentVM.size());

        return pageDepartmentVM;
    }

    @Override
    public DepartmentVM findById(Long id) {
        Department department = departmentRepository.findById(id).get();
        DepartmentVM departmentVM = new DepartmentVM(department);
        departmentVM.setEmployeesVM(department.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));

        return departmentVM;
    }

    @Override
    public DepartmentVM save(DepartmentVM departmentVM) {
        Department department = departmentVM.getDepartment();
        department.setEmployees(
                departmentVM.getEmployeesVM().stream()
                        .map(EmployeeVM::getEmployee)
                        .collect(Collectors.toList())
        );
        department = departmentRepository.save(department);
        departmentVM = new DepartmentVM(department);
        List<EmployeeVM> lEmployeeVM = new LinkedList<>();
        departmentVM.setEmployeesVM(department.getEmployees().stream().map(EmployeeVM::new).collect(Collectors.toList()));

        return departmentVM;
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteDepartmentById(id);

    }
}
