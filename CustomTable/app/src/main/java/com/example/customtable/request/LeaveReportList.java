package com.example.customtable;

public class LeaveReportList {

    String Empno,LeaveDate,Seesion,Raeson,IsApproved,IsRejected;

    public LeaveReportList(String empno, String leaveDate, String seesion, String raeson, String isApproved, String isRejected) {
        Empno = empno;
        LeaveDate = leaveDate;
        Seesion = seesion;
        Raeson = raeson;
        IsApproved = isApproved;
        IsRejected = isRejected;
    }

    public String getEmpno() {
        return Empno;
    }

    public void setEmpno(String empno) {
        Empno = empno;
    }

    public String getLeaveDate() {
        return LeaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        LeaveDate = leaveDate;
    }

    public String getSeesion() {
        return Seesion;
    }

    public void setSeesion(String seesion) {
        Seesion = seesion;
    }

    public String getRaeson() {
        return Raeson;
    }

    public void setRaeson(String raeson) {
        Raeson = raeson;
    }

    public String getIsApproved() {
        return IsApproved;
    }

    public void setIsApproved(String isApproved) {
        IsApproved = isApproved;
    }

    public String getIsRejected() {
        return IsRejected;
    }

    public void setIsRejected(String isRejected) {
        IsRejected = isRejected;
    }
}
