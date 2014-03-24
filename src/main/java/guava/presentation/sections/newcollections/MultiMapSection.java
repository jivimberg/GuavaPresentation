package guava.presentation.sections.newcollections;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import java.util.Set;

/**
 * Every experienced Java programmer has, at one point or another, implemented a Map<K, List<V>> or Map<K, Set<V>>,
 * and dealt with the awkwardness of that structure.
 */
public class MultiMapSection {

    private static Multimap<String, Role> modifiyingMultiMaps() {
        Multimap<String, Role> rolesForAsset = HashMultimap.create();

        rolesForAsset.put("User1", Role.OWNER);
        System.out.println(rolesForAsset);

        final Set<Role> rolesForUser2 = ImmutableSet.of(Role.VIEWER, Role.EDITOR);
        rolesForAsset.putAll("User2", rolesForUser2);
        System.out.println(rolesForAsset);

        final Set<Role> newRolesForUser2 = ImmutableSet.of(Role.OWNER, Role.EDITOR);
        rolesForAsset.replaceValues("User2", newRolesForUser2);
        System.out.println(rolesForAsset);

        return rolesForAsset;
    }

    private static void viewsOnMultiMaps(Multimap<String, Role> multiMap){
        System.out.println("multiMap.asMap() = " + multiMap.asMap());
        System.out.println("multiMap.entries() = " + multiMap.entries());
        System.out.println("multiMap.keySet() = " + multiMap.keySet());
        System.out.println("multiMap.values() = " + multiMap.values());
    }

    public static void main(String[] args) {
        Multimap<String, Role> multiMap = modifiyingMultiMaps();
        viewsOnMultiMaps(multiMap);
    }

    private enum Role { VIEWER, EDITOR, OWNER }
}
