package exam.entity;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Satellite {
    private int id;
    private String name;
    private float radius;
    private float distance;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getRadius() {
        return radius;
    }

    public float getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", radius=" + radius +
                ", distance=" + distance +
                '}';
    }

    private static Satellite map(ResultSet rs) throws SQLException {
        Satellite satellite = new Satellite();
        satellite.setId(rs.getInt(1));
        satellite.setName(rs.getString(2));
        satellite.setRadius(rs.getFloat(3));
        satellite.setDistance(rs.getFloat(4));
        return satellite;
    }
}
