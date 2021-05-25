package cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CircularQueue {

	private int currentSize;
	private Box[] circularQueueElements;

	private List<Box> replacedBoxes = new ArrayList<>();
	private int maxSize;

	private int rear; // new item will be enqueued at rear
	private int front; // item will be dequeued from front

	public CircularQueue(int maxSize) {
		this.maxSize = maxSize;
		// circularQueueElements = (Box[]) new Object[this.maxSize];
		circularQueueElements = new Box[this.maxSize];
		currentSize = 0;
		front = -1;
		rear = -1;
	}

	public List<Box> getReplacedBoxes() {
		return replacedBoxes;
	}

	public synchronized void enqueue(Box item) {
		if (isFull()) {
			throw new RuntimeException(
					"Circular Queue is full. Element cannot be added");
		}
		// else {
		// rear = (rear + 1) % circularQueueElements.length;
		// circularQueueElements[rear] = item;
		// currentSize++;
		// if (front == -1)
		// front = rear;
		// }
		else if (front == -1 && rear == -1) { // queue empty
			rear = front = 0;
			circularQueueElements[rear] = item;
			currentSize++;
		} else if (rear == circularQueueElements.length - 1 && front != 0) {
			rear = 0;
			circularQueueElements[rear] = item;
		} else {
			rear = rear + 1;

			circularQueueElements[rear] = item;

		}
		System.out.println("Enqueued : " + item.getContent());
	}

	public synchronized Box dequeue() {
		Box dequeuedItem;
		if (isEmpty()) {
			throw new RuntimeException(
					"Circular Queue is empty. Element cannot be retrieved");
		} else {
			dequeuedItem = circularQueueElements[front];
			circularQueueElements[front] = null;
			if (front == rear) {
				front = -1;
				rear = -1;
			} else if (front == circularQueueElements.length - 1)
				front = 0;
			else
				front++;

			// return data;

			// front = (front + 1) % circularQueueElements.length;
			// currentSize--;
		}
		System.out.println("Dequeued : " + dequeuedItem.getContent());
		replacedBoxes.add(dequeuedItem);
		return dequeuedItem;
	}

	public boolean isFull() {
		if (((rear + 1) % circularQueueElements.length == front) // for 1st and
																	// last
																	// position
				|| (rear == ((front - 1) % (circularQueueElements.length - 1)))) // for
																					// any
		// position
		{

			return true;
		}
		return false;
	}

	// return (currentSize == circularQueueElements.length);

	public boolean isEmpty() {
		if ((front == -1) && (rear == -1)) {

			return true;

		} else
			return false;
	}

	// return (currentSize == 0);
	// }

	@Override
	public String toString() {
		return Arrays.toString(circularQueueElements);
	}

	public boolean isPresent(int item) {
		// boolean ret = Arrays.asList(circularQueueElements).contains(item);
		for (Box box : circularQueueElements) {
			if (box != null && box.getContent() == item) {
				box.setRefBit(true); // update refBit and timestamp
				return true;
			}
		}
		return false;
	}

	// convenience method
	public void dequeueAndEnqueue(Box item) {
		if (isFull()) {
			Arrays.sort(circularQueueElements);
			dequeue();
		}
		enqueue(item);
	}
}