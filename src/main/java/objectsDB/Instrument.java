package objectsDB;

import java.io.Serializable;

public class Instrument implements Serializable {
    private static final long serialVersionUID = 1L;

    private int instId;
    private String instType;
    private String instName;
    private byte[] image;
    private int instPrice;
    private int instCondition;
    private int instLocation;
    private String instDescription;

    public Instrument(){ }

    public Instrument(int instId, String instType, String instName, int instPrice, int instCondition, int instLocation, String instDescription) {
        this.instId = instId;
        this.instType = instType;
        this.instName = instName;
        this.instPrice = instPrice;
        this.instCondition = instCondition;
        this.instLocation = instLocation;
        this.instDescription = instDescription;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getInstId() {
        return instId;
    }

    public void setInstId(int instId) {
        this.instId = instId;
    }

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public int getInstPrice() {
        return instPrice;
    }

    public void setInstPrice(int instPrice) {
        this.instPrice = instPrice;
    }

    public int getInstCondition() {
        return instCondition;
    }

    public void setInstCondition(int instCondition) {
        this.instCondition = instCondition;
    }

    public int getInstLocation() {
        return instLocation;
    }

    public void setInstLocation(int instLocation) {
        this.instLocation = instLocation;
    }

    public String getInstDescription() {
        return instDescription;
    }

    public void setInstDescription(String instDescription) {
        this.instDescription = instDescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
