package org.jerseyspring.entity.enumaration;

/**
 * @author Andrey <Andrey at andrew.my@yahoo.com>
 */
public enum DepartmentEnum {
    FIRSTDEP("FirstDep"),
    SECONDDEP("SecondDep"),
    THIRDDEP("ThirdDep");

    private String code;

    DepartmentEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "DepartmentEnum{" +
                "code='" + code + '\'' +
                '}';
    }

}
