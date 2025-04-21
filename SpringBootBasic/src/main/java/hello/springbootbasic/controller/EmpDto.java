package hello.springbootbasic.controller;

public class EmpDto {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String hireDate;

    public EmpDto() {
    }

    public EmpDto(String email, int employeeId, String firstName, String hireDate, String lastName) {
        this.email = email;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.hireDate = hireDate;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "EmpDto{" +
                "email='" + email + '\'' +
                ", employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
