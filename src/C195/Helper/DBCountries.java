package C195.Helper;

import C195.Entities.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {

    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM Countries;";
            PreparedStatement countryPS = JDBC.getConnection().prepareStatement(sql);
            ResultSet results = countryPS.executeQuery();

            while(results.next()){
                int countryID = results.getInt("Country_ID");
                String countryName = results.getString("Country");
                Country country = new Country(countryID, countryName);
                countryList.add(country);
            }

        }
        catch(SQLException throwable){
            throwable.printStackTrace();
        }

        return countryList;
    }

    public static void addCountry(int id, String name){
        String sql = "INSERT into Countries(Country_ID, Country) Values("+ id +", '"+name+"');";
        try {
            //prepare the sql stmt
            PreparedStatement countryPS = JDBC.getConnection().prepareStatement(sql);
            //execute the sql command
             countryPS.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
