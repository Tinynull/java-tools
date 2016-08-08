package com.zhaoliang.lang;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/8.
 */
public class ThreadLocalTest2 {
    private static Index num = new Index();
    //创建一个Index类型的本地变量
    private static ThreadLocal<Index> local = new ThreadLocal<Index>() {
        @Override
        protected Index initialValue() {
            return new Index();

            // 出现错误。
//             return num;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int j = 0; j < 5; j++) {
            threads[j] = new Thread(() -> {
                //取出当前线程的本地变量，并累加1000次
                Index index = local.get();
                for (int i = 0; i < 1000; i++) {
                    index.increase();
                }
                System.out.println(Thread.currentThread().getName() + " : " + index.num);

            }, "Thread-" + j);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    static class Index {
        int num;

        public void increase() {
            num++;
        }
    }
}
