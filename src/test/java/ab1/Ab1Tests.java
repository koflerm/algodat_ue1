package ab1;

import ab1.impl.Kofler.Ab1Impl;
import ab1.Ab1.ListNode;
import ab1.Ab1.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class Ab1Tests {

	private Random rand = new Random(System.currentTimeMillis());
	private static Ab1 ab1Impl = new Ab1Impl();
	private LinkedList ll;
	private int[] array;

	//Testfälle
	@BeforeEach
	public void initSortedLinkedList() {
		ll = new LinkedList();
		ListNode e1 = new ListNode();
		ListNode e2 = new ListNode();
		ListNode e3 = new ListNode();
		ListNode e4 = new ListNode();
		ListNode e5 = new ListNode();

		e1.value = 2;
		e2.value = 3;
		e3.value = 5;
		e4.value = 7;
		e5.value = 9;

		e1.next = e2;
		e2.prev = e1;
		e2.next = e3;
		e3.prev = e2;
		e3.next = e4;
		e4.prev = e3;
		e4.next = e5;
		e5.prev = e4;

		ll.head = e1;
		ll.tail = e5;

		//array = new int[]{7,8,10,2,26,4,19,5,3,16,11,6,13,12};
		array = new int[]{34,7000,12,3213,5,321,5432,12,1,32,5,23,1,2,3,4};
	}

	@Test
	public void testInsertOfHeadElement() {
		ListNode firstElement = ab1Impl.insert(ll, 1);

		assertEquals(firstElement.value, 1);
	}

	@Test
	public void testInsertOfTailElement() {
		ListNode firstElement = ab1Impl.insert(ll, 12);

		assertEquals(ll.tail.value, 12);
	}

	@Test
	public void testReverseHead() {
		LinkedList list = ab1Impl.reverse(ll, ll.tail);

		assertEquals(list.head.value, 9);
	}

	@Test
	public void testReverseTail() {
		LinkedList list = ab1Impl.reverse(ll, ll.tail);

		assertEquals(list.tail.value, 2);
	}

	@Test
	public void testQuickSort() {
		ab1Impl.quicksort(array);

		String output = "";
		for (int i = 0; i < array.length; i++) {
			output += " " + array[i];
		}
		System.out.println(output);
	}
}
