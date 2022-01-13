package au.com.openmarkets;

import java.util.Objects;

public class ReportDetail {


    public String txnId;
    public String timestampString;
    public Integer account;
    public String adviser;
    public Double value;
    public Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDetail report = (ReportDetail) o;
        return Objects.equals(txnId, report.txnId) && Objects.equals(timestampString, report.timestampString) && Objects.equals(account, report.account) && Objects.equals(adviser, report.adviser) && Objects.equals(value, report.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(txnId, timestampString, account, adviser, value);
    }

    @Override
    public String toString() {
        return "Report{" +
                "txnId='" + txnId + '\'' +
                ", timestamp='" + timestampString + '\'' +
                ", account=" + account +
                ", adviser='" + adviser + '\'' +
                ", value=" + value +
                '}';
    }
}
