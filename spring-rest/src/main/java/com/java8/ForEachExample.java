package com.java8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ForEachExample {

	public static void main(String[] args) {

		// creating sample Collection
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			list.add(i);

		// traversing using Iterator
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			Integer i = it.next();
			System.out.println("Iterator Value::" + i);
		}

		// traversing through forEach method of Iterable with anonymous class
		list.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.println("forEach anonymous class Value::" + t);
			}

		});

		// traversing through forEach method of Iterable with lambda expression
		list.forEach(i -> System.out.println("forEach lambda expression::" + i));

		// traversing with Consumer interface implementation
		MyConsumer action = new MyConsumer();
		list.forEach(action);

	}

}

// Consumer implementation that can be reused
class MyConsumer implements Consumer<Integer> {

	@Override
	public void accept(Integer t) {
		System.out.println("Consumer impl Value::" + t);
	}

}
