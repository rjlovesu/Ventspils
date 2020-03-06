package tasks;

import java.math.BigInteger;
import java.util.Arrays;

public class Example {
	static class CountTask implements Runnable {

		private BigInteger[] array;
		private int start;
		private int end;

		public CountTask(BigInteger[] array, int start, int end) {
			this.array = array;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			int count = 0;
			for (int i = start; i < end; ++i) {
				if (array[i].isProbablePrime(20)) {
					count++;
				}
			}
			System.out.println("Count " + count + " in " + Thread.currentThread().getName());
		}

	}

	public static void main(String[] args) throws InterruptedException {

		// TODO Auto-generated method stub
		int LEN = 5_000_000;
		BigInteger[] bIntArray = new BigInteger[LEN];

		Arrays.parallelSetAll(bIntArray, i -> BigInteger.valueOf(i + 1));
		long initial_time = System.currentTimeMillis();

		CountTask task1 = new CountTask(bIntArray, 0, LEN / 2);
		CountTask task2 = new CountTask(bIntArray, LEN / 2, LEN);

		Thread thread1 = new Thread(task1, "Thread 0 to len/2");
		Thread thread2 = new Thread(task2, "Thread len/2 to len");

		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
		/*
		int count = 0;
		for (int j = 0; j < bIntArray.length; j++) {
			if (bIntArray[j].isProbablePrime(20))
				++count;
		}*/

		System.out.println(System.currentTimeMillis() - initial_time);
		// System.out.println(count);

		System.out.println("done");

	}

}
