# cockroach 爬虫

cockroach[小强] 当时不知道为啥选了这么个名字，又长又难记，导致编码的过程中因为单词的拼写问题耽误了好长时间。

这算是我的又一个坑吧。

一个小巧、灵活、健壮的爬虫框架，暂且叫做框架吧。

简单到什么程度呢，几句话就可以创建一个爬虫。

## 小巧

> git clone https://github.com/zhangyingwei/cockroach.git

> cd cockroach

> git install

然后新建一个 maven 项目，在 pom 文件中引入

```
<dependency>
    <groupId>com.zhangyingwei.cockroach</groupId>
    <artifactId>cockroach</artifactId>
    <version>1.0-Alpha</version>
</dependency>
```

在项目中新建一个测试类 App.java 并新建 main 方法。

### 实例

```
public static void main(String[] args){
    CockroachConfig config = new CockroachConfig()
                    .setAppName("我是一个小强")
                    .setThread(2); //爬虫线程数
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

## 灵活

那灵活又体现在什么方面呢

* 可以自定义 http 客户端（可选，默认使用 okhttp3）
* 可以自定义结果的处理 （可选，默认使用打印处理器）

### 自定义 http 客户端

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
```

### 自定义结果处理类

自定义结果处理类

```
public class SelfStore implements IStore {
    @Override
    public void store(TaskResponse response) {
        System.out.println(response.getContent());
    }
}
```

这里简单的将结果打印了出来，在实际应用中，我们可以保存到数据库或者保存到文件中等等。值得一说的是，如果结果是 html 网页文本的话，我们还提供了 select("css选择器") 来对结果文本进行处理。

应用自定义 store 客户端到爬虫

```
CockroachConfig config = new CockroachConfig()
    .setAppName("我是一个小强")
    .setThread(2) //爬虫线程数
    .setHttpClient(SelfHttpClient.class)
    .setStore(SelfStore.class);
```

## 健壮 

说到健壮，这里主要体现在应对反爬虫机制中最常见的封锁 ip 的手段。

这里我们使用动态代理来解决这个问题。

### 动态代理的使用

```
CockroachConfig config = new CockroachConfig()
    .setAppName("我是一个小强")
    .setThread(2) //爬虫线程数
    .setHttpClient(SelfHttpClient.class)
    .setProxys("100.100.100.100:8888,101.101.101.101:8888")
```

如上所示，我们可以设置若干个代理 ip，最终将所有代理 ip 生成一个代理池，在爬虫请求之前，我们会从代理池中随机抽取一个 ip 做代理。
