package exam.dao;


import exam.entity.Galaxy;
import exam.entity.Planet;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanetDao {
    private static final String jdbcDriverName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:7777/planets";
    private static final String user = "postgres";
    private static final String password = "12345";
    private static Logger logger = Logger.getLogger(PlanetDao.class.getName());


    public static ArrayList<Planet> getPlanetsByGalaxyName(String name) { // 1
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select * " +
                    "from planet " +
                    "         inner join satellite on planet.id = satellite.planet_id " +
                    "         inner join galaxy on planet.galaxy_id = galaxy.id " +
                    "where galaxy.name = ? and planet.life = true;";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Planet> planets = new ArrayList<>();
            while (rs.next()) {
                planets.add(Planet.map(rs));
            }
            connection.close();
            return planets;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return new ArrayList<Planet>();
        }
    }

    public static String getPlanetWithMinRadiusMaxCountOdSatellites() { //2
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select planet.planet_name, count(satellite.id), planet.radius\n" +
                    "from planet\n" +
                    "inner join satellite on satellite.planet_id = planet.id\n" +
                    "inner join galaxy on planet.galaxy_id = galaxy.id\n" +
                    "group by planet.planet_name\n" +
                    "order by count(satellite.id)\n" +
                    "order by planet.radius ASC\n" +
                    "limit 1;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Planet planet = new Planet();
            if (rs.next()) {
                planet.setPlanetName(rs.getString(1));
            }
            connection.close();
            return planet.getPlanetName();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<String> getPlanetAndGalaxy() { //3
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select planet.planet_name, galaxy.name ,count(satellite.id)\n" +
                    "from planet\n" +
                    "inner join satellite on satellite.planet_id = planet.id\n" +
                    "inner join galaxy on planet.galaxy_id = galaxy.id\n" +
                    "group by planet.planet_name\n" +
                    "order by count(satellite.id) DESC\n" +
                    "limit 1;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String> result = new ArrayList<>();
            if (rs.next()) {
                result.add(rs.getString(1));
                result.add(rs.getString(2));
            }
            connection.close();
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static Galaxy getGalaxyWIthHottestPlanets() { //4
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "select galaxy.id, galaxy.name, sum(planet.temperature)\n" +
                    "from planet\n" +
                    "inner join galaxy on planet.galaxy_id = galaxy.id\n" +
                    "group by galaxy.name\n" +
                    "order by sum(planet.temperature) DESC\n" +
                    "limit 1;\n";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Galaxy galaxy = new Galaxy();
            if (rs.next()) {
                galaxy.map(rs);
            }
            connection.close();
            return galaxy;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return new Galaxy();
        }
    }


}
