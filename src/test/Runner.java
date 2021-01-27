package test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
       var numbers = List.of(34,32,6,7,9);

       var total = numbers.stream().reduce((acum,actually)->{
           return acum + actually;
       });

       System.out.println( total );
    }
}
