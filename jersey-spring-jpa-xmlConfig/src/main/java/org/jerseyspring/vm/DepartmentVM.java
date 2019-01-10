package org.jerseyspring.vm;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.jerseyspring.entity.Department;
import org.jerseyspring.entity.enumaration.DepartmentEnum;
import org.jerseyspring.vm.interfaces.VMInterface;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */
public class DepartmentVM implements VMInterface {

    private Long id;

    @Size(max = 256)
    private String name;

    private DepartmentEnum departmentEnum;

    private Instant date;

    private List<EmployeeVM> employeesVM;

    public DepartmentVM() {
    }

    public DepartmentVM(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.departmentEnum = department.getDepartmentEnum();
        this.date = department.getDate();
    }

    @JsonIgnore
    public Department getDepartment() {
        Department department = new Department();
        department.setId(this.id);
        department.setName(this.name);
        department.setDepartmentEnum(this.departmentEnum);
        department.setDate(this.date);

        return department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<EmployeeVM> getEmployeesVM() {
        return employeesVM;
    }

    public void setEmployeesVM(List<EmployeeVM> employeesVM) {
        this.employeesVM = employeesVM;
    }

    public String getDepartmentEnum() {
        return departmentEnum.getCode();
    }

    public void setDepartmentEnum(DepartmentEnum departmentEnum) {
        this.departmentEnum = departmentEnum;
    }

    @Override
    public String toString() {
        return "DepartmentVM{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentEnum=" + departmentEnum +
                ", date=" + date +
                '}';
    }
}
