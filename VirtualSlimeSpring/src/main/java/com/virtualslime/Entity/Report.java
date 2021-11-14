package com.virtualslime.Entity;

enum ReportType{
    UNDEFINED,ITEM, USER, COMMENT
}

enum ReportState{
    CREATED, PROCESSING, FINISHED
}

public class Report {
    private int rid;//user_report.rid unsigned int
    private int uid;//user_report.uid unsigned int
    private ReportType reportType;//user_report.type tinyint unsigned
    private int idTarget;//user_report.target unsigned int
    private String reportContent;//user_report.content varchar(50)
    private int handledBy;//user_report.handled_by unsigned int
    private String handleResult;//user_report.handle_result varchar(50)
    private ReportState reportState;//user_report.state unsigned tinyint

    public Report() {
        this.rid = 0;
        this.rid = 0;
        this.uid = 0;
        this.reportType = ReportType.UNDEFINED;
        this.idTarget = 0;
        this.reportContent = "null";
        this.handledBy = 0;
        this.handleResult = "null";
        this.reportState = ReportState.CREATED;

    }

    public Report(int uid, ReportType reportType, int idTarget, String reportContent) {
        this.uid = uid;
        this.reportType = reportType;
        this.idTarget = idTarget;
        this.reportContent = reportContent;
        this.handledBy = 0;
        this.handleResult = "null";
        this.reportState = ReportState.CREATED;
    }

    public Report(int rid, int uid, ReportType reportType, int idTarget, String reportContent,
                  int handledBy, String handleResult, ReportState reportState) {
        this.rid = rid;
        this.uid = uid;
        this.reportType = reportType;
        this.idTarget = idTarget;
        this.reportContent = reportContent;
        this.handledBy = handledBy;
        this.handleResult = handleResult;
        this.reportState = reportState;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public int getIdTarget() {
        return idTarget;
    }

    public void setIdTarget(int idTarget) {
        this.idTarget = idTarget;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public int getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(int handledBy) {
        this.handledBy = handledBy;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public ReportState getReportState() {
        return reportState;
    }

    public void setReportState(ReportState reportState) {
        this.reportState = reportState;
    }
}
