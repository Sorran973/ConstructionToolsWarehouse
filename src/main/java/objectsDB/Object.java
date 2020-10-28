package objectsDB;

import java.io.Serializable;

public class Object  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int objId;
    private String objAddress;

    public Object(){ }

    public Object(int objId, String objAddress) {
        this.objId = objId;
        this.objAddress = objAddress;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public String getObjAddress() {
        return objAddress;
    }

    public void setObjAddress(String objAddress) {
        this.objAddress = objAddress;
    }

    @Override
    public String toString() {
        return "Object{" +
                "objId=" + objId +
                ", objAddress='" + objAddress + '\'' +
                '}';
    }
}
