package com.cisex.qd.reports;

/**
 * Created by vezhou.
 * Date: 2012-8-13
 * Time: 15:51:33
 */
public class TestStats {
    private int totalCase;
    private int failedCase;

    private float passrate;

    private String projectCode;
    private String buildNumber;

    private String dateCreated;



    public TestStats(String projectCode, String buildNumber, int totalCase, int failedCase, String dateCreated) {
        this.projectCode = projectCode;
        this.totalCase = totalCase;
        this.failedCase = failedCase;
        this.buildNumber = buildNumber;
        this.dateCreated = dateCreated;



        this.passrate = 0;

        if(this.totalCase != 0)
            this.passrate = (this.totalCase - this.failedCase) * 1.0f / this.totalCase;

        this.passrate = Math.round(this.passrate * 10000) * 1.0f / 100;
    }

    public int getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(int totalCase) {
        this.totalCase = totalCase;
    }

    public int getFailedCase() {
        return failedCase;
    }

    public void setFailedCase(int failedCase) {
        this.failedCase = failedCase;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public float getPassrate() {
        return this.passrate;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
