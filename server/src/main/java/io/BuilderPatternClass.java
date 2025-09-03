package io;

import collection.*;
import collection.collectionWorking.CollectionManager;
import collection.collectionWorking.interfaceCollection.WorkWithCollection;


import java.util.Queue;

public class Builder {

    public Vehicle buildVehicle(Queue<Object> queue) {
        String name = (String) queue.remove();
        int x = (int) queue.remove();
        float y = (float) queue.remove();
        Coordinates coordinates = new Coordinates(x, y);
        int enginePower = (int) queue.remove();
        VehicleType vehicleType = VehicleType.getVehicleType((String) queue.remove());
        FuelType fuelType = FuelType.getFuelType((String) queue.remove());
        WorkWithCollection handler = new CollectionManager();
        return new Vehicle(name, coordinates, enginePower, vehicleType, fuelType);
    }
}

