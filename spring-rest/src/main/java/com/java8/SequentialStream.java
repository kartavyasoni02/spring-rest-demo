package com.java8;

import java.util.ArrayList;
import java.util.List;

public class SequentialStream {
	public static void main(String[] args) {

		SequentialStream manipulation = new SequentialStream();
		List<String> stringCollection = manipulation.loadList();

		// filtering

		stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

		// "aaa2", "aaa1"

		// sorting
		System.out.println("Sort the collection...");
		stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);

		// "aaa1", "aaa2"

		// mapping
		System.out.println("Mapping the collection...");
		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);

		// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

		// matching
		System.out.println("Matching the collection...");
		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

		System.out.println("Matchig in collection any string start with a " + anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

		System.out.println("Matchig in collection all string start with a " + allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

		System.out.println("Matchig in collection none string start with z " + noneStartsWithZ); // true

		// counting

		long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

		System.out.println("collection count string start with b " + startsWithB); // 3

	}

	private List<String> loadList() {

		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		return stringCollection;
	}
}
