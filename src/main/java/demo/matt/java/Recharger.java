package demo.matt.java;

@FunctionalInterface
public interface Recharger<T> {

    void recharge(T t);
    
}
