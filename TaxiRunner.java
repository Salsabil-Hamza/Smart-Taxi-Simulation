package assignment4;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.*;


public class TaxiRunner {

    public static void main(String[] args) {

        BoundedGrid grid = new BoundedGrid(10, 10);
        ActorWorld world = new ActorWorld(grid);
        RequestReader requestReader = new RequestReader();

        requestReader.reading();
        System.out.println("the request list in ascending order");
        System.out.println(requestReader.data);

        Company company1 = new Company(Color.RED, "A");
        world.add(new Location(0, 0), company1);
        Company company2 = new Company(Color.CYAN, "B");
        world.add(new Location(0, 9), company2);
        Company company3 = new Company(Color.ORANGE, "C");
        world.add(new Location(9, 0), company3);
        Company company4 = new Company(Color.magenta, "D");
        world.add(new Location(9, 9), company4);


        SimpleTaxi taxi1 = new SimpleTaxi(1);
        world.add(new Location(4, 4), taxi1);
        SimpleTaxi taxi2 = new SimpleTaxi(2);
        world.add(new Location(4, 5), taxi2);
        SimpleTaxi taxi3 = new SimpleTaxi(3);
        world.add(new Location(5, 4), taxi3);
        SimpleTaxi taxi4 = new SimpleTaxi(4);
        world.add(new Location(5, 5), taxi4);

        /* uncomment to run the smart taxi */

//        SmartTaxi taxi1 = new SmartTaxi(1);
//        world.add(new Location(4, 4), taxi1);
//        SmartTaxi taxi2 = new SmartTaxi(2);
//        world.add(new Location(4, 5), taxi2);
//        SmartTaxi taxi3 = new SmartTaxi(3);
//        world.add(new Location(5, 4), taxi3);
//        SmartTaxi taxi4 = new SmartTaxi(4);
//        world.add(new Location(5, 5), taxi4);


        for (Request request : requestReader.data) {

            if (request.requestTaxiId == taxi1.id) {
                taxi1.addRequest(request);
            }

            if (request.requestTaxiId == taxi2.id) {
                taxi2.addRequest(request);
            }

            if (request.requestTaxiId == taxi3.id) {
                taxi3.addRequest(request);
            }

            if (request.requestTaxiId == taxi3.id) {
                taxi4.addRequest(request);
            }
        }
        world.show();
    }
}
