package assignment4;

import info.gridworld.grid.Location;

import java.util.List;
import java.util.Set;

public interface Taxi {

    boolean isOccupied();
    void addRequest(Request request);
    Set<Location> companiesAdjacentLocations(int[] directions, Location loc);
    Location convertToLocation(String companyName);

}
