package Concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz_Semaphore {
    private final int n;
	private final Semaphore fizz = new Semaphore(0);
	private final Semaphore buzz = new Semaphore(0);
	private final Semaphore fizzbuzz = new Semaphore(0);
	private final Semaphore num = new Semaphore(1);

	public FizzBuzz_Semaphore(int n) {
        this.n = n;
    }

	// printFizz.run() outputs "fizz".
	public void fizz(Runnable printFizz) throws InterruptedException {
		for (int k = 1; k <= n; k++) {
			if (k % 3 == 0 && k % 5 != 0) {
				fizz.acquire();
				printFizz.run();
				releaseLock(k + 1);
			}
		}
	}

	// printBuzz.run() outputs "buzz".
	public void buzz(Runnable printBuzz) throws InterruptedException {
		for (int k = 1; k <= n; k++) {
			if (k % 5 == 0 && k % 3 != 0) {
				buzz.acquire();
				printBuzz.run();
				releaseLock(k + 1);
			}
		}
	}

	// printFizzBuzz.run() outputs "fizzbuzz".
	public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
		for (int k = 1; k <= n; k++) {
			if (k % 15 == 0) {
				fizzbuzz.acquire();
				printFizzBuzz.run();
				releaseLock(k + 1);
			}
		}
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void number(IntConsumer printNumber) throws InterruptedException {
		for (int k = 1; k <= n; k++) {
			if (k % 3 != 0 && k % 5 != 0) {
				num.acquire();
				printNumber.accept(k);
				releaseLock(k + 1);
			}
		}
	}

	public void releaseLock(int n) {
		if (n % 3 == 0 && n % 5 != 0) {
			fizz.release();
		} else if (n % 5 == 0 && n % 3 != 0) {
			buzz.release();
		} else if (n % 15 == 0) {
			fizzbuzz.release();
		} else {
			num.release();
		}
	}
}