package com.zzb.tutorial.threadlocaldemo;

public class TestThreadLocal {

    private ThreadLocal<StringBuilder> builder = ThreadLocal.withInitial(StringBuilder::new);

    public void start() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                String threadName = Thread.currentThread().getName();
                for(int j = 0; j < 3; j++) {
                    append(j);
                    System.out.printf("%s append %d, now builder value is %s, ThreadLocal instance is %d, ThreadLocal instance mapping value hashcode is %d\n",
                            threadName, j, builder.get().toString(), builder.hashCode(), builder.get().hashCode());
                }

                change();

                System.out.printf("%s set new stringbuilder, now builder value is %s, ThreadLocal instance hashcode is %d, ThreadLocal instance mapping value hashcode is %d\n",
                        threadName, builder.get().toString(), builder.hashCode(), builder.get().hashCode());
            }, "thread-"+i).start();
        }
    }

    private void append(int num) {
        builder.get().append(num);
    }

    private void change() {
        StringBuilder stringBuilder = new StringBuilder("New String");
        builder.set(stringBuilder);
    }
}
