package objectsDB;

import java.io.Serializable;
import java.sql.Date;

public class Supply implements Serializable {
    private static final long serialVersionUID = 1L;

    private int supId;
    private int supObjId;
    private Date supDate;

    private String supAddress;

    public Supply() {}

    public Supply(int supId, int supObjId, Date supDate) {
        this.supId = supId;
        this.supObjId = supObjId;
        this.supDate = supDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSupAddress() {
        return supAddress;
    }

    public void setSupAddress(String supAddress) {
        this.supAddress = supAddress;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public int getSupObjId() {
        return supObjId;
    }

    public void setSupObjId(int supObjId) {
        this.supObjId = supObjId;
    }

    public Date getSupDate() {
        return supDate;
    }

    public void setSupDate(Date supDate) {
        this.supDate = supDate;
    }
}
