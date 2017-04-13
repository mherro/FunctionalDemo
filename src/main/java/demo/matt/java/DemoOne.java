package demo.matt.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLEditorKit.Parser;

public class DemoOne {

    
    
    public static void main(String[] args) {
        
        System.out.println("Starting Demo...");

        streamDemo();
        
        lamdaDemo();
        
        sortingDemo();
        
        functionalInterfaceDemo();
        
        parallelDemo();
        
        parallelLimitThreadDemo();
        
        customFunctionalInterfaceDemo();
        
        mapReduceExample();

        System.out.println();
        System.out.println("DEMO DONE");

    }
    
    private static List<Robot> createRobots() {

        List<Robot> robots = new ArrayList<Robot>();

        Robot aRobot = new Robot();
        aRobot.setName("Alvin");
        aRobot.setSize(10);
        aRobot.setBatteryLevel(56);
        robots.add(aRobot);

        Robot tRobot = new Robot();
        tRobot.setName("Tad");
        tRobot.setSize(5);
        tRobot.setBatteryLevel(23);
        robots.add(tRobot);


        Robot bRobot = new Robot();
        bRobot.setName("Bart");
        bRobot.setSize(2);
        bRobot.setBatteryLevel(80);
        robots.add(bRobot);

        // Can override function implementation in constructor
        Robot customAlarmerRobot = new Robot() {
            @Override
            public String raiseAlarm() {
                String customAlarmSound = "CLANG! CLANG!";
                return customAlarmSound;
            }
        };

        customAlarmerRobot.setName("Clanger");
        customAlarmerRobot.setSize(19);
        customAlarmerRobot.setBatteryLevel(39);
        robots.add(customAlarmerRobot);
        
        return robots;
    }
    
    private static void streamDemo() {
        // Iterate through a list
        
        System.out.println();
        System.out.println("Running streamDemo");
        
        List<Robot> robots = createRobots();
        
        // Print each robot
        robots.stream().forEach(System.out::println);
        
    }
    
    
    private static void lamdaDemo() {
        // Iterate through a list with lambda

        System.out.println();
        System.out.println("Running lamdaDemo");
        
        List<Robot> robots = createRobots();

        System.out.println();
        System.out.println("Map to raiseAlarm string, print each using method reference");
        // "Robot::raiseAlarm" is a method reference, target is derived stream
        // "System.out::println" is also a method reference, parameter to "println" is derived from stream
        robots.stream().map(Robot::raiseAlarm).forEach(System.out::println);

        System.out.println();
        System.out.println("Same result use a lambda");
        robots.stream().forEach((Robot r) -> System.out.println(r.raiseAlarm()));

        System.out.println();
        System.out.println("Inferring the parameter type");
        robots.stream().forEach(r -> System.out.println(r.raiseAlarm()));
        
        /*
        // Synthesized method would look like this
        robots.stream().forEach(
            function(Robot r) {
                System.out.println(r.raiseAlarm());
            }
        );

         */
        
        
    }

    
    private static void sortingDemo() {
        // Sorting
        
        System.out.println();
        System.out.println("Running sortingDemo");
        
        List<Robot> robots = createRobots();

        System.out.println();
        System.out.println("Sorted by Name");
        robots.stream().sorted((r1, r2) -> r1.getName().compareTo(r2.getName())).forEach(System.out::println);

        System.out.println();
        System.out.println("Sorted by Size");
        robots.stream().sorted((r1, r2) -> r1.getSize().compareTo(r2.getSize())).forEach(System.out::println);
     
        
    }

    private static void functionalInterfaceDemo() {

        System.out.println();
        System.out.println("Running functionalInterfaceDemo");

        List<Robot> robots = createRobots();
        
        Function<Robot, String> upperRobotName = r -> r.getName().toUpperCase();
        
        robots.stream().map(upperRobotName).forEach(System.out::println);
        

        Robot maxRobot = new Robot();
        maxRobot.setName("Max");
        String upperTest = upperRobotName.apply(maxRobot);
         
        // prints out "upperTest: MAX"
        System.out.println("upperTest: " + upperTest);
            
    }
    
    
    private static void parallelDemo() {

        System.out.println();
        System.out.println("Running parallelDemo");
        System.out.println("This will be faster, trust me");

        List<Robot> robots = createRobots();

        Function<Robot, String> upperRobotName = r -> r.getName().toUpperCase();
        
        robots.parallelStream().map(upperRobotName).forEach(System.out::println);
        
    
    }
    
    private static void parallelLimitThreadDemo() {

        int threadCount = 3;
        
        System.out.println();
        System.out.println("Running parallelLimitThreadDemo");
        System.out.println("Limit number of threads to " + threadCount);

        List<Robot> robots = createRobots();

        robots.addAll(createRobots());
        robots.addAll(createRobots());
        robots.addAll(createRobots());
        
        Function<Robot, String> upperRobotName = r -> r.getName().toUpperCase();
        
        // Limit amount of threads used by parallelStream
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        try {
            forkJoinPool.submit(() ->
                robots.parallelStream().map(upperRobotName).forEach(x -> System.out.println(Thread.currentThread().getId() + " : " + x))
            ).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    
    }
    
    
    private static void customFunctionalInterfaceDemo() {
        System.out.println();
        System.out.println("Running customFunctionalInterfaceDemo");
        
        List<Robot> robots = createRobots();
        
        Recharger<Robot> robotRecharger = r -> r.setBatteryLevel(100);
        
        robots.forEach(robotRecharger);
        
        robots.stream().forEach(System.out::println);
    }
    
    private static void mapReduceExample() {
        System.out.println();
        System.out.println("Running mapReduceExample");

        List<Robot> robots = createRobots();
        
        robots.stream().map(r -> r.getBatteryLevel()).forEach(System.out::println);
        
        Integer totalBatteryLevel = robots.stream().map(r -> r.getBatteryLevel()).reduce(0, (bl1, bl2) -> bl1 + bl2);
    
        /* // Reduce function
         
             T result = identity;
             
             for (T element : this stream)
                 result = accumulator.apply(result, element)
          
             return result;
         
         */
        
        
        System.out.println("totalBatteryLevel: " + totalBatteryLevel);
        
    }
    
    
    private static void completableFutureDemo() {
        
        
        
    
    }
    
}
