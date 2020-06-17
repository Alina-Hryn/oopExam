package exam;

import exam.dao.PlanetDao;
import exam.entity.Galaxy;
import exam.entity.Planet;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter galaxy:");
        Scanner scanner = new Scanner(System.in);
        String region = scanner.nextLine();
        ArrayList<Planet> planets = PlanetDao.getPlanetsByGalaxyName(region);
        for (Planet w : planets) {
            System.out.println(w.toString());
        }
        scanner.close();
        System.out.println("Planet with the less radius and max number of satellites");
        System.out.println(PlanetDao.getPlanetWithMinRadiusMaxCountOdSatellites());
        System.out.println("Galaxy and planet in it with max number of satellites");
        ArrayList<String> res = PlanetDao.getPlanetAndGalaxy();
        System.out.println("Planet:\n" + res.get(0) + "\nGalaxy:\n" + res.get(1));
        System.out.println("Galaxy in which sum of planet temperature is max");
        Galaxy galaxy = PlanetDao.getGalaxyWIthHottestPlanets();
        System.out.println(galaxy.toString());
    }

}

