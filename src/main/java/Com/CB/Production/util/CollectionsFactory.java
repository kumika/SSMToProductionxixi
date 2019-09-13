package Com.CB.Production.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionsFactory {
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K,V>();
    }


    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<K,V>();
    }

    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap<K,V>();
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> LinkedList<T> newLinkList() {
        return new LinkedList<>();
    }

    public static <T> HashSet<T> newHashSet() {
        return new HashSet<>();
    }

    public static <T> LinkedHashSet<T> newLinkedHasSet() {
        return new LinkedHashSet<>();
    }


}
