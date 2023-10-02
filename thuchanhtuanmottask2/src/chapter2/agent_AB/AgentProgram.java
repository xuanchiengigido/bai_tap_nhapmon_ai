package chapter2.agent_AB;

import java.util.Random;

public class AgentProgram {

    public Action execute(Percept p) {// location, status
        //TODO
        Random rd = new Random();
        int number = rd.nextInt(2);
        System.out.println(number);
        if (p.getLocationState() == Environment.LocationState.DIRTY) {
            return Environment.SUCK_DIRT;
        } else if (p.getAgentLocation() == Environment.LOCATION_A) {
            if (number == 1) {
                return Environment.MOVE_RIGHT;
            } else {
                return Environment.MOVE_DOWN;
            }
        } else if (p.getAgentLocation() == Environment.LOCATION_B) {
            if (number == 1) {
                return Environment.MOVE_DOWN;
            } else {
                return Environment.MOVE_LEFT;
            }
        } else if (p.getAgentLocation() == Environment.LOCATION_C) {
            if (number == 1) {
                return Environment.MOVE_UP;
            } else {
                return Environment.MOVE_RIGHT;
            }
        } else if (p.getAgentLocation() == Environment.LOCATION_D) {
            if (number == 1) {
                return Environment.MOVE_UP;
            } else {
                return Environment.MOVE_LEFT;
            }
        }
        return NoOpAction.NO_OP;
    }
}