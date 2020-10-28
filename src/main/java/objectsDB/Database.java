package objectsDB;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static String driver;  //com.microsoft.sqlserver.jdbc.SQLServerDriver
    private static String url;     //jdbc:sqlserver://localhost:1433;database=master
    private static String user;    //sa
    private static String password;//Password1

    public static void setDatabase(String driver, String url, String user, String password) {
        Database.driver = driver;
        Database.url = url;
        Database.user = user;
        Database.password = password;
    }

    public static int function(String instType) {

        int answer = 0;
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "Select dbo.FuncInstOnObj(?) as [answer]";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, instType);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    answer = resultSet.getInt("answer");
                }

                resultSet.close();
                preparedStatement.close();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return answer;
    }
    public static ArrayList<ArrayList> procedure() {

        ArrayList<ArrayList> res = new ArrayList<>();
        ArrayList<String> instType = new ArrayList<>();
        ArrayList<Integer> instAmount = new ArrayList<>();
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("EXECUTE ProcRunningOutOfInst");
                while(resultSet.next()){
                    instType.add(resultSet.getString(1));
                    instAmount.add(resultSet.getInt(2));
                }

                statement.close();
                resultSet.close();
            }
            res.add(instType);
            res.add(instAmount);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return res;
    }

    public static void insertRecord(int supId, String[] instIdList) {

        try{
            String supIdStr = String.valueOf(supId);
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                String sql = "INSERT INTO records (sup_id, inst_id) Values ";
                sql += "("+supIdStr+","+instIdList[0]+")";
                for (int i = 1; i < instIdList.length; i++){
                    sql += ",("+supIdStr+","+instIdList[i]+")";
                }
                try (PreparedStatement preparedStatement1 = conn.prepareStatement(sql)){
                    preparedStatement1.executeUpdate();
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void insertSupply(String address, Date date, String[] instIdList) {
        try{
            int supObjId = 0;
            int supId = 0;
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                String sql = "SELECT obj_id from objects where address =?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, address);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    supObjId = resultSet.getInt("obj_id");
                }
                resultSet.close();
                preparedStatement.close();

                sql = "INSERT INTO supplies (obj_id, date) Values (?,?)";
                try (PreparedStatement preparedStatement1 = conn.prepareStatement(sql)){
                    preparedStatement1.setInt(1, supObjId);
                    preparedStatement1.setDate(2, date);
                    preparedStatement1.executeUpdate();
                }
                sql = "select ident_current('supplies') as [supId]";
                try (PreparedStatement preparedStatement2 = conn.prepareStatement(sql)){
                    ResultSet resultSet1 = preparedStatement2.executeQuery();
                    while (resultSet1.next()){
                        supId = resultSet1.getInt("supId");
                    }
                    resultSet.close();
                }

                Database.insertRecord(supId, instIdList);

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static ArrayList<Instrument> selectInstrumentListBySupply(int id) {

        ArrayList<Instrument> instrumentList = new ArrayList<>();
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM ViewInstSup where sup_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        int instId = resultSet.getInt(3);
                        String instType = resultSet.getString(4);
                        String instName = resultSet.getString(5);
                        int instCondition = resultSet.getInt(6);

                        Instrument instrument = new Instrument(instId, instType, instName,
                                0, instCondition, 0, "");
                        instrumentList.add(instrument);
                    }

                    resultSet.close();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return instrumentList;
    }
    public static void deleteSupply(int id) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "DELETE FROM supplies WHERE sup_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static Supply getSupplyById(int id) {

        Supply supply = null;
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM supplies WHERE sup_id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){

                        int supId = resultSet.getInt(1);
                        int supObjId = resultSet.getInt(2);
                        Date supDate = resultSet.getDate(3);

                        supply = new Supply(supId, supObjId, supDate);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return supply;
    }
    public static void updateSupply(Supply supply, String address) {

        try{
            int supObjId = 0;
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                String sql = "SELECT obj_id from objects where address =?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, address);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    supObjId = resultSet.getInt("obj_id");
                }

                resultSet.close();
                preparedStatement.close();

                sql = "UPDATE supplies SET obj_id = ?, date = ? WHERE sup_id = ?";
                try (PreparedStatement preparedStatement1 = conn.prepareStatement(sql)){
                    preparedStatement1.setInt(1, supObjId);
                    preparedStatement1.setDate(2, supply.getSupDate());
                    preparedStatement1.setInt(3, supply.getSupId());

                    preparedStatement1.executeUpdate();
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static ArrayList<Supply> selectSupplyList() {

        HashMap<Integer, Object> objectHashMap = Database.getObjectHashMap();
        ArrayList<Supply> supplyList = new ArrayList<>();
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM supplies");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    int objId = resultSet.getInt(2);
                    Date date = resultSet.getDate(3);
                    Supply supply = new Supply(id, objId, date);
                    supply.setSupAddress(objectHashMap.get(objId).getObjAddress());
                    supplyList.add(supply);
                }

                statement.close();
                resultSet.close();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return supplyList;
    }
    public static HashMap<Integer, Object> getObjectHashMap() {
        ArrayList<Object> objectArrayList = Database.selectObjectList();
        HashMap<Integer, Object> objectMap = new HashMap<>();
        for (Object object: objectArrayList){
            objectMap.put(object.getObjId(), object);
        }

        return objectMap;
    }

    public static void LoadImageInstrument(int instId, InputStream inputStream) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "update instruments set instruments.image = ? where inst_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setBinaryStream(1, inputStream);
                    preparedStatement.setInt(2, instId);

                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static byte[] getImageInstrumentById(int id) {
        byte[] instId = new byte[0];
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT image FROM instruments WHERE inst_id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        instId = resultSet.getBytes(1);
                    }

                    resultSet.close();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return instId;
    }
    public static Instrument getInstrumentById(int id) {
        Instrument instrument = null;
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM instruments WHERE inst_id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        int instId = resultSet.getInt(1);
                        String instType = resultSet.getString(2);
                        String instName = resultSet.getString(3);
                        int instPrice = resultSet.getInt(5);
                        int instCondition = resultSet.getInt(6);
                        int instLocation = resultSet.getInt(7);
                        String instDescription = resultSet.getString(8);

                        instrument = new Instrument(instId,
                                instType, instName, instPrice, instCondition,
                                instLocation, instDescription);
                    }

                    resultSet.close();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return instrument;
    }
    public static void deleteInstrument(int id) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "DELETE FROM instruments WHERE inst_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void updateInstrumentWithoutInputStream(Instrument instrument) {
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "UPDATE instruments SET type = ?, name = ?, price = ?, condition = ?," +
                        "location = ?, description = ? WHERE inst_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, instrument.getInstType());
                    preparedStatement.setString(2, instrument.getInstName());
                    preparedStatement.setInt(3, instrument.getInstPrice());
                    preparedStatement.setInt(4, instrument.getInstCondition());
                    preparedStatement.setInt(5, instrument.getInstLocation());
                    preparedStatement.setString(6, instrument.getInstDescription());
                    preparedStatement.setInt(7, instrument.getInstId());

                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void updateInstrument(Instrument instrument, InputStream inputStream) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "UPDATE instruments SET type = ?, name = ?, price = ?, condition = ?," +
                        "location = ?, description = ? WHERE inst_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, instrument.getInstType());
                    preparedStatement.setString(2, instrument.getInstName());
                    preparedStatement.setInt(3, instrument.getInstPrice());
                    preparedStatement.setInt(4, instrument.getInstCondition());
                    preparedStatement.setInt(5, instrument.getInstLocation());
                    preparedStatement.setString(6, instrument.getInstDescription());
                    preparedStatement.setInt(7, instrument.getInstId());

                    preparedStatement.executeUpdate();
                }
                if (!inputStream.markSupported()) {
                    sql = "update instruments set instruments.image = ? where inst_id = ?";
                    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                        preparedStatement.setBinaryStream(1, inputStream);
                        preparedStatement.setInt(2, instrument.getInstId());

                        preparedStatement.executeUpdate();
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void insertInstrument(Instrument instrument, InputStream inputStream) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "INSERT INTO instruments (type, name, image, price, description) Values (?, ?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, instrument.getInstType());
                    preparedStatement.setString(2, instrument.getInstName());
                    preparedStatement.setBinaryStream(3, inputStream);
                    preparedStatement.setInt(4, instrument.getInstPrice());
                    preparedStatement.setString(5, instrument.getInstDescription());

                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static ArrayList<Instrument> selectInstrumentList() {

        ArrayList<Instrument> instrumentList = new ArrayList<>();
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT inst_id, type, name, " +
                        "price, condition, location, description FROM instruments");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String type = resultSet.getString(2);
                    String name = resultSet.getString(3);
                    int price = resultSet.getInt(4);
                    int condition = resultSet.getInt(5);
                    int location = resultSet.getInt(6);
                    String description = resultSet.getString(7);

                    Instrument instrument = new Instrument(id, type, name, price, condition, location, description);
                    instrumentList.add(instrument);
                }

                statement.close();
                resultSet.close();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return instrumentList;
    }

    public static Object getObjectById(int id) {

        Object object = null;
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "SELECT * FROM objects WHERE obj_id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int objId = resultSet.getInt(1);
                        String objAddress = resultSet.getString(2);
                        object = new Object(objId, objAddress);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return object;
    }
    public static void deleteObject(int id) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "DELETE FROM objects WHERE obj_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void updateObject(Object object) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "UPDATE objects SET address = ? WHERE obj_id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, object.getObjAddress());
                    preparedStatement.setInt(2, object.getObjId());

                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void insertObject(String address) {

        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                String sql = "INSERT INTO objects (address) Values (?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, address);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static ArrayList<Object> selectObjectList() {

        ArrayList<Object> objectList = new ArrayList<>();
        try{
            Class.forName(driver).getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM objects");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String address = resultSet.getString(2);
                    Object object = new Object(id, address);
                    objectList.add(object);
                }

                statement.close();
                resultSet.close();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return objectList;
    }
}
