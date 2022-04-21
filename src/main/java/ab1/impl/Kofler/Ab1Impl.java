package ab1.impl.Kofler;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	// Aufgabe a)
	@Override
	public ListNode insert(LinkedList list, int value)
	{
		if (Math.abs(list.head.value - value) < Math.abs(list.tail.value - value))
			return insertFromHead(list, value);

		return insertFromTail(list, value);
	}

	private ListNode insertFromHead(LinkedList list, int value) {
		ListNode currentNode = list.head;
		while (currentNode != null) {
			if (currentNode.value <= value ) {
				currentNode = currentNode.next;
			} else {
				ListNode newElement = new ListNode();
				newElement.value = value;
				newElement.next = currentNode;
				if (currentNode.prev != null) {
					newElement.prev = currentNode.prev;
					currentNode.prev.next = newElement;
				}
				currentNode.prev = newElement;

				updateHeadOrTail(currentNode, newElement, list);
				break;
			}
		}

		return list.head;
	}

	private ListNode insertFromTail(LinkedList list, int value) {
		ListNode currentNode = list.tail;
		while (currentNode != null) {
			if (value <= currentNode.value) {
				currentNode = currentNode.prev;
			} else {
				ListNode newElement = new ListNode();
				newElement.value = value;
				newElement.prev = currentNode;
				if (currentNode.next != null) {
					newElement.next = currentNode.next;
				}
				currentNode.next = newElement;

				updateHeadOrTail(currentNode, newElement, list);
				break;
			}
		}

		return list.head;
	}

	private void updateHeadOrTail(ListNode currentNode, ListNode newElement, LinkedList list) {
		if (currentNode == list.head) {
			list.head = newElement;
		} else if (currentNode == list.tail) {
			list.tail = newElement;
		}
	}

	// Aufgabe a)
	@Override
	public LinkedList reverse(LinkedList list, ListNode tail)
	{
		ListNode currentElement = tail;
		list.head = currentElement;

		while(tail.prev != null) {
			currentElement.next = tail.prev;
			if (tail.next != null) {
				currentElement.prev = tail.next;
			}
			tail = tail.prev;
			currentElement = currentElement.next;
			if (tail.prev == null) {
				list.tail = tail;
				currentElement.next = null;
			}
		}

		return list;
	}

	// Aufgabe a)
	@Override
	public ListNode maximum(LinkedList list)
	{
		// YOUR CODE HERE
		return null;
	}

	// QUICKSORT
	@Override
	public void quicksort(int[] array)
	{
		quicksortSlice(array, 0, array.length-1);
	}

	private void quicksortSlice(int[] array, int leftBorder, int rightBorder) {
		if (leftBorder >= rightBorder) {
			return;
		}

		int pivot = medianOfThreePivot(array,leftBorder,rightBorder);
		int leftCursor = leftBorder;
		int rightCursor = rightBorder-2;

		while (leftCursor <= rightCursor) {
			if (array[leftCursor] > pivot) {
				while (leftCursor <= rightCursor) {
					if (array[rightCursor] < pivot) {
						swapElements(array, leftCursor, rightCursor);
						break;
					} else {
						rightCursor--;
					}
				}
			} else {
				leftCursor++;
			}
		}
		swapElements(array, leftCursor, rightBorder-1);

		quicksortSlice(array, 0, leftCursor-1);
		quicksortSlice(array, leftCursor+1, rightBorder);
	}

	private int medianOfThreePivot(int[] array, int leftCursor, int rightCursor) {
		int centerPositon = (leftCursor + rightCursor) / 2;

		if (array[rightCursor] < array[centerPositon]) {
			swapElements(array, centerPositon, rightCursor);
		}
		if (array[rightCursor] < array[leftCursor]) {
			swapElements(array, leftCursor, rightCursor);
		}
		if (array[centerPositon] < array[leftCursor]) {
			swapElements(array, leftCursor, centerPositon);
		}
		swapElements(array, centerPositon, rightCursor-1);
		return array[rightCursor-1];
	}

	private void swapElements(int[] array, int leftCursor, int rightCursor) {
		int tempStore = array[leftCursor];
		array[leftCursor] = array[rightCursor];
		array[rightCursor] = tempStore;
	}

	// Aufgabe c)
	@Override
	public void toMinHeap(int[] data)
	{
		// YOUR CODE HERE
	}

	// Aufgabe c)
	@Override
	public void removeHeapElement(int position, int length, int[] minHeap)
	{
		// YOUR CODE HERE
	}

	// Aufgabe c)
	@Override
	public void heapsort(int[] data)
	{
		// YOUR CODE HERE
	}
}
