# 关键类介绍

> 在整个框架中，有两几个类是与用户交互最为密切的。这里着重做介绍。

## Task

Task 在程序中是被处理的任务。主要包含了以下几个功能：

* 任务内容

```java
new Task("http://zhangyingwei.com");
```

* 任务分组

为了区分每个任务的类型，程序中为任务提供了 `Group` 属性来标识每个任务。举例

```java
new Task("http://zhangyingwei.com","group1");
new Task("http://blog.zhangyingwei.com","group2");
```

这样我们就可以根据 Group 内容很容易的区分出当前任务是属于哪个网站。具体的应用场景要是要在 [TaskResponse](#TaskResponse) 中体现。

* 重试次数

任务免不了有失败的，对于失败的任务我们的做法往往是多试几次。这里提供了 `retry` 方法来标识我们想让一个任务最多失败几次，换种说法就是如果一个任务失败了，我们要试多少次才相信他真的是一个必败无疑的任务。

```java
new Task("http://zhangyingwei.com","group1").retry(100);
```

* 任务优先级

在一个爬虫程序中，会有很多种任务。有可能每种任务我们需要执行的优先级是不一样的。

在框架中提供了一个 `deep` 属性来控制任务的优先级, `deep` 的值越大，说明此任务的优先接越高。对于 `deep` 的操作提供了以下方法。

> 如果不设置 deep 值，默认为 0。

```java
new Task("http://zhangyingwei.com","group1").setDeep(0); //设置优先级
new Task("http://zhangyingwei.com","group1").addDeep(1); //在当前优先级的基础上加 1
new Task("http://zhangyingwei.com","group1").getDeep(); //获取当前任务的优先级
new Task("http://zhangyingwei.com","group1").nextDeepBy(task1); //在 task1 的优先级基础上加 1
```

## TaskResponse

其实一个爬虫说白了就是两步， 获取到请求内容以及将内容中所需要的部分提取出来。这个类主要负责的就是对请求返回结果做处理。

得到这个类，我们可以做很多事情，比如说：

* 获取到任务队列

有一个词语叫深度爬取，大概意思就是我们需要从获取到的页面上获取到地址再次进行爬取，递归爬取整个词大概能说明情况了吧。所以我们有可能需要在结果中提取出一些新的任务然后放入到任务队列中继续进行爬取。

!> 不知道有没有表达清楚，总之在一些情况下，我们需要在结果处理的时候获取到任务队列。

```java
public class MyStore implements IStore {
    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        CockroachQueue queue = taskResponse.getQueue();
    }
}
```

* 对结果做处理

我们忙活了那么，最重要的就是获取到我们想要的结果，好啦，最关心的来啦。

这里总共分为两大类：

> * 文本类数据

文本类数据指的是返回的数据是字符串类型。主要包含最常见的 html 网页类型，json 文本类型，xml 文本类型以及其他的一些不常见的文本类型等。

这里分为两种情况作处理：

?> html类型

对于 `html` 类型，我们提供了 `css` 选择器 与 `xpath` 选择器两种选择器。

css选择器

```java
public class MyStore implements IStore {
    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        String title = taskResponse.select("title").text();
    }
}
```

xpath 选择器

```java
public class MyStore implements IStore {
    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        String title = taskResponse.xpath("/html/head/title").text();
    }
}
```

?> 其他类型

对于其他类型，这里也分为两种情况来说明，一种是程序内置解析器的类型，另外一种就是程序中不具备解析器的类型。

  * JSON 类型

先说程序中内置了解析器的类型，即 json 类型:

```java
public class MyStore implements IStore {
    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        JSONObject jsonObject = taskResponse.toJsonObject(); // 转化为 json 对象
        JSONArray jsonArray = taskResponse.toJsonArray(); // 转化为 json 数组
    }
}
```

!> 上边的示例程序中，给出两种解析结果。一种为 `JSONObject` 类型，另外一种为 `JSONArray` 类型。需要说明的是这两种结果是需要根据实际情况来定的。我们需要在分析 `url` 的时候确定返回结果是 `JSONObject` 还是 `JSONArray`，然后在程序中使用对应的方法。

  * 其他类型

  其他类型指的是程序中不具备解析器的文本类型。既然程序中不具备,那么就需要我们自己解析文本内容了。

```java
public class MyStore implements IStore {
    @Override
    public void store(TaskResponse taskResponse) throws Exception {
        String content = taskResponse.getContent().string();
        selfContentParser(content); // 需要自己适配的内容解析器
    }
}
```

> * 二进制数据


* 获取到本地任务的信息