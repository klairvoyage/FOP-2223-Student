package projekt.delivery.service;

import projekt.base.Location;
import projekt.delivery.event.Event;
import projekt.delivery.routing.ConfirmedOrder;
import projekt.delivery.routing.Vehicle;
import projekt.delivery.routing.VehicleManager;

import java.util.*;
import java.util.function.Consumer;

import static org.tudalgo.algoutils.student.Student.crash;

public class OurDeliveryService extends AbstractDeliveryService {

    protected final List<ConfirmedOrder> pendingOrders = new ArrayList<>();

    public OurDeliveryService(VehicleManager vehicleManager) {
        super(vehicleManager);
    }

    public class HelperClass{public static long helper;}
    @Override
    protected List<Event> tick(long currentTick, List<ConfirmedOrder> newOrders) {
        BasicDeliveryService.HelperClass.helper=currentTick;
        List<Event> toReturn=vehicleManager.tick(currentTick);
        for(ConfirmedOrder order:newOrders) pendingOrders.add(order);
        Collections.sort(pendingOrders, (o1, o2) -> {
            if(o1.getDeliveryInterval().start()<o2.getDeliveryInterval().start()) return -1;
            if(o1.getDeliveryInterval().start()>o2.getDeliveryInterval().start()) return 1;
            return 0;
        });
        // accept orders in restaurants
        VehicleManager.OccupiedRestaurant res;
        boolean loaded;

        Map<Vehicle,List<ConfirmedOrder>> listOfOrders=new HashMap<>(); // a priori, we know all orders that are
        // in a car are from the same restaurant
        // (within each function call)

        for(int i=0;i<pendingOrders.size();i++) {
            res=pendingOrders.get(i).getRestaurant();
            if (vehicleManager.getOccupiedRestaurants().contains(res)){
                loaded=false;
                for(Vehicle veh:res.getVehicles()){
                    if(!loaded&&veh.getCapacity()>=veh.getCurrentWeight()+pendingOrders.get(i).getWeight()){
                        loaded=true;
                        res.loadOrder(veh,pendingOrders.get(i),currentTick);
                        if(listOfOrders.get(veh)==null) listOfOrders.put(veh,new ArrayList<>());
                        listOfOrders.get(veh).add(pendingOrders.get(i));
                        pendingOrders.remove(i);
                        i--;
                    }
                }
            }
        }
        List<Location> destinations=new ArrayList<>();
        List<List<ConfirmedOrder>> orders=new ArrayList<>();
        // deliver orders
        Consumer<? super Vehicle> consumer;
        for(Vehicle veh:listOfOrders.keySet()){
            destinations.clear();
            orders.clear();
            for(ConfirmedOrder order:listOfOrders.get(veh)){
                if(!destinations.contains(order.getLocation())){
                    destinations.add(order.getLocation());
                    orders.add(new ArrayList<>());
                }
                orders.get(destinations.indexOf(order.getLocation())).add(order);
            }
            for(int i=0;i<destinations.size();i++){
                List<ConfirmedOrder> locationSpecificOrders = new ArrayList<>(orders.get(i));
                consumer=new Consumer<Vehicle>() {
                    @Override
                    public void accept(Vehicle vehicle) {
                        // VERY SUSPICIOUS
                        VehicleManager.OccupiedNeighborhood dest=vehicleManager.getOccupiedNeighborhood(
                            vehicleManager.getRegion().getNode(locationSpecificOrders.get(0).getLocation()));
                        for(ConfirmedOrder order:locationSpecificOrders)
                            dest.deliverOrder(vehicle,order, BasicDeliveryService.HelperClass.helper);              // THIS IS LIKELY WRONG!!!
                        if(vehicle.getOrders().isEmpty()&&!(vehicle.getOccupied().getComponent()
                            instanceof projekt.delivery.routing.Region.Node myNode &&
                            myNode.equals(vehicleManager.getRegion().getNode(locationSpecificOrders.get(0).getLocation()))))
                            vehicle.moveDirect(vehicleManager.getRegion().getNode(
                            locationSpecificOrders.get(0).getLocation()));
                    }
                };
                veh.moveQueued(vehicleManager.getRegion().getNode(destinations.get(i)),consumer);
                //move back to restaurant?
                veh.moveQueued(orders.get(i).get(0).getRestaurant().getComponent());
            }
        }
        return toReturn;
    }

    @Override
    public List<ConfirmedOrder> getPendingOrders() {
        return pendingOrders;
    }

    @Override
    public void reset() {
        super.reset();
        pendingOrders.clear();
    }

    public interface Factory extends DeliveryService.Factory {

        OurDeliveryService create(VehicleManager vehicleManager);
    }
}
