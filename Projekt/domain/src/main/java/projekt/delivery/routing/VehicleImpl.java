package projekt.delivery.routing;

import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

import static org.tudalgo.algoutils.student.Student.crash;

class VehicleImpl implements Vehicle {

    private final int id;
    private final double capacity;
    private final List<ConfirmedOrder> orders = new ArrayList<>();
    private final VehicleManagerImpl vehicleManager;
    private final Deque<PathImpl> moveQueue = new LinkedList<>();
    private final VehicleManager.OccupiedRestaurant startingNode;
    private AbstractOccupied<?> occupied;

    public VehicleImpl(
        int id,
        double capacity,
        VehicleManagerImpl vehicleManager,
        VehicleManager.OccupiedRestaurant startingNode) {
        this.id = id;
        this.capacity = capacity;
        this.occupied = (AbstractOccupied<?>) startingNode;
        this.vehicleManager = vehicleManager;
        this.startingNode = startingNode;
    }

    @Override
    public VehicleManager.Occupied<?> getOccupied() {
        return occupied;
    }

    @Override
    public @Nullable VehicleManager.Occupied<?> getPreviousOccupied() {
        AbstractOccupied.VehicleStats stats = occupied.vehicles.get(this);
        return stats == null ? null : stats.previous;
    }

    @Override
    public List<? extends Path> getPaths() {
        return new LinkedList<>(moveQueue);
    }

    void setOccupied(AbstractOccupied<?> occupied) {
        this.occupied = occupied;
    }

    @Override
    public void moveDirect(Region.Node node, Consumer<? super Vehicle> arrivalAction) {
        if(!moveQueue.isEmpty()) {
            Region.Node start = moveQueue.getFirst().nodes().getFirst();
            moveQueue.clear();
            checkMoveToNode(node);
            if (!(occupied.component instanceof Region.Node)) moveQueued(start, arrivalAction);
            moveQueued(node, arrivalAction);
        } else{
            checkMoveToNode(node);
            // if the queue is empty, we must be at the original restaurant
            moveQueued(node,arrivalAction);
        }
    }

    @Override
    public void moveQueued(Region.Node node, Consumer<? super Vehicle> arrivalAction) {
        checkMoveToNode(node);
        if(!moveQueue.isEmpty())
            moveQueue.add(new PathImpl(vehicleManager.getPathCalculator().getPath(moveQueue.getLast().nodes().getLast(),node),
                                arrivalAction));
        else
            moveQueue.add(new PathImpl(vehicleManager.getPathCalculator().getPath(startingNode.getComponent(),node),
                arrivalAction));

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getCapacity() {
        return capacity;
    }

    @Override
    public VehicleManager getVehicleManager() {
        return vehicleManager;
    }

    @Override
    public VehicleManager.Occupied<? extends Region.Node> getStartingNode() {
        return startingNode;
    }

    @Override
    public Collection<ConfirmedOrder> getOrders() {
        return orders;
    }

    @Override
    public void reset() {
        occupied = (AbstractOccupied<?>) startingNode;
        moveQueue.clear();
        orders.clear();
    }

    private void checkMoveToNode(Region.Node node) {
        if (occupied.component.equals(node) && moveQueue.isEmpty()) {
            throw new IllegalArgumentException("Vehicle " + getId() + " cannot move to own node " + node);
        }
    }

    void move(long currentTick) {
        final Region region = vehicleManager.getRegion();
        if (moveQueue.isEmpty()) {
            return;
        }
        final PathImpl path = moveQueue.peek();
        if (path.nodes().isEmpty()) {
            moveQueue.pop();
            final @Nullable Consumer<? super Vehicle> action = path.arrivalAction();
            if (action == null) {
                move(currentTick);
            } else {
                action.accept(this);
            }
        } else {
            Region.Node next = path.nodes().peek();
            if (occupied instanceof OccupiedNodeImpl) {
                vehicleManager.getOccupied(region.getEdge(((OccupiedNodeImpl<?>) occupied).getComponent(), next)).addVehicle(this, currentTick);
            } else if (occupied instanceof OccupiedEdgeImpl) {
                vehicleManager.getOccupied(next).addVehicle(this, currentTick);
                path.nodes().pop();
            } else {
                throw new AssertionError("Component must be either node or component");
            }
        }
    }

    void loadOrder(ConfirmedOrder order) {
        orders.add(order);
        if(getCurrentWeight()>capacity) throw new VehicleOverloadedException(this,getCurrentWeight());
    }

    void unloadOrder(ConfirmedOrder order) {
        orders.remove(order);
    }

    @Override
    public int compareTo(Vehicle o) {
        return Integer.compare(getId(), o.getId());
    }

    @Override
    public String toString() {
        return "VehicleImpl("
            + "id=" + id
            + ", capacity=" + capacity
            + ", orders=" + orders
            + ", component=" + occupied.component
            + ')';
    }

    private record PathImpl(Deque<Region.Node> nodes, Consumer<? super Vehicle> arrivalAction) implements Path {

    }
}
