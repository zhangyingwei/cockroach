# cockroach 爬虫

cockroach[小强] 当时不知道为啥选了这么个名字，又长又难记，导致编码的过程中因为单词的拼写问题耽误了好长时间。

这算是我的又一个坑吧。

一个小巧、灵活、健壮的爬虫框架，暂且叫做框架吧。

简单到什么程度呢，几句话就可以创建一个爬虫。

## 快速开始

> git clone https://github.com/zhangyingwei/cockroach.git
cd cockroach
git install

然后新建一个 maven 项目，在 pom 文件中引入

```
<dependency>
    <groupId>com.zhangyingwei.cockroach</groupId>
    <artifactId>cockroach</artifactId>
    <version>1.0-Alpha</version>
</dependency>
```

在项目中新建一个测试类 App.java 并新建 main 方法。

## 实例

```
public static void main(String[] args){
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
}
```

那灵活又体现在什么方面呢

* 我们可以自己定义 http 客户端
* 我们可以自己定义结果的处理

首先我们尝试一下自定义客户端

```
public class SelfHttpClient implements HttpClient {
       public HttpClient setProxy(HttpProxy proxy){
            //设置代理实现方法
       }
       public TaskResponse doGet(Task task) throws Exception{
            // get 请求实现方法
       }
   
       public HttpClient proxy(){
            // 应用代理到 http 客户端 方法
       }
   
       public TaskResponse doPost(Task task) throws Exception{
            // post 请求实现方法
       }
   
       public HttpClient setCookie(String cookie){
            // 设置 cookie 实现方法
       }
   
       public HttpClient setHttpHeader(Map<String, String> httpHeader){
            // 设置 header 实现方法
       }
}
```

应用自定义 http 客户端到爬虫

```
CockroachConfig config = new CockroachConfig()
    .setAppName("我是一个小强")
    .setThread(2) //爬虫线程数
    .setHttpClient(SelfHttpClient.class)
CockroachContext context = new CockroachContext(config);
```

