package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.ReportState;
import com.virtualSlime.Enum.EntityType.ReportType;

@TableName(schema = "virtual_slime", value = "user_report")
public class Report {
    @TableId(type = IdType.AUTO)
    private final Integer rid;
    private Integer uid;
    private ReportType reportType;
    private Integer idTarget;
    private String reportContent;//<= 50
    private Integer handledBy;
    private String handleResult;//<= 50
    private ReportState reportState;

    public Report(Integer uid, ReportType reportType, Integer idTarget, String reportContent) {
        this.rid = null;
        this.uid = uid;
        this.reportType = reportType;
        this.idTarget = idTarget;
        this.reportContent = reportContent;
    }

    public Report(Integer rid, Integer uid, ReportType reportType, Integer idTarget,
                  String reportContent, Integer handledBy, String handleResult,
                  ReportState reportState) {
        this.rid = rid;
        this.uid = uid;
        this.reportType = reportType;
        this.idTarget = idTarget;
        this.reportContent = reportContent;
        this.handledBy = handledBy;
        this.handleResult = handleResult;
        this.reportState = reportState;
    }

    public Integer getRid() {
        return rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public Integer getIdTarget() {
        return idTarget;
    }

    public void setIdTarget(Integer idTarget) {
        this.idTarget = idTarget;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Integer getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(Integer handledBy) {
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

    @Override
    public String toString() {
        return "Report{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", reportType=" + reportType +
                ", idTarget=" + idTarget +
                ", reportContent='" + reportContent + '\'' +
                ", handledBy=" + handledBy +
                ", handleResult='" + handleResult + '\'' +
                ", reportState=" + reportState +
                '}';
    }
}
