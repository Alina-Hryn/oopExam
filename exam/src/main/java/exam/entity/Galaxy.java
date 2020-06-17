package exam.entity;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Galaxy {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Galaxy map(ResultSet rs) throws SQLException {
        Galaxy galaxy = new Galaxy();
        galaxy.setId(rs.getInt(1));
        galaxy.setName(rs.getString(2));
        return galaxy;
    }
}
