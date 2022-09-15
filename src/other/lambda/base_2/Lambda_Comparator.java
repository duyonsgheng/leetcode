package other.lambda.base_2;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName Lambda_Comparator
 * @Author Duys
 * @Description
 * @Date 2022/6/20 13:30
 **/
public class Lambda_Comparator {
    public static void main(String[] args) throws Exception {
        int[] arr = {4, 6, 3, 7, 2, 1, 8, 10, 9};
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.tryLock();
        lock.unlock();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        writeLock.lock();
        writeLock.unlock();

        readLock.lock();
        readLock.unlock();

        ThreadPoolExecutor threadPoolExecutor = null;
        threadPoolExecutor.execute(null);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        countDownLatch.countDown();
        countDownLatch.await();

        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        semaphore.tryAcquire();
        semaphore.release();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("xxxx");
        });
        cyclicBarrier.await();
        cyclicBarrier.reset();

        PriorityBlockingQueue priorityBlockingQueue = null;
        priorityBlockingQueue.poll();
        priorityBlockingQueue.take();

        DelayQueue queue = null;
        queue.put(null);
        queue.poll();

        SynchronousQueue synchronousQueue = null;
        synchronousQueue.offer(null);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.execute(null); // 调用的依然是schedule方法。只是传的延迟时间是0.
        scheduledThreadPoolExecutor.schedule(() -> {
            System.out.println("只是执行一次");
        }, 1000, TimeUnit.MILLISECONDS);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("根据执行时间和等待时间，取最大的");
        }, 1000, 2000, TimeUnit.MILLISECONDS);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("根据任务执行时间和等待时间，二者相加取得下一次任务执行时间");
        }, 1000, 2000, TimeUnit.MILLISECONDS);

        CompletableFuture completableFuture = null;
        completableFuture.thenRunAsync(null);
        FutureTask futureTask = null;
        futureTask.get();

    }

}
