package projekt.delivery.routing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import projekt.ObjectUnitTests;
import projekt.base.Location;

import static org.tudalgo.algoutils.student.Student.crash;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;


public class RegionImplUnitTests {

    private static ObjectUnitTests<RegionImpl> objectUnitTests;

    @BeforeAll
    public static void initialize() {
        Function<Integer, RegionImpl> testObjectFactory=(i)->{
            RegionImpl reg=new RegionImpl();
            Location l1=new Location(0,0), l2=new Location(i,0);
            Set<Location> set1=new HashSet<Location>(),set2=new HashSet<Location>();
            set1.add(l2);
            set2.add(l1);
            reg.putNode(new NodeImpl(reg,"Node1",l1,set1));
            reg.putNode(new NodeImpl(reg,"Node2",l2,set2));
            EdgeImpl edge=new EdgeImpl(reg,"Connection",l1,l2,i);
            reg.putEdge(edge);
            return reg;
        };
        objectUnitTests=new ObjectUnitTests<>(testObjectFactory,null);
        objectUnitTests.initialize(10);
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
    public void testNodes() {
        RegionImpl reg=new RegionImpl(),reg2=new RegionImpl();
        Location lA=new Location(1,2),lB=new Location(3,4), lC=new Location(5,6),lD=new Location(7,8);
        NodeImpl A=new NodeImpl(reg,"A",lA,new HashSet<>()),B=new NodeImpl(reg,"B",lB,new HashSet<>()),
            C=new NodeImpl(reg,"C",lC,new HashSet<>()),D=new NodeImpl(reg2,"D",lD,new HashSet<>());
        reg.putNode(A);
        reg.putNode(B);
        reg.putNode(C);
        reg2.putNode(D);
        assert(reg.getNodes().contains(A));
        assert(reg.getNodes().contains(B));
        assert(reg.getNodes().contains(C));

        assertEquals(A,reg.getNode(lA));
        assertEquals(B,reg.getNode(lB));
        assertEquals(C,reg.getNode(lC));

        assertNull(reg.getNode(new Location(11,11)));

        assertEquals(assertThrowsExactly(IllegalArgumentException.class,()->reg.putNode(D)).getMessage(),"Node "+
            D.toString()+" has incorrect region");
    }

    @Test
    public void testEdges() {
        RegionImpl reg=new RegionImpl(),reg2=new RegionImpl();
        Location lA=new Location(1,2),lB=new Location(3,4), lC=new Location(5,6);
        NodeImpl A=new NodeImpl(reg,"A",lA,(new HashSet<>())),B=new NodeImpl(reg,"B",lB,new HashSet<>()),
            C=new NodeImpl(reg,"C",lC,new HashSet<>());
        reg.putNode(A);
        reg.putNode(B);
        reg.putNode(C);
        A.connections.add(lA);
        A.connections.add(lB);
        B.connections.add(lC);
        B.connections.add(lA);
        C.connections.add(lB);
        EdgeImpl edge1=new EdgeImpl(reg,"Connection1",lA,lA,0),
                edge2=new EdgeImpl(reg,"Connection2",lA,lB,10),
                edge3=new EdgeImpl(reg,"Connection3",lB,lC,20);
        reg.putEdge(edge1);
        reg.putEdge(edge2);
        reg.putEdge(edge3);

        assertEquals(edge1,reg.getEdge(lA,lA));
        assertEquals(edge2,reg.getEdge(lA,lB));
        assertEquals(edge3,reg.getEdge(lB,lC));

        assertTrue(reg.getEdges().contains(edge1));
        assertTrue(reg.getEdges().contains(edge2));
        assertTrue(reg.getEdges().contains(edge3));

        Location lD=new Location(4,7);
        NodeImpl D=new NodeImpl(reg2,"D",lD,new HashSet<>());
        D.connections.add(C.getLocation());
        C.connections.add(lD);
        B.connections.add(D.getLocation());
        D.connections.add(lB);
        EdgeImpl edge4=new EdgeImpl(reg,"Connection4",lD,lC,1),
                edge5=new EdgeImpl(reg,"Connection5",lB,lD,1),
                edge6=new EdgeImpl(reg2,"Connection6",lA,lC,1);

        assertThrows(IllegalArgumentException.class,()->reg.putEdge(edge4));
        assertThrows(IllegalArgumentException.class,()->reg.putEdge(edge5));
        assertThrows(IllegalArgumentException.class,()->reg.putEdge(edge6));

        assertNull(reg.getEdge(lA,lC));
    }
}
