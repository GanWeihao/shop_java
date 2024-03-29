package org.taru.lanqiao.model;

import java.util.Date;

public class Sign {
    private String signId;

    private String signAdmin;

    private String signCustId;

    private String signContent;

    private String signContentOne;

    private String signContentTwo;

    private String signContentThree;

    private String signContentFourth;

    private Date signDate;

    private String signKpi;

    public Sign(String signId, String signAdmin, String signCustId, String signContent, String signContentOne, String signContentTwo, String signContentThree, String signContentFourth, Date signDate, String signKpi) {
        this.signId = signId;
        this.signAdmin = signAdmin;
        this.signCustId = signCustId;
        this.signContent = signContent;
        this.signContentOne = signContentOne;
        this.signContentTwo = signContentTwo;
        this.signContentThree = signContentThree;
        this.signContentFourth = signContentFourth;
        this.signDate = signDate;
        this.signKpi = signKpi;
    }

    public Sign() {
        super();
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId == null ? null : signId.trim();
    }

    public String getSignAdmin() {
        return signAdmin;
    }

    public void setSignAdmin(String signAdmin) {
        this.signAdmin = signAdmin == null ? null : signAdmin.trim();
    }

    public String getSignCustId() {
        return signCustId;
    }

    public void setSignCustId(String signCustId) {
        this.signCustId = signCustId == null ? null : signCustId.trim();
    }

    public String getSignContent() {
        return signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent == null ? null : signContent.trim();
    }

    public String getSignContentOne() {
        return signContentOne;
    }

    public void setSignContentOne(String signContentOne) {
        this.signContentOne = signContentOne == null ? null : signContentOne.trim();
    }

    public String getSignContentTwo() {
        return signContentTwo;
    }

    public void setSignContentTwo(String signContentTwo) {
        this.signContentTwo = signContentTwo == null ? null : signContentTwo.trim();
    }

    public String getSignContentThree() {
        return signContentThree;
    }

    public void setSignContentThree(String signContentThree) {
        this.signContentThree = signContentThree == null ? null : signContentThree.trim();
    }

    public String getSignContentFourth() {
        return signContentFourth;
    }

    public void setSignContentFourth(String signContentFourth) {
        this.signContentFourth = signContentFourth == null ? null : signContentFourth.trim();
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignKpi() {
        return signKpi;
    }

    public void setSignKpi(String signKpi) {
        this.signKpi = signKpi == null ? null : signKpi.trim();
    }
}