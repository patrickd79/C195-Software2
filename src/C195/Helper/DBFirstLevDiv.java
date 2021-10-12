package C195.Helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevDiv {
    public static ObservableList<String> getDivNames(int countryId){
        ObservableList<String> divNames= FXCollections.observableArrayList();
        String sqlStmt = "Select Division_ID, Division from FIRST_LEVEL_DIVISIONS where Country_ID = "+countryId+";";
        try {
            //prepare the sql stmt
            PreparedStatement fldPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            fldPS.execute();
            ResultSet results = fldPS.executeQuery();

            while(results.next()){
                int divId = results.getInt("Division_ID");
                String divName = results.getString("Division");
                //FLDivision fld = new FLDivision(divId, divName);
                divNames.add(divName);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return divNames;
    }

    public static String getDivID(String divName) throws SQLException {
        ResultSet results = null;
        int divID = 0;
        String sqlStmt = "Select Division_ID from FIRST_LEVEL_DIVISIONS where Division = '"+divName+"';";
        try {
            //prepare the sql stmt
            PreparedStatement fldPS = JDBC.getConnection().prepareStatement(sqlStmt);
            //execute the sql command
            fldPS.execute();
            results = fldPS.executeQuery();
            while(results.next()) {
                divID = results.getInt("Division_ID");
                }
            } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return String.valueOf(divID);
    }





    /*public static void addCountry(String name, Date createdDate){

        String sql = "INSERT into Countries(Country, Create_Date) Values('"+name+"', '"+createdDate+"');";
        try {
            //prepare the sql stmt
            PreparedStatement countryPS = JDBC.getConnection().prepareStatement(sql);
            //execute the sql command
             countryPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }*/
}
