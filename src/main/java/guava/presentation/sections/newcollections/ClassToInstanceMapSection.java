package guava.presentation.sections.newcollections;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

@SuppressWarnings("UnusedDeclaration")
public class ClassToInstanceMapSection {

    private interface ServiceFactory {}

    private class SoaServiceFactory implements ServiceFactory {}

    private class CloudServiceFactory implements ServiceFactory {}

    public void classToInstanceExample() {
        ClassToInstanceMap<ServiceFactory> factories = MutableClassToInstanceMap.create();
        factories.putInstance(SoaServiceFactory.class, new SoaServiceFactory());
        factories.putInstance(CloudServiceFactory.class, new CloudServiceFactory());

        ServiceFactory serviceFactory = factories.get(CloudServiceFactory.class);
        CloudServiceFactory serviceFactoryInstance = factories.getInstance(CloudServiceFactory.class);
        //No cast needed!
    }
}
