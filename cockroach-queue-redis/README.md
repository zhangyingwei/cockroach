# cockroach-queue-redis

使用 redis 作为消息队列，使用方法为：

```java
@EnableAutoConfiguration
@AppName("redis")
@ThreadConfig(num = 5, sleep = 100)
@AutoClose(false)
@Store(DescribeStore.class)
public class RedisTaskQueueTest {
    private static CockroachQueue queue = RedisTaskQueue.of("172.30.154.75", 6379, "cockroach");

    public static void main(String[] args) throws Exception {
        CockroachApplication.run(RedisTaskQueueTest.class,queue);
    }

    @Test
    public void push() throws Exception {
        queue.filter(new IQueueTaskFilter() {
            @Override
            public boolean accept(Task task) {
                return task.getUrl().contains("zhangyingwei");
            }
        });
        for (int i = 0; i < 100; i++) {
            Task task = new Task("http://blog.zhangyingwei.com","zhangyingwei").retry(10).addDeep(20);
            queue.push(task);
        }
        queue.push(new Task("http://baidu.com"));
    }
}

```