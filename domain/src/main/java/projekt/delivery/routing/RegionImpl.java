package projekt.delivery.routing;

import org.jetbrains.annotations.Nullable;
import projekt.base.DistanceCalculator;
import projekt.base.EuclideanDistanceCalculator;
import projekt.base.Location;

import java.util.*;

import static org.tudalgo.algoutils.student.Student.crash;

class RegionImpl implements Region {

    private final Map<Location, NodeImpl> nodes = new HashMap<>();
    private final Map<Location, Map<Location, EdgeImpl>> edges = new HashMap<>();
    private final List<EdgeImpl> allEdges = new ArrayList<>();
    private final DistanceCalculator distanceCalculator;

    /**
     * Creates a new, empty {@link RegionImpl} instance using a {@link EuclideanDistanceCalculator}.
     */
    public RegionImpl() {
        this(new EuclideanDistanceCalculator());
    }

    /**
     * Creates a new, empty {@link RegionImpl} instance using the given {@link DistanceCalculator}.
     */
    public RegionImpl(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public @Nullable Node getNode(Location location) {
        return nodes.get(location);
    }

    @Override
    public @Nullable Edge getEdge(Location locationA, Location locationB) {
        Location tmp;
        if(locationA.compareTo(locationB)>0){
            tmp=locationA;
            locationA=locationB;
            locationB=tmp;
        }
        Map<Location,EdgeImpl> edges_from_node_A=edges.get(locationA);
        if(edges_from_node_A==null) return null;
        return edges_from_node_A.get(locationB);
    }

    @Override
    public Collection<Node> getNodes() {
        return Collections.unmodifiableCollection(nodes.values());
    }

    @Override
    public Collection<Edge> getEdges() {
        return Collections.unmodifiableCollection(allEdges);
    }

    @Override
    public DistanceCalculator getDistanceCalculator() {
        return distanceCalculator;
    }

    /**
     * Adds the given {@link NodeImpl} to this {@link RegionImpl}.
     * @param node the {@link NodeImpl} to add.
     */
    void putNode(NodeImpl node){
        if(this!=node.getRegion()) throw new IllegalArgumentException("Node "+node.toString()+" has incorrect region");
        else nodes.put(node.getLocation(),node);
    }

    /**
     * Adds the given {@link EdgeImpl} to this {@link RegionImpl}.
     * @param edge the {@link EdgeImpl} to add.
     */
    void putEdge(EdgeImpl edge) {
        if(edge.getNodeA()==null) throw new IllegalArgumentException("NodeA "+edge.getLocationA()+" is not part of the region");
        if(edge.getNodeB()==null) throw new IllegalArgumentException("NodeB "+edge.getLocationB()+" is not part of the region");
        if(edge.getNodeA().getRegion()!=this||edge.getNodeB().getRegion()!=this||edge.getRegion()!=this)
            throw new IllegalArgumentException("Edge "+edge.toString()+" has incorrect region");
        allEdges.add(edge);
        if(edge.getLocationA().compareTo(edge.getLocationB())<=0){
            if(edges.get(edge.getLocationA())==null) edges.put(edge.getLocationA(),new HashMap<>());
            edges.get(edge.getLocationA()).put(edge.getLocationB(),edge);
        }
        else{ //this case should not even occur!!!
            if(edges.get(edge.getLocationB())==null) edges.put(edge.getLocationB(),new HashMap<>());
            edges.get(edge.getLocationB()).put(edge.getLocationA(),edge);
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o==this) return true;
        if(o==null) return false;
        if(o instanceof RegionImpl reg) return Objects.equals(nodes,reg.nodes)&&Objects.equals(edges,reg.edges);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes,edges);
    }
}
