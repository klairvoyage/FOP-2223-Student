package projekt.delivery.routing;

import org.jetbrains.annotations.Nullable;
import projekt.base.Location;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.tudalgo.algoutils.student.Student.crash;

class NodeImpl implements Region.Node {

    protected final Set<Location> connections;
    protected final Region region;
    protected final String name;
    protected final Location location;

    /**
     * Creates a new {@link NodeImpl} instance.
     * @param region The {@link Region} this {@link NodeImpl} belongs to.
     * @param name The name of this {@link NodeImpl}.
     * @param location The {@link Location} of this {@link EdgeImpl}.
     * @param connections All {@link Location}s this {@link NeighborhoodImpl} has an {@link Region.Edge} to.
     */
    NodeImpl(
        Region region,
        String name,
        Location location,
        Set<Location> connections
    ) {
        this.region = region;
        this.name = name;
        this.location = location;
        this.connections = connections;
    }

    @Override
    public Region getRegion() {
        return region;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public Set<Location> getConnections() {
        return connections;
    }

    @Override
    public @Nullable Region.Edge getEdge(Region.Node other) {
        return region.getEdge(this,other);
    }

    @Override
    public Set<Region.Node> getAdjacentNodes() {
        Set<Region.Node> adjacentNodes=new HashSet<>();
        for(Location loc:connections) adjacentNodes.add(region.getNode(loc));
        return Collections.unmodifiableSet(adjacentNodes);
    }

    @Override
    public Set<Region.Edge> getAdjacentEdges() {
        Set<Region.Edge> adjacentEdges=new HashSet<>();
        for(Location loc:connections) adjacentEdges.add(getEdge(region.getNode(loc)));
        return Collections.unmodifiableSet(adjacentEdges);
    }

    @Override
    public int compareTo(Region.Node o) {
         return location.compareTo(o.getLocation());
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(o instanceof NodeImpl node) return o==this||(Objects.equals(name,node.name)&&
                                                Objects.equals(location,node.location)&&
                                                Objects.equals(connections,node.connections));
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,location,connections);
    }

    @Override
    public String toString() {
        return "NodeImpl (name='" +name+"', location='"+location.toString()+"', connections='"+connections.toString()+"')";
    }
}
