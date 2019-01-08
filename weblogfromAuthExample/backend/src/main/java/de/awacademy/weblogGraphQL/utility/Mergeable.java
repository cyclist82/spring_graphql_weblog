package de.awacademy.weblogGraphQL.utility;

public interface Mergeable<T, V> {

    T merge(V target);
}
