package exam.entity;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Planet {
    private int id;
    private String planetName;
    private float radius;
    private float temperature;
    private boolean atmosphere;
    private boolean life;

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setAtmosphere(boolean atmosphere) {
        this.atmosphere = atmosphere;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public float getRadius() {
        return radius;
    }

    public float getTemperature() {
        return temperature;
    }

    public boolean isAtmosphere() {
        return atmosphere;
    }

    public boolean isLife() {
        return life;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", planetName='" + planetName + '\'' +
                ", radius=" + radius +
                ", temperature=" + temperature +
                ", atmosphere=" + atmosphere +
                ", life=" + life +
                '}';
    }

    public static Planet map(ResultSet rs) throws SQLException {
        Planet planet = new Planet();
        planet.setId(rs.getInt(1));
        planet.setPlanetName(rs.getString(2));
        planet.setRadius(rs.getFloat(3));
        planet.setTemperature(rs.getFloat(4));
        return planet;
    }
}
