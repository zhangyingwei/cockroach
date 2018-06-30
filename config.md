# 配置介绍

## Store

程序中使用 `Store` 类来做结果处理。我们需要创建一个类并实现 `IStore` 接口。

```java
public class MyStore implements IStore {
    @Override
    public void store(TaskResponse response) throws IOException {
        System.out.println(response.getContent().string());
    }
}
```

在 `store` 方法中我们会得到一个 `TaskResponse` 类，这个类中包含了任务信息、结果信息以及贵结果进行处理的一些方法。具体的使用方法参考 [TaskResponse]() 小节。

## Generator

`Generator` 是为了应对程序中需要变化的一些配置而产生的接口，共包含两种。

### StringGenerator

根据名称我们可以猜到，这个接口主要是为了应对 `String` 类型的配置。例如程序中的 `Cookie` 生成器就需要实现 `StringGenerator`

举例

```java
public class MyCookieGenerator implements StringGenerator {
    @Override
    public String get(Task task) {
        return "this is cookie";
    }
}
```

这里我们可以看到在 `get` 方法中，有一个 `task` 参数。在设计之初，加入这个参数的目的在与为不同的任务匹配不同的 `Cookie`。

举个例子： 假设我们的任务中总共有两个网站的任务，分别为 http://website1.com 与 http://website2.com。 我们要分别为这两个地址匹配 `Cookie1` 与 `Cookie2`，在程序中我们可以这么写

```java
public class MyCookieGenerator implements StringGenerator {
    @Override
    public String get(Task task) {
        if("http://website1.com".equals(task.getUrl())){
            return "cookie1";
        }else if("http://website2.com".equals(task.getUrl())){
            return "cookie2";
        }
        return "this is cookie";
    }
}
```

### MapGenerator

同样，根据名称。一眼看上去这个接口就是为了产生 `Map` 类型的配置而存在的。程序中 `Header` 生成器就是基于 `MapGenerator` 实现的。

```java
public class NoHeaderGenerator implements MapGenerator {
    @Override
    public Map get(Task task) {
        return new HashMap();
    }
}
```

同样，这里的 `task` 也是为了为不同的任务匹配不同的 `header` 而存在的。具体方法参考上边     `MyCookieGenerator`。

## Listener

### ExecutersListener

很多程序中都存在生命周期的说法，这里有点类似生命周期。 `Listener` 定义了 `onStart` 与 `onEnd` 两个方法，分别在程序启动之前与程序停止之后执行。

```java
public class MyExecutersListener implements IExecutersListener {
    @Override
    public void onStart() {
        System.out.println("executers start...");
    }

    @Override
    public void onEnd() {
        System.out.println("executers end...");
    }
}
```

## Handler

### TaskErrorHandler

每一个执行失败的任务都需要给用户一个交代，所以我们创造了 `TaskErrorHandler` 来收集每一个执行失败的任务。

```java
public class MyTaskErrorHandler implements ITaskErrorHandler {
    @Override
    public void error(TaskErrorResponse response) {
        response.getQueue().push(response.getTask())
        System.out.println( response.getTask()+ " error ")
    }
}
```

在这里我们可以选择对执行失败的任务重新提交或者做其他处理。以上代码就是对失败任务重新提交。

## Filter

### TaskResponseFilter

Google 有一种很出门的算法叫 `Simhash`算法，其主要功能就是网页去重。这个接口就是为了对爬取结果进行去重。

```java
public class MyTaskResponseFilter implements ITaskResponseFilter {
    @Override
    public boolean accept(TaskResponse response){
        String content = response.getContent();
        return true;
    }
}
```

这里 `accept` 方法返回值是一个 `Boolean` 类型，如果返回 `true` 则代表合法结果，如果返回 `false` 则代表非法结果。