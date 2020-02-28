package assignment4;

import info.gridworld.actor.Actor;

import java.awt.*;

public class Company extends Actor {

    static String id;

    public Company(Color companyColor, String id) {

        this.setColor(companyColor);
        this.id = id;
    }

    public void act() {
    }


}
