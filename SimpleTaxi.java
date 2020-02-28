package assignment4;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.*;
import java.util.List;


public class SimpleTaxi extends Actor implements Taxi {
    int id;
    int steps = 0;
    List<Integer> totalSteps = new ArrayList<>();
    List<Request> requestSet = new ArrayList<>();
    boolean companyIsReached = false;


    public SimpleTaxi(int id) {

        this.setColor(Color.GREEN);
        this.id = id;
    }

    @Override
    public Location convertToLocation(String companyName) {

        switch (companyName) {

            case "A":
                return new Location(0, 0);

            case "B":
                return new Location(0, 9);

            case "C":
                return new Location(9, 0);

            case "D":
                return new Location(9, 9);

        }

        return new Location(0, 0);
    }


    public void act() {

        if (!companyIsReached) {

            if (this.canMove()) {

                this.move();
                if (isOccupied()) {

                    steps++;
                }
            } else {

                this.turn();
            }
        } else {

            companyIsReached = false;
        }

        if (!requestSet.isEmpty()) {

            processRequest(requestSet.get(0).originCompanyId, requestSet.get(0).destinationCompanyId);
        }

        if (!isOccupied() && steps != 0 && !requestSet.isEmpty()) {

            requestSet.remove(0);
            totalSteps.add(steps);
            steps = 0;
        }

        if (requestSet.isEmpty()) {

            System.out.println("the taxi id " + id + " at each request order had the following steps: " + totalSteps + "  the total is " + sum(totalSteps));
            this.removeSelfFromGrid();
        }
    }


    int processRequest(String origin, String destination) {

        int[] dirs = {Location.AHEAD, Location.RIGHT, Location.LEFT, Location.HALF_LEFT, Location.HALF_RIGHT,
                Location.HALF_CIRCLE, Location.NORTHWEST, Location.SOUTHEAST, Location.SOUTHWEST, Location.WEST};

        Grid<Actor> gr = this.getGrid();
        Location loc = this.getLocation();
        Location next = loc.getAdjacentLocation(this.getDirection());

        if (gr.isValid(next)) {

            if ((companiesAdjacentLocations(dirs, convertToLocation(origin)).contains(loc)) && !isOccupied()) {

                this.setColor(Color.RED);
                companyIsReached = true;
            }

            if ((companiesAdjacentLocations(dirs, convertToLocation(destination)).contains(loc)) && isOccupied()) {

                this.setColor(Color.GREEN);
                this.moveTo(this.getLocation());
                companyIsReached = true;
            }
        }
        return steps;
    }


    public void turn() {

        this.setDirection(this.getDirection() + 45);
    }

    public void move() {

        Grid<Actor> gr = this.getGrid();
        if (gr != null) {

            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation(this.getDirection());
            if (gr.isValid(next)) {

                this.moveTo(next);

            } else {
                this.removeSelfFromGrid();
            }
        }
    }

    public boolean canMove() {

        Grid<Actor> gr = this.getGrid();
        if (gr == null) {

            return false;
        } else {
            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation(this.getDirection());
            if (!gr.isValid(next)) {

                return false;
            } else {
                Actor neighbor = (Actor) gr.get(next);
                if (neighbor == null || !(neighbor instanceof SimpleTaxi) && !(neighbor instanceof Company)) {

                    return true;
                }
            }
            return false;
        }
    }


    @Override
    public boolean isOccupied() {

        if (this.getColor() == Color.RED)
            return true;
        return false;
    }

    @Override
    public void addRequest(Request request) {

        requestSet.add(request);
    }


    public static int sum(List<Integer> list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

    @Override
    public Set<Location> companiesAdjacentLocations(int[] directions, Location loc) {

        Set<Location> locs = new HashSet<Location>();
        Grid gr = getGrid();
        for (int d : directions) {

            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {

                locs.add(neighborLoc);
            }
        }
        return locs;
    }
}


