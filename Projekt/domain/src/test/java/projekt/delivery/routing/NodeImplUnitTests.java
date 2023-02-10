package projekt.delivery.routing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import projekt.ComparableUnitTests;
import projekt.ObjectUnitTests;

import static org.tudalgo.algoutils.student.Student.crash;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import projekt.base.Location;

public class NodeImplUnitTests {

    private static ComparableUnitTests<NodeImpl> comparableUnitTests;
    private static ObjectUnitTests<NodeImpl> objectUnitTests;
    private static NodeImpl nodeA;
    private static NodeImpl nodeB;
    private static NodeImpl nodeC;
    private static NodeImpl nodeD;

    private static EdgeImpl edgeAA;
    private static EdgeImpl edgeAB;
    private static EdgeImpl edgeBC;

    public static RegionImpl reg=new RegionImpl();

    /*public NodeImplUnitTests(RegionImpl reg){
        NodeImplUnitTests.reg=reg;
    }*/

    @BeforeAll
    public static void initialize() {
        Function<Integer, NodeImpl> testObjectFactory=(i)->
            new NodeImpl(reg,"Node "+i,new Location(2*i,3*i),new HashSet<>());
        comparableUnitTests=new ComparableUnitTests<>(testObjectFactory);
        objectUnitTests=new ObjectUnitTests<>(testObjectFactory,NodeImpl::toString);
        comparableUnitTests.initialize(10);
        objectUnitTests.initialize(10);

        RegionImpl reg2=new RegionImpl();
        Location lA=new Location(1,2),lB=new Location(3,4), lC=new Location(5,6),lD=new Location(7,8);
        nodeA=new NodeImpl(reg2,"A",lA,new HashSet<>());
        nodeB=new NodeImpl(reg2,"B",lB,new HashSet<>());
        nodeC=new NodeImpl(reg2,"C",lC,new HashSet<>());
        nodeD=new NodeImpl(reg2,"D",lD,new HashSet<>());
        reg2.putNode(nodeA);
        reg2.putNode(nodeB);
        reg2.putNode(nodeC);
        reg2.putNode(nodeD);
        nodeA.connections.add(lA);
        nodeA.connections.add(lB);
        nodeB.connections.add(lC);
        nodeB.connections.add(lA);
        nodeC.connections.add(lB);
        edgeAA=new EdgeImpl(reg2,"Connection1",lA,lA,0);
        edgeAB=new EdgeImpl(reg2,"Connection2",lA,lB,10);
        edgeBC=new EdgeImpl(reg2,"Connection3",lB,lC,20);
        reg2.putEdge(edgeAA);
        reg2.putEdge(edgeAB);
        reg2.putEdge(edgeBC);
    }

    @Test
    public void testEquals() {
        objectUnitTests.testEquals();
    }

    @Test
    public void testHashCode() {
        objectUnitTests.testHashCode();
    }

    @Test
    public void testToString() {
        objectUnitTests.testToString();
    }

    @Test
    public void testBiggerThen() {
        comparableUnitTests.testBiggerThen();
    }

    @Test
    public void testAsBigAs() {
        comparableUnitTests.testAsBigAs();
    }

    @Test
    public void testLessThen() {
        comparableUnitTests.testLessThen();
    }

    @Test
    public void testGetEdge() {
        assertEquals(nodeA.getEdge(nodeA),edgeAA);
        assertEquals(nodeA.getEdge(nodeB),edgeAB);
        assertNull(nodeA.getEdge(nodeC));
        assertNull(nodeA.getEdge(nodeD));
    }

    @Test
    public void testAdjacentNodes() {
        assertEquals(nodeA.getAdjacentNodes(),Set.of(nodeA,nodeB));
        assertEquals(nodeB.getAdjacentNodes(),Set.of(nodeA,nodeC));
        assertEquals(nodeC.getAdjacentNodes(),Set.of(nodeB));
        assertEquals(nodeD.getAdjacentNodes(),Set.of());
    }

    @Test
    public void testAdjacentEdges() {
        assertEquals(nodeA.getAdjacentEdges(),Set.of(edgeAA,edgeAB));
        assertEquals(nodeB.getAdjacentEdges(),Set.of(edgeAB,edgeBC));
        assertEquals(nodeC.getAdjacentEdges(),Set.of(edgeBC));
        assertEquals(nodeD.getAdjacentEdges(),Set.of());
    }
}
