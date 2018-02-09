package com.quantum.engine.homy.model;

public class BizUserCompany extends User{

    private String operatingProjects;

    private String employeeNum;

    private Integer operatingYears;

    private String companyPhone;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getOperatingProjects() {
        return operatingProjects;
    }

    public void setOperatingProjects(String operatingProjects) {
        this.operatingProjects = operatingProjects == null ? null : operatingProjects.trim();
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum == null ? null : employeeNum.trim();
    }

    public Integer getOperatingYears() {
        return operatingYears;
    }

    public void setOperatingYears(Integer operatingYears) {
        this.operatingYears = operatingYears;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}