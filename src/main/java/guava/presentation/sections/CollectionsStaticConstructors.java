package guava.presentation.sections;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"UnusedDeclaration", "Convert2Diamond"})
public class CollectionsStaticConstructors {

    public void constructingCollections(){
        //Pre Java 7
        List<String> listPreJava7 = new ArrayList<String>();

        //Java 7 diamond operator
        List<String> listUsingDiamond = new ArrayList<>();

        //Guava factory methods
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");

        //Factory methods improve readability
        List<String> exactly100 = Lists.newArrayListWithCapacity(100);
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
    }
}
