package ab1.impl.Kofler;

import ab1.Ab1;

public class Ab1Impl implements Ab1 {

	// Aufgabe a)
	@Override
	public LinkedList insert(LinkedList list, int value)
	{
		if (list.head == null && list.tail == null) {
			ListNode newElement = new ListNode();
			newElement.value = value;
			list.head = newElement;
			list.tail = newElement;

			return list;
		}

		if (Math.abs(list.head.value - value) < Math.abs(list.tail.value - value))
			return insertFromHead(list, value);

		return insertFromTail(list, value);
	}

	private LinkedList insertFromHead(LinkedList list, int value) {
		ListNode currentNode = list.head;
		while (currentNode != null) {
			if (value > currentNode.value && currentNode.next != null) {
				currentNode = currentNode.next;
			} else if (value > currentNode.value) {
				ListNode newElement = new ListNode();
				newElement.value = value;
				newElement.prev = currentNode;
				currentNode.next = newElement;

				updateHeadOrTail(currentNode, newElement, list);
				break;
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

		return list;
	}

	private LinkedList insertFromTail(LinkedList list, int value) {
		ListNode currentNode = list.tail;
		while (currentNode != null) {
			if (value < currentNode.value && currentNode.prev != null) {
				currentNode = currentNode.prev;
			} else if (value < currentNode.value) {
				ListNode newElement = new ListNode();
				newElement.value = value;
				newElement.next = currentNode;
				currentNode.prev = newElement;

				updateHeadOrTail(currentNode, newElement, list);
				break;
			} else {
				ListNode newElement = new ListNode();
				newElement.value = value;
				newElement.prev = currentNode;
				if (currentNode.next != null) {
					newElement.next = currentNode.next;
					currentNode.next.prev = newElement;
				}
				currentNode.next = newElement;

				updateHeadOrTail(currentNode, newElement, list);
				break;
			}
		}

		return list;
	}

	private void updateHeadOrTail(ListNode currentNode, ListNode newElement, LinkedList list) {
		if (currentNode == list.head && currentNode == list.tail) {
			if (currentNode.value > newElement.value) {
				list.head = newElement;
			} else {
				list.tail = newElement;
			}
		} else if (currentNode == list.head && currentNode.value >= newElement.value) {
			list.head = newElement;
		} else if (currentNode == list.tail && currentNode.value <= newElement.value) {
			list.tail = newElement;
		}
	}

	// Aufgabe a)
	@Override
	public LinkedList reverse(LinkedList list)
	{
		ListNode cursor = list.tail;

		LinkedList reversed = new LinkedList();
		reversed.head = new ListNode();
		ListNode current = reversed.head;
		ListNode previous = null;

		while (cursor.prev != null) {
			current.value = cursor.value;
			current.next = new ListNode();
			current.prev = previous;
			previous = current;
			current = current.next;
			cursor = cursor.prev;
		}

		current.value = cursor.value;
		current.prev = previous;
		reversed.tail = current;

		return reversed;
	}

	// Aufgabe a)
	@Override
	public ListNode maximum(LinkedList list)
	{
		return list.tail;
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
		int centerPosition = (leftCursor + rightCursor) / 2;

		if (array[rightCursor] < array[centerPosition]) {
			swapElements(array, centerPosition, rightCursor);
		}
		if (array[rightCursor] < array[leftCursor]) {
			swapElements(array, leftCursor, rightCursor);
		}
		if (array[centerPosition] < array[leftCursor]) {
			swapElements(array, leftCursor, centerPosition);
		}
		swapElements(array, centerPosition, rightCursor-1);
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
		if (data.length > 1) {
			toHeap(data, data.length - 1, true);
		}
	}

	// Aufgabe c)
	public void toMaxHeap(int[] data)
	{
		if (data.length > 1) {
			toHeap(data, data.length-1, false);
		}
	}

	// Aufgabe c)
	@Override
	public void removeHeapElement(int position, int length, int[] minHeap)
	{
		if (position < length) {
			swapElements(minHeap, position, length-1);
			toHeap(minHeap, length-2, true);
		}
	}

	// Aufgabe c)
	@Override
	public void heapsort(int[] data)
	{
		int lastIndex = data.length-1;
		while(lastIndex > 0) {
			toHeap(data, lastIndex, true);
			swapElements(data, 0, lastIndex);
			lastIndex--;
		}
	}

	private void toHeap(int[] data, int rightCursor, boolean toMinHeap)
	{
		boolean swapHappened = true;
		while(swapHappened) {
			for (int i = 0; i < (rightCursor+1); i++) {
				int twoKIndex = 2*(i+1)-1;
				int twoKPlusOneIndex = 2*(i+1);

				if (checkHeapConditions(data, i, twoKIndex, twoKPlusOneIndex, rightCursor, toMinHeap)) {
					swapHappened = true;
					break;
				}
				swapHappened = false;
			}
		}
	}

	private boolean checkHeapConditions(int[] data, int elementIndex, int twoKIndex, int twoKPlusOneIndex, int rightCursor, boolean checkForMinHeap) {
		if (checkHeapCondition(data, elementIndex, twoKIndex, rightCursor, checkForMinHeap)) {
			return true;
		}
		if (checkHeapCondition(data, elementIndex, twoKPlusOneIndex, rightCursor, checkForMinHeap)) {
			return true;
		}
		return false;
	}

	private boolean checkHeapCondition(int[] data, int elementIndex, int checkedIndex, int rightCursor, boolean checkForMinHeap) {
		if (checkedIndex <= rightCursor) {
			if (!checkForMinHeap) {
				if (data[elementIndex] < data[checkedIndex]) {
					swapElements(data, elementIndex, checkedIndex);
					return true;
				}
			} else {
				if (data[elementIndex] > data[checkedIndex]) {
					swapElements(data, elementIndex, checkedIndex);
					return true;
				}
			}
		}
		return false;
	}
}
