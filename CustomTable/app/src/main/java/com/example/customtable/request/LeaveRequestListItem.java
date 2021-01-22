package com.example.customtable;

public class LeaveRequestListItem {

    String empno,name,team,dept,designation,unit,leavedate,session,purpose,remarks;

    public LeaveRequestListItem(String empno, String name, String team, String dept, String designation, String unit, String leavedate, String session, String purpose, String remarks) {
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

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getDept() {
        return dept;
    }

    public String getDesignation() {
        return designation;
    }

    public String getUnit() {
        return unit;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public String getSession() {
        return session;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getRemarks() {
        return remarks;
    }
}
