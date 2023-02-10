package projekt.delivery.routing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import projekt.ComparableUnitTests;
import projekt.ObjectUnitTests;
import projekt.base.Location;

import static org.tudalgo.algoutils.student.Student.crash;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.function.Function;

public class EdgeImplUnitTests {

    private static ComparableUnitTests<Region.Edge> comparableUnitTests;
    private static ObjectUnitTests<Region.Edge> objectUnitTests;
    private static NodeImpl nodeA;
    private static NodeImpl nodeB;
    private static NodeImpl nodeC;

    private static EdgeImpl edgeAA;
    private static EdgeImpl edgeAB;
    private static EdgeImpl edgeBC;

    @BeforeAll
    public static void initialize() {
        RegionImpl reg=new RegionImpl(),reg2=new RegionImpl();
        Location lA=new Location(1,2),lB=new Location(3,4), lC=new Location(5,6);
        nodeA=new NodeImpl(reg,"A",lA,new HashSet<>());
        nodeB=new NodeImpl(reg,"B",lB,new HashSet<>());
        nodeC=new NodeImpl(reg,"C",lC,new HashSet<>());

        for(int i=0;i<10;i++) reg2.putNode(new NodeImpl(reg2,"Node"+i,new Location(i,0),new HashSet<>()));
        Function<Integer,Region.Edge> testObjectFactory=(i)->new EdgeImpl(reg2,"Edge"+i,new Location(0,0),
            new Location(i%10,0),i);
        comparableUnitTests=new ComparableUnitTests<>(testObjectFactory);
        objectUnitTests=new ObjectUnitTests<>(testObjectFactory,Region.Edge::toString);
        comparableUnitTests.initialize(10);
        objectUnitTests.initialize(10);

        reg.putNode(nodeA);
        reg.putNode(nodeB);
        reg.putNode(nodeC);
        nodeA.connections.add(lA);
        nodeA.connections.add(lB);
        nodeB.connections.add(lC);
        nodeB.connections.add(lA);
        nodeC.connections.add(lB);

        edgeAA=new EdgeImpl(reg,"Connection1",lA,lA,0);
        edgeAB=new EdgeImpl(reg,"Connection2",lA,lB,10);
        edgeBC=new EdgeImpl(reg,"Connection3",lB,lC,20);
        reg.putEdge(edgeAA);
        reg.putEdge(edgeAB);
        reg.putEdge(edgeBC);
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
    public void testGetNode() {
        assertEquals(edgeAA.getNodeA(),nodeA);
        assertEquals(edgeAA.getNodeB(),nodeA);
        assertEquals(edgeAB.getNodeA(),nodeA);
        assertEquals(edgeAB.getNodeB(),nodeB);
        assertEquals(edgeBC.getNodeA(),nodeB);
        assertEquals(edgeBC.getNodeB(),nodeC);
    }
}
