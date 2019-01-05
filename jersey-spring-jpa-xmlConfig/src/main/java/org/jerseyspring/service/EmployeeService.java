package org.jerseyspring.service;

import org.jerseyspring.entity.Department;
import org.jerseyspring.entity.Employee;
import org.jerseyspring.repositories.DepartmentRepository;
import org.jerseyspring.repositories.EmployeeRepository;
import org.jerseyspring.service.interfaces.EmployeeInterface;
import org.jerseyspring.vm.DepartmentVM;
import org.jerseyspring.vm.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */
@Service(value = "employeeService")
@Scope(value = "singleton")
public class EmployeeService implements EmployeeInterface<EmployeeVM> {

    private static final long serialVersionUID = -2609914157567100940L;

    @Autowired
    @Qualifier(value = "employeeRepository")
    private EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier(value = "departmentRepository")
    private DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeVM> getAll() {
        List<Employee> listEmployee = employeeRepository.findAll();
        List<EmployeeVM> listEmployeeVM = listEmployee.stream().map(EmployeeVM::new).collect(Collectors.toList());
        for (EmployeeVM empVM : listEmployeeVM) {
            for (Employee emp : listEmployee) {
                if (empVM.getId().equals(emp.getId())) {
                    empVM.setDepartmentsVM(emp.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));
                }
            }
        }
        return listEmployeeVM;
    }

    @Override
    public Page<EmployeeVM> getAllPage(Pageable pageable) {
        List<Employee> listEmployee = employeeRepository.findAll(pageable).getContent();
        List<EmployeeVM> listEmployeeVM = listEmployee.stream().map(EmployeeVM::new).collect(Collectors.toList());
        for (EmployeeVM empVM : listEmployeeVM) {
            for (Employee emp : listEmployee) {
                if (empVM.getId().equals(emp.getId())) {
                    empVM.setDepartmentsVM(emp.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));
                }
            }
        }
        Page<EmployeeVM> pageEmployeeVM = new PageImpl<EmployeeVM>(listEmployeeVM, pageable, listEmployeeVM.size());

        return pageEmployeeVM;
    }

    @Override
    public EmployeeVM findById(Long id) {
        Employee employee = employeeRepository.findEmployeesById(id);
        EmployeeVM employeeVM = new EmployeeVM(employee);
        employeeVM.setDepartmentsVM(employee.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));

        return employeeVM;
    }

    @Override
    public List<EmployeeVM> getAllAge(int from, int to) {
        List<Employee> listEmployee = employeeRepository.findEmployeesBetweenAge(from, to);
        List<EmployeeVM> listEmployeeVM = listEmployee.stream().map(EmployeeVM::new).collect(Collectors.toList());
        for (EmployeeVM empVM : listEmployeeVM) {
            for (Employee emp : listEmployee) {
                if (empVM.getId().equals(emp.getId())) {
                    empVM.setDepartmentsVM(emp.getDepartments().stream().map(DepartmentVM::new).collect(Collectors.toSet()));
                }
            }
        }
        return listEmployeeVM;
    }

    @Override
    public EmployeeVM save(EmployeeVM employeeVM) {
        EmployeeVM empVM = new EmployeeVM(employeeRepository.save(employeeVM.getEmployee()));
        Set<DepartmentVM> departmentVMS = new HashSet<>();
        employeeVM.getDepartmentsVM().forEach(departmentVM -> {
            Department department = departmentVM.getDepartment();
            department.setEmployees(Arrays.asList(new Employee[]{empVM.getEmployee()}));
            departmentVMS.add(new DepartmentVM(departmentRepository.save(department)));
        });
        empVM.setDepartmentsVM(departmentVMS);
        return empVM;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }

}
