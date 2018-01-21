# cockroach 爬虫：又一个 java 爬虫实现

[![](https://travis-ci.org/zhangyingwei/cockroach.svg?branch=master)]()
[![](https://img.shields.io/badge/language-java-orange.svg)]()
[![](https://img.shields.io/badge/jdk-1.8-green.svg)]()
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


## 简介

cockroach[小强] 当时不知道为啥选了这么个名字，又长又难记，导致编码的过程中因为单词的拼写问题耽误了好长时间。

这个项目算是我的又一个坑吧，算起来挖的坑多了去了，多一个不多少一个不少。

一个小巧、灵活、健壮的爬虫框架，暂且叫做框架吧。

简单到什么程度呢，几句话就可以创建一个爬虫。

## 环境

* java8 程序中用到了一些 java8 的新特性
* maven

```xml
<dependency>
  <groupId>com.github.zhangyingwei</groupId>
  <artifactId>cockroach</artifactId>
  <version>LATEST</version>
</dependency>
```
如果哪天我忘了更新文档了，一定要记住使用最新的版本，最新的版本，新的版本，版本，本。

---

下面就逐点介绍一下：

## 小巧

小巧主要体现在两个方面

* 体积小 （打包之后整个 jar 包只有 70k 多一点，所以完全担得起小巧两个字）
* 开发代码量小（基于本框架开发一个爬虫需要的工作量可以说是非常小。这个在下边的文档中会有体现）

### 实例

在项目中新建一个测试类 App.java 并新建 main 方法。

```java
public static void main(String[] args){
    CockroachConfig config = new CockroachConfig()
                    .setAppName("我是一个小强")
                    .setThread(2); //爬虫线程数
    CockroachContext context = new CockroachContext(config);
    TaskQueue queue = TaskQueue.of();
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

* 可以自定义 http 客户端（<del>可选<del>，默认使用 okhttp3）
* 可以自定义结果的处理 （可选，默认使用打印处理器）

---

### 自定义 http 客户端

首先我们尝试一下自定义客户端

```java
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

```java
CockroachConfig config = new CockroachConfig()
    .setAppName("我是一个小强")
    .setThread(2) //爬虫线程数
    .setHttpClient(SelfHttpClient.class)
```

*ps:上边这一块目前处于待定状态*

---

### 自定义结果处理类

自定义结果处理类

```java
public class SelfStore implements IStore {
    @Override
    public void store(TaskResponse response) {
        System.out.println(response.getContent());
    }
}
```

这里简单的将结果打印了出来，在实际应用中，我们可以保存到数据库或者保存到文件中等等。值得一说的是，如果结果是 html 网页文本的话，我们还提供了 select("css选择器") 来对结果文本进行处理。

应用自定义 store 客户端到爬虫

```java
CockroachConfig config = new CockroachConfig()
    .setAppName("我是一个小强")
    .setThread(2) //爬虫线程数
    .setHttpClient(SelfHttpClient.class)
    .setStore(SelfStore.class);
```

### 自定义错误处理类

当 http 请求网页出现错误的时候会统一定位到错误处理类，如果没有自定义错误处理类，系统会默认使用 DefaultTaskErrorHandler ，此处理类会吧错误信息打印出来。具体实现代码如下。

```java
public class DefaultTaskErrorHandler implements ITaskErrorHandler {
    private Logger logger = Logger.getLogger(DefaultTaskErrorHandler.class);
    @Override
    public void error(Task task,String message) {
        logger.info("task error: "+message);
    }
}
```

如果需要自定义错误处理类，可以仿照以上代码，实现 ITaskErrorHandler 接口，在 error 方法中实现自己的处理逻辑。

在自定义错误处理类之后，我们需要把自定义类应用到爬虫。

```java
CockroachConfig config = new CockroachConfig()
    .setAppName("我是一个小强")
    .setThread(2) //爬虫线程数
    .setHttpClient(SelfHttpClient.class)
    .setStore(SelfStore.class)
    .setTaskErrorHandler(SelfTaskErrorHandler.class);
```

## 健壮 

说到健壮，这里主要体现在以下几个方面：

> 应对IP封锁

这里我们使用动态代理来解决这个问题。

### 动态代理的使用

```java
CockroachConfig config = new CockroachConfig()
    .setAppName("我是一个小强")
    .setThread(2) //爬虫线程数
    .setHttpClient(SelfHttpClient.class)
    .setProxys("100.100.100.100:8888,101.101.101.101:8888")
```

如上所示，我们可以设置若干个代理 ip，最终将所有代理 ip 生成一个代理池，在爬虫请求之前，我们会从代理池中随机抽取一个 ip 做代理。

> 应对 http 请求中的 user-agent 问题

程序中实现了一个 user-agent 池，每次请求都会随机取出一个 user-agent 使用，目前在程序中集成了 17 种 user-agent，后续会考虑把这块开放出来到配置中，自定义配置（有没有意义呢？）。

> 程序中的异常处理问题

目前在异常处理这块，本身也不是非常擅长，已经尽力把异常控制在一个可控的范围内，程序中定义了很多自定义异常，这里没有什么发言权，就不细说了，各位要是有意见建议，欢迎拍砖。

## 所谓深度爬取

程序中并没有现成的深度爬取实现，是因为一般情况下我并不觉得深度爬取有什么卵用，但是也不是没有为深度爬取留出来一席之地。我们可以自己提取出页面中的链接并加入到任务队列中。以达到深度爬取的效果。

```java
public class DemoStore implements IStore {

    private String id = NameUtils.name(DemoStore.class);

    public DemoStore() throws IOException {}

    @Override
    public void store(TaskResponse response) throws IOException {
        List<String> urls = response.select("a").stream().map(element -> element.attr("href")).collect(Collectors.toList());
        try {
            response.getQueue().push(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## 注解支持
最近忙里偷闲增加了注解支持，那么在使用注解之后，一个爬虫是什么样的呢？

```java
@EnableAutoConfiguration
@AppName("hello spider")
@Store(PrintStore.class)
@AutoClose(true)
@ThreadConfig(num = 1)
@CookieConfig("asdfasdfasdfasdfasfasdfa")
@HttpHeaderConfig({
        "key1=value1",
        "key2=value2"
})
@ProxyConfig("1.1.1.1,2.2.2.2")
public class CockroachApplicationTest {
    public static void main(String[] args) throws Exception {
        TaskQueue queue = TaskQueue.of();
        queue.push(new Task("http://blog.zhangyingwei.com"));
        CockroachApplication.run(CockroachApplicationTest.class,queue);
    }
}
```
如上就是基本上所有注解的演示，那么抛开演示的部分，如果真的只是做一个demo，需要怎么写呢？

```java
@EnableAutoConfiguration
public class CockroachApplicationTest {
    public static void main(String[] args) throws Exception {
        TaskQueue queue = TaskQueue.of();
        queue.push(new Task("http://blog.zhangyingwei.com"));
        CockroachApplication.run(CockroachApplicationTest.class,queue);
    }
}
```
没错，就是这么简单。这个爬虫就是爬取 `http://blog.zhangyingwei.com` 这个页面的内容并将结果打印出来。
在爬虫结果处理这个问题上，程序中默认使用 PringStore 这个类将所有结果打印出来。

## 动态 header 支持
最近做了一个工作职位的爬虫，在爬拉钩的时候遇到一个问题。需要登录才能爬取，这个当然配置 cookie 就能解决，但是拉钩的 cookie 里边做了防爬虫验证。cookie 里边有一个时间需要动态变化。所以就产生了这个功能。

这个功能使用起来如下：

### Cookie 生成器

```java
@CookieConfig(cookieGenerator = CookieGeneratorTest.class)
```

```java
/**
 * Created by zhangyw on 2017/12/19.
 */
public class CookieGeneratorTest implements StringGenerator {

    @Override
    public String get(Task task) {
        String cookie = "v="+ UUID.randomUUID().toString();
        System.out.println(cookie);
        return cookie;
    }
}
```

在每次发生 http 请求之前，程序都会调用 Generator 的 get 方法。获取到本次的 cookie 值，并附加到 http 请求头中。

### Header 生成器

由于程序中需要的 header 是 map 类型的数据，所以 header 生成器如下：

```java
@HttpHeaderConfig(headerGenerator = HeaderGeneratorTest.class)
```

```java
/**
 * Created by zhangyw on 2017/12/19.
 */
public class HeaderGeneratorTest implements MapGenerator {
    private Map headers = new HashMap();
    @Override
    public Map get(Task task) {
        return headers;
    }
}
```

以上就是目前所有的生成器，可以看到生成器中传入了 task 对象，这里是为了在爬虫应对不同的地址的时候使用不同的 cookie/header 。

算了还是举个栗子吧：

```java
/**
 * Created by zhangyw on 2017/12/19.
 */
public class HeaderGeneratorTest implements MapGenerator {
    private Map headers = new HashMap();
    @Override
    public Map get(Task task) {
        if ("jobs.lagou".equals(task.getGroup())) {
            header.put("key","value");
            return headers;
        } else {
            return null;
        }
    }
}
```

OK，到此为止，就啰嗦这么多了。

## 队列过滤器

最近遇到一个需求，要对需要入队的 task 进行有选择的入队。 例如： 如果 url 为空，则放弃入队。于是便产生了入队过滤器。

过滤器依赖 IQueueTaskFilter 接口。

创建过滤器示例：
```java
/**
 * @author: zhangyw
 * @date: 2018/1/19
 * @time: 下午2:37
 * @desc: 过滤所有入队的task
 */
public class TestQueueTaskFilter implements IQueueTaskFilter {
    @Override
    public boolean accept(Task task) {
        return StringUtils.isNotBlank(task.getUrl()) && task.getUrl().contains("baidu");
    }
}
```

使用方法：

```java
/**
 * @author: zhangyw
 * @date: 2018/1/19
 * @time: 下午2:32
 * @desc:
 */
public class DefaultQueueTaskFilterTest {
    @Test
    public void accept() throws Exception {
        CockroachQueue queue = TaskQueue.of().filter(new TestQueueTaskFilter());
        queue.push(new Task(null));
        queue.push(new Task("http://baidu.com"));
        queue.push(new Task("https://google.com"));
    }
}
```

日志：

```text
[INFO ][2018/01/19 15:33:00 ][com.zhangyingwei.cockroach.queue.TaskQueue] create queue whith calacity 2147483647
[INFO ][2018/01/19 15:33:00 ][com.zhangyingwei.cockroach.queue.TaskQueue] main Task{id='Task-1', group='default', url='null'} is not accepted by class com.zhangyingwei.cockroach.queue.TestQueueTaskFilter
[INFO ][2018/01/19 15:33:00 ][com.zhangyingwei.cockroach.queue.TaskQueue] main push task Task{id='Task-2', group='default', url='http://baidu.com'}
[INFO ][2018/01/19 15:33:00 ][com.zhangyingwei.cockroach.queue.TaskQueue] main Task{id='Task-3', group='default', url='https://google.com'} is not accepted by class com.zhangyingwei.cockroach.queue.TestQueueTaskFilter
```

## xpath 选择器支持

本来选择器只支持 css 选择器，但是有些时候，xpath 选择器还是非常方便的。所以使用了 JsoupXpath 来支持 xpath 选择器，同时与 jsoup 完美结合。

BUT，最终还是发现， JsoupXpath 对 xpath 中的数组选择存在一定的问题，当然也可能是我用的不对，有知道的老铁请联系我。

以 `https://www.cnblogs.com/wanghaomiao/p/4899355.html` 这个页面为例。

示例：

```java
/**
 * @author: zhangyw
 * @date: 2018/1/21
 * @time: 下午3:06
 * @desc:
 */
public class SelecterTestStore implements IStore {
    @Override
    public void store(TaskResponse response) throws Exception {
        String title = response.select("title").text();
        System.out.println(title);
        String res = response.xpath("//*[@id='cnblogs_post_body']/h2").get(2).text();
        System.out.println(res);
    }
}
```

结果：

```text
Java开源的支持xpath的html解析器介绍--JsoupXpath - 无忌小伙 - 博客园
二 函数
```

注意：

这里本来的 xpath 选择器应该是： `//*[@id='cnblogs_post_body']/h2[3]` ，但是在使用 `[3]` 的时候报错了，可能是这个库对此类语法的支持有问题。

所以，本着条条大路通罗马的精神，使用 `response.xpath("//*[@id='cnblogs_post_body']/h2").get(2)` 来代替。

同时，程序中对 `JsoupXpath` 本来的结果集进行了进一步的封装，使之返回 `jsoup` 的 `Elements` 对象，所以之后可以直接连接 `jsoup` 的 `css` 选择器。

## scala & kotlin

作为目前使用的 jvm 系语言几大巨头，scala 与 kotlin 这里基本上对跟 java 的互调做的很好，但是这里还是给几个 demo。

### scala

```scala
/**
  * Created by zhangyw on 2017/12/25.
  */
class TTTStore extends IStore{
    override def store(taskResponse: TaskResponse): Unit = {
        println("ttt store")
    }
}

object TTTStore{}
```

```scala
/**
  * Created by zhangyw on 2017/12/25.
  */
@EnableAutoConfiguration
@ThreadConfig(num = 1)
@Store(classOf[TTTStore])
object MainApplication {
    def main(args: Array[String]): Unit = {
        println("hello scala spider")
        val queue = TaskQueue.of()
        queue.push(new Task("http://blog.zhangyingwei.com"))
        CockroachApplication.run(MainApplication.getClass(),queue)
    }
}
```

### kotlin

```kotlin
class TTTStore :IStore{
    override fun store(response: TaskResponse) {
        print("ttt store")
    }
}
```

```kotlin

/**
 * Created by zhangyw on 2017/12/25.
 */
@EnableAutoConfiguration
@ThreadConfig(num = 1)
@Store(TTTStore::class)
object MainApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        print("hello kotlin spider")
        val queue = TaskQueue.of()
        queue.push(Task("http://blog.zhangyingwei.com"))
        CockroachApplication.run(MainApplication::class.java, queue)
    }
}
```

## 关于分布式，我有话说

现在网上是个爬虫就要搞一下分布式，这令我很不爽。

实际上我看过几个所谓的分布式爬虫源码，他们所谓的分布式，连伪分布式都算不上！！！使用个 redis 做消息中间件就分布式了吗？ 这就是所谓的分布式？？这根本就不是分布式，本来我也准备使用 redis 做消息中间件来装个分布式的 B，但是写了一半忽然觉得有点恶心，遂删除了代码，还程序一个清静，也还我自己一个安心。

分布式这个坑肯定是要挖的！！！

所以，我的分布式将会包括：

* 分布式消息中间件（有可能会使用 redis 或者自己实现一个; 为了还程序一个清静，最有可能会自己实现一个）
* 分布式任务调度
* 分布式容错机制
* 分布式事务
* 状态监控

所以，这个坑是越来越大了么？？我靠，有点怕怕！！
至于这个坑什么时候填上，还能不能填上，看心情咯。。。

实际上，到现在我还没心情填这个分布式的坑。。。

## PS

昨天下午开了几十个线程爬知乎，结果公司网管说疑似有 DOS 攻击，吓得我赶紧放在云上跑。

能看到这里你也是挺厉害了，留个 star 呗！😺😺


## 联系方式
* 邮箱： zhangyw001@gmail.com
* 微信： fengche361


## Lisence

Lisenced under [Apache 2.0 lisence](./LICENSE)