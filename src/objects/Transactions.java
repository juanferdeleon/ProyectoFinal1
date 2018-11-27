package objects;

import java.sql.Date;

public class Transactions {

    private Integer acctNo;
    private Integer amountWithdrawn;
    private Date date;

    public Integer getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(Integer acctNo) {
        this.acctNo = acctNo;
    }

    public Integer getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public void setAmountWithdrawn(Integer amountWithdrawn) {
        this.amountWithdrawn = amountWithdrawn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
