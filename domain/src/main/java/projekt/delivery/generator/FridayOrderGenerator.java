package projekt.delivery.generator;

import projekt.base.TickInterval;
import projekt.delivery.routing.ConfirmedOrder;
import projekt.delivery.routing.VehicleManager;

import java.util.*;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * An implementation of an {@link OrderGenerator} that represents the incoming orders on an average friday evening.
 * The incoming orders follow a normal distribution.<p>
 *
 * To create a new {@link FridayOrderGenerator} use {@code FridayOrderGenerator.Factory.builder()...build();}.
 */
public class FridayOrderGenerator implements OrderGenerator {

    private final Random random;
    private final Map<Long,List<ConfirmedOrder>> orders;
    /**
     * Creates a new {@link FridayOrderGenerator} with the given parameters.
     * @param orderCount The total amount of orders this {@link OrderGenerator} will create. It is equal to the sum of
     *                   the size of the lists that are returned for every positive long value.
     * @param vehicleManager The {@link VehicleManager} this {@link OrderGenerator} will create orders for.
     * @param deliveryInterval The amount of ticks between the start and end tick of the deliveryInterval of the created orders.
     * @param maxWeight The maximum weight of a created order.
     * @param variance The variance of the normal distribution.
     * @param lastTick The last tick this {@link OrderGenerator} can return a non-empty list.
     * @param seed The seed for the used {@link Random} instance. If negative a random seed will be used.
     */
    private FridayOrderGenerator(int orderCount, VehicleManager vehicleManager, int deliveryInterval, double maxWeight, double variance, long lastTick, int seed) {
        random = seed < 0 ? new Random() : new Random(seed);
        if(orderCount<0) throw new IndexOutOfBoundsException(orderCount);
        orders=new HashMap();
        double dIndex;
        long lIndex;
        ConfirmedOrder order;
        VehicleManager.OccupiedNeighborhood[] neighborhoods=vehicleManager.getOccupiedNeighborhoods()
            .toArray(new VehicleManager.OccupiedNeighborhood[0]);
        VehicleManager.OccupiedRestaurant[] restaurants=vehicleManager.getOccupiedRestaurants()
            .toArray(new VehicleManager.OccupiedRestaurant[0]);
        VehicleManager.OccupiedRestaurant res;
        List<String> foodList=new ArrayList();
        String[] resList;
        int number_of_foods;

        for(int i=0;i<orderCount;i++){
            do{
                dIndex=random.nextGaussian(lastTick/2.0,variance);
            } while(dIndex<0||dIndex>=lastTick+1);
            lIndex=(long)dIndex;
            res=restaurants[random.nextInt(restaurants.length)];
            foodList.clear();
            resList=res.getComponent().getAvailableFood().toArray(new String[0]);
            for(number_of_foods=1+random.nextInt(9);number_of_foods>0;number_of_foods--){
                foodList.add(resList[random.nextInt(resList.length)]);
            }
            order=new ConfirmedOrder(neighborhoods[random.nextInt(neighborhoods.length)].getComponent().getLocation(),
                                    res,
                                    new TickInterval(lIndex,lIndex+deliveryInterval),
                                    foodList,
                                    random.nextDouble(maxWeight));
            if(!orders.containsKey(lIndex)) orders.put(lIndex,new ArrayList<>());
            orders.get(lIndex).add(order);
        }
    }

    @Override
    public List<ConfirmedOrder> generateOrders(long tick) {
        List<ConfirmedOrder> list=orders.get(tick);
        if(list==null) return new ArrayList<>();
        return list;
    }

    /**
     * A {@link OrderGenerator.Factory} for creating a new {@link FridayOrderGenerator}.
     */
    public static class Factory implements OrderGenerator.Factory {

        public final int orderCount;
        public final VehicleManager vehicleManager;
        public final int deliveryInterval;
        public final double maxWeight;
        public final double variance;
        public final long lastTick;
        public final int seed;

        private Factory(int orderCount, VehicleManager vehicleManager, int deliveryInterval, double maxWeight, double variance, long lastTick, int seed) {
            this.orderCount = orderCount;
            this.vehicleManager = vehicleManager;
            this.deliveryInterval = deliveryInterval;
            this.maxWeight = maxWeight;
            this.variance = variance;
            this.lastTick = lastTick;
            this.seed = seed;
        }

        @Override
        public OrderGenerator create() {
            return new FridayOrderGenerator(orderCount, vehicleManager, deliveryInterval, maxWeight, variance, lastTick, seed);
        }

        /**
         * Creates a new {@link FridayOrderGenerator.FactoryBuilder}.
         * @return The created {@link FridayOrderGenerator.FactoryBuilder}.
         */
        public static FridayOrderGenerator.FactoryBuilder builder() {
            return new FridayOrderGenerator.FactoryBuilder();
        }
    }


    /**
     * A {@link OrderGenerator.FactoryBuilder} form constructing a new {@link FridayOrderGenerator.Factory}.
     */
    public static class FactoryBuilder implements OrderGenerator.FactoryBuilder {

        public int orderCount = 1000;
        public VehicleManager vehicleManager = null;
        public int deliveryInterval = 15;
        public double maxWeight = 0.5;
        public double variance = 0.5;
        public long lastTick = 480;
        public int seed = -1;

        private FactoryBuilder() {}

        public FactoryBuilder setOrderCount(int orderCount) {
            this.orderCount = orderCount;
            return this;
        }

        public FactoryBuilder setVehicleManager(VehicleManager vehicleManager) {
            this.vehicleManager = vehicleManager;
            return this;
        }

        public FactoryBuilder setDeliveryInterval(int deliveryInterval) {
            this.deliveryInterval = deliveryInterval;
            return this;
        }

        public FactoryBuilder setMaxWeight(double maxWeight) {
            this.maxWeight = maxWeight;
            return this;
        }

        public FactoryBuilder setVariance(double variance) {
            this.variance = variance;
            return this;
        }

        public FactoryBuilder setLastTick(long lastTick) {
            this.lastTick = lastTick;
            return this;
        }

        public FactoryBuilder setSeed(int seed) {
            this.seed = seed;
            return this;
        }

        @Override
        public Factory build() {
            Objects.requireNonNull(vehicleManager);
            return new Factory(orderCount, vehicleManager, deliveryInterval, maxWeight, variance, lastTick, seed);
        }
    }
}
