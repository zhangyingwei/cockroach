# cockroach 爬虫

cockroach[小强] 当时不知道为啥选了这么个名字，又长又难记，导致编码的过程中因为单词的拼写问题耽误了好长时间。

这算是我的又一个坑吧。

一个简单的爬虫框架，暂且叫做框架吧。

简单到什么程度呢，几句话就可以创建一个爬虫。

## 实例

```
CockroachConfig config = new CockroachConfig()
                .setAppName("我是一个小强")
                .setThread(2) //爬虫线程数
CockroachContext context = new CockroachContext(config);
TaskQueue queue = new TaskQueue();
context.start(queue);

// 以上就是一个完整的爬虫，下边的代码相当于一个生产者，往队列里边写任务，一旦写入任务，爬虫就会对任务进行爬取
new Thread(() -> {
    int i = 0;
    while(true){
        i++;
        try {
            Thread.sleep(1000);
            String url = "http://www.xicidaili.com/wt/"+i;
            System.out.println(url);
            queue.push(new Task(url));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i > 1000) {
            break;
        }
    }
}).start();
```

