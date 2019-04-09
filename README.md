# cockroach 爬虫：又一个 java 爬虫实现

[![](https://travis-ci.org/zhangyingwei/cockroach.svg?branch=master)](https://travis-ci.org/zhangyingwei/cockroach)
[![](https://img.shields.io/badge/language-java-orange.svg)]()
[![](https://img.shields.io/badge/jdk-1.8-green.svg)]()
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

![](http://util.zhangyingwei.com//cockroach/1/carbon.png)

重构了
[cockroach2](https://github.com/zhangyingwei/cockroach2)

## 简介

cockroach[小强] 当时不知道为啥选了这么个名字，又长又难记，导致编码的过程中因为单词的拼写问题耽误了好长时间。

这个项目算是我的又一个坑吧，算起来挖的坑多了去了，多一个不多少一个不少。

一个小巧、灵活、健壮的爬虫框架，暂且叫做框架吧。

简单到什么程度呢，几句话就可以创建一个爬虫。

### 依赖部分

```xml
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

### 代码部分：

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

## 联系方式
* 邮箱： zhangyw001@gmail.com
* 微信： fengche361

## Lisence

Lisenced under [Apache 2.0 lisence](./LICENSE)
