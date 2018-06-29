# 快速开始

## 环境准备

>* Java8
* Maven

推荐使用 maven 构建工具，可以方便的管理项目中的依赖。

```xml
<!-- https://mvnrepository.com/artifact/com.github.zhangyingwei/cockroach-core -->
<dependency>
  <groupId>com.github.zhangyingwei</groupId>
  <artifactId>cockroach-core</artifactId>
  <version>1.0.6-Beta</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.github.zhangyingwei/cockroach-annotation -->
<dependency>
    <groupId>com.github.zhangyingwei</groupId>
    <artifactId>cockroach-annotation</artifactId>
    <version>1.0.6-Beta</version>
</dependency>
```

## 编写代码

引入依赖之后，在项目中新建一个类，这里以 App.java 举例。

```App.java
@EnableAutoConfiguration
@AppName("一个最简单的爬虫")
public class App {
    public static void main(String[] args) throws Exception {
        CockroachQueue queue = TaskQueue.of();
        queue.push(new Task("http:zhangyingwei.com"));
        CockroachApplication.run(App.class, queue);
    }
}
```

这段代码的意思就是创建一个任务队列，然后在队列中加入一个任务，任务的内容是爬取网站`http://zhangyingwei.com`的首页内容 。

直接 `run` 这个类，我们就可以得到爬取结果。对结果的处理在程序中是通过 [Store](/annotations?id=store) 类来处理的，如果不加以配置的话，就会使用默认的 `PrintStore` 来做最简单的打印处理。

运行日志如下

```text
[2018-06-29 16:22:15] INFO  TaskQueue:35 - create queue whith calacity 11
[2018-06-29 16:22:15] INFO  TaskQueue:74 - main push task Task{id='Task-1', group='default', url='http:zhangyingwei.com', params={}, selects=null, extr=null, retry=0, deep=0}
[2018-06-29 16:22:15] INFO  CockroachContext:32 - starting...
[2018-06-29 16:22:15] INFO  CockroachConfig:203 - ---------------------------config--------------------------
[2018-06-29 16:22:15] INFO  CockroachConfig:204 - AppName: 一个最简单的爬虫
[2018-06-29 16:22:15] INFO  CockroachConfig:205 - Proxys: null
[2018-06-29 16:22:15] INFO  CockroachConfig:206 - Threads: 1
[2018-06-29 16:22:15] INFO  CockroachConfig:207 - ThreadSleep: 500
[2018-06-29 16:22:15] INFO  CockroachConfig:208 - IHttpClient: class com.zhangyingwei.cockroach.http.client.okhttp.COkHttpClient
[2018-06-29 16:22:15] INFO  CockroachConfig:209 - HttpClientProgress: false
[2018-06-29 16:22:15] INFO  CockroachConfig:210 - Store: class com.zhangyingwei.cockroach.store.PrintStore
[2018-06-29 16:22:15] INFO  CockroachConfig:211 - Cookie: null
[2018-06-29 16:22:15] INFO  CockroachConfig:212 - CookieGenerator: class com.zhangyingwei.cockroach.common.generators.NoCookieGenerator
[2018-06-29 16:22:15] INFO  CockroachConfig:213 - HttpHeaders: null
[2018-06-29 16:22:15] INFO  CockroachConfig:214 - HttpHeadersGenerator: class com.zhangyingwei.cockroach.common.generators.NoHeaderGenerator
[2018-06-29 16:22:15] INFO  CockroachConfig:215 - AutoClose: false
[2018-06-29 16:22:15] INFO  CockroachConfig:216 - TaskErrorHandler: class com.zhangyingwei.cockroach.http.handler.DefaultTaskErrorHandler
[2018-06-29 16:22:15] INFO  CockroachConfig:217 - ResponseFilters: []
[2018-06-29 16:22:15] INFO  CockroachConfig:218 - -------------------------------------------------------------
[2018-06-29 16:22:15] INFO  ExecuterManager:96 - bulid response filters
[2018-06-29 16:22:15] INFO  BootstrapExecutersListener:13 - BootstrapExecutersListener.onStart
[2018-06-29 16:22:15] INFO  DefaultExecutersListener:15 - executers start...
[2018-06-29 16:22:15] INFO  ExecuterManager:106 - bulid httpclient
[2018-06-29 16:22:15] INFO  ExecuterManager:62 - new thread:TaskExecuter-1
[2018-06-29 16:22:15] INFO  TaskQueue:50 - pool-1-thread-1 take task Task{id='Task-1', group='default', url='http:zhangyingwei.com', params={}, selects=null, extr=null, retry=0, deep=0}
[2018-06-29 16:22:16] INFO  TaskExecuter:60 - TaskExecuter-1 GET - Task{id='Task-1', group='default', url='http:zhangyingwei.com', params={}, selects=null, extr=null, retry=0, deep=0}
<!DOCTYPE html>
<html>
<head>
    <title>张英伟的主页</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <meta charset="utf-8">
    <meta name="keywords" content="张英伟,zhangyingwei,张英伟的个人主页,张英伟的主页,主页,个人主页">
    <meta name="description" content="张英伟的主页">
</head>
<body>
    <div class="bg"></div>
    <div class="bg bg2"></div>
    <div class="bg bg3"></div>
    <div class="main-container">
    	<div class="title">
    		<i class='icon-main'></i>
    		<span id="title"></span>
    	</div>
    	<div class="content">
    		<ul id="items"></ul>
    	</div>
    	<div class="foot">
    		<a target="_blank" href="http://www.miitbeian.gov.cn/">京ICP备15037338号-1</a>
    	</div>
    </div>
</body>
<script type="text/javascript" src="js/index.js"></script>
</html>

[2018-06-29 16:22:16] INFO  TaskQueue:67 - pool-1-thread-1 queue is empty
```

## 编译打包
上边使用的是 maven 中央仓库的版本。如果想感受最新版本可以自己进行打包。在打包之前我们需要安装好 git 并配置好 maven 环境变量。

在准备工作都完成之后，需要三步进行打包。

1. 将项目克隆到本地

```git
git clone https://github.com/zhangyingwei/cockroach.git
``` 
2. 切换目录到cockroach

```shell
cd cockroach
```

3. 编译打包

```maven
mvn clean install
```

在执行完上边三步之后，我们就成功的将程序编译打包并安装到了 maven 本地的仓库中。

