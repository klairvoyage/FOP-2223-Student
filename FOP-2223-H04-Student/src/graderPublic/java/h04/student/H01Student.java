package h04.student;


import org.tudalgo.algoutils.tutor.general.reflections.BasicPackageLink;
import org.tudalgo.algoutils.tutor.general.reflections.PackageLink;

public class H01Student {

    private static final PackageLink linkToPackage;

    static {
        linkToPackage = BasicPackageLink.of("h04");
    }

    public static PackageLink linkToH01() {
        return linkToPackage;
    }
}
