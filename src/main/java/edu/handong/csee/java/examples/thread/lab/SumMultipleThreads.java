package edu.handong.csee.java.examples.thread.lab;

import java.util.ArrayList;

public class SumMultipleThreads {

	public static void main(String[] args) {

		long to = 10000000;
		ArrayList<SumRunner> sumRunners = new ArrayList<SumRunner>();
		ArrayList<Thread> threadsForSubSum = new ArrayList<Thread>();

		for(long i=0; i<to/1000000; i++) {
			SumRunner currentRunner = new SumRunner((i*1000000)+1, (i+1)*1000000);
			sumRunners.add(currentRunner);
			
			Thread thread = new Thread(currentRunner);
			thread.start();
			threadsForSubSum.add(thread);
			System.out.println("Thread-" + i + " started!");
		}
		for(Thread t: threadsForSubSum) {
			try {
				t.join();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}


		long grandTotal = 0;
		for(SumRunner runner:sumRunners) {
			grandTotal += runner.totalSum;
		}

		System.out.println("Grand Total = " + grandTotal);
	}

}
