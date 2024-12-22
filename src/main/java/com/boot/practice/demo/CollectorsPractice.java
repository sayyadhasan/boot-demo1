package com.boot.practice.demo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CollectorsPractice {

    public static void main(String[] args) throws URISyntaxException, IOException {
        System.out.println("getValue() = " + getValue(() -> new Integer(1)));

        consumerMethod((t, i) -> System.out.println("t = " + t + " i = " + i));
        consumerMethod(new ConsumerImpl());


        Path path = Paths.get(CollectorsPractice.class.getClassLoader().getResource("convertcsv.csv").toURI());

        List<Person> persons = Files.lines(path)
                .skip(1)
                .map(e -> mapLinetoPerson(e))
                .collect(Collectors.toList());

        persons.stream().filter(p -> p.getAge() > 64).forEach(System.out::println);

        // parallel stream helps reduce time by 6 just to print 100k. Linear stream took 126 seconds
        // while parallel stream took 20 seconds
        long l = System.currentTimeMillis();
        persons.stream()
                .parallel()
                .forEach(p -> {
                    System.out.println(p);
                    try {
                        Thread.sleep(1 );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        long l1 = System.currentTimeMillis();
        System.out.println("time taken = " + (l1-l));


    }

    private static Person mapLinetoPerson(String line) {
        Person person = new Person();

        String[] split = line.split(",");

        person.setGuid(split[0]);
        person.setFirstName(split[1]);
        person.setLastName(split[2]);
        person.setAge(Integer.valueOf(split[3]));
        person.setBirthday(split[4], "M/d/uuuu");
        person.setCity(split[5]);
        person.setPostal(split[6]);
        person.setState(split[7]);
        person.setStreet(split[8]);
        person.setGender(split[9]);

        return person;

    }



    public static <T extends Number> T getValue (Supplier<T> t) {

        return t.get();
    }

    public static void consumerMethod (BiConsumer<String, Integer> consumer) {
        consumer.accept("", 1234);
    }



    public static class ConsumerImpl implements BiConsumer<String, Integer> {

        @Override
        public void accept(String s, Integer integer) {
            System.out.println("s = " + s + " integer= " + integer);
        }
    }

}
