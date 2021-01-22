package com.example.customtable;

public class ListItemHeader {

    String empno,name,team,dept,designation,unit,leavedate,session,purpose,remarks;

    public ListItemHeader(String empno, String name, String team, String dept, String designation, String unit, String leavedate, String session, String purpose, String remarks) {
        this.empno = empno;
        this.name = name;
        this.team = team;
        this.dept = dept;
        this.designation = designation;
        this.unit = unit;
        this.leavedate = leavedate;
        this.session = session;
        this.purpose = purpose;
        this.remarks = remarks;
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
