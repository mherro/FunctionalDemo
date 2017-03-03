package demo.matt.java;

import java.util.function.Consumer;

@FunctionalInterface
public interface Recharger<T> extends Consumer<T> {

}
