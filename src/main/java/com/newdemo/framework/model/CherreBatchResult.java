package com.newdemo.framework.model;

public class CherreBatchResult {
    public String name;
    public String start;
    public String end;
    public int loanCached;
    public String lastPinId;
    public String cacheBuilderStatus;
    public int errorCount;


    public CherreBatchResult() {
        super();
    }

    /**
     * @param name
     * @param start
     * @param end
     * @param loanCached
     * @param lastPinId
     * @param cacheBuilderStatus
     * @param errorCount
     */
    public CherreBatchResult(String name, String start, String end, int loanCached, String lastPinId,
                             String cacheBuilderStatus, int errorCount) {
        super();
        this.name = name;
        this.start = start;
        this.end = end;
        this.loanCached = loanCached;
        this.lastPinId = lastPinId;
        this.cacheBuilderStatus = cacheBuilderStatus;
        this.errorCount = errorCount;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }


    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }


    /**
     * @return the end
     */
    public String getEnd() {
        return end;
    }


    /**
     * @param end the end to set
     */
    public void setEnd(String end) {
        this.end = end;
    }


    /**
     * @return the loanCached
     */
    public int getLoanCached() {
        return loanCached;
    }


    /**
     * @param loanCached the loanCached to set
     */
    public void setLoanCached(int loanCached) {
        this.loanCached = loanCached;
    }


    /**
     * @return the lastPinId
     */
    public String getLastPinId() {
        return lastPinId;
    }


    /**
     * @param lastPinId the lastPinId to set
     */
    public void setLastPinId(String lastPinId) {
        this.lastPinId = lastPinId;
    }


    /**
     * @return the cacheBuilderStatus
     */
    public String getCacheBuilderStatus() {
        return cacheBuilderStatus;
    }

    /**
     * @return the errorCount
     */
    public int getErrorCount() {
        return errorCount;
    }

    /**
     * @param errorCount the errorCount to set
     */
    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * @param cacheBuilderStatus the cacheBuilderStatus to set
     */
    public void setCacheBuilderStatus(String cacheBuilderStatus) {
        this.cacheBuilderStatus = cacheBuilderStatus;
    }

    @Override
    public String toString() {
        return "CherreBatchResult [name=" + name + ", start=" + start + ", end=" + end + ", loanCached=" + loanCached
                + ", lastPinId=" + lastPinId + ", cacheBuilderStatus=" + cacheBuilderStatus + ", errorCount="
                + errorCount + "]";
    }

}
