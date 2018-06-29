# 注解介绍

注解是一个很神奇的东西，有了注解我们可以大幅度的简化很多代码，这里会详细解释每一种注解的作用以及用法。

但是如果你不想使用注解，那么请看 [抛开注解配置 Cockroach]()

## AppName

每一个爬虫都是需要尊严的，至少要给他们一个名字，所以就有了这个注解。

使用方法

```annotation
@AppName("一只有尊严的爬虫")
public class App{
  ...
}
```

## AutoClose

当队列中所有的任务都执行完了之后，我们是需要爬虫立马关闭还是需要它一直阻塞直到来了新的任务，然后继续执行任务。

所以这个注解就是为了解决上诉问题，注解的值为 `Boolean` 类型。实际上如果我们不添加这个注解，程序中默认为 false。

使用方法

```annotation
@AutoClose(true)
public class App{
  ...
}
```

## CookieConfig

做过爬虫项目的都知道，在一个爬虫工程中， `Cookie` 通常扮演着非常重要的角色。差不多相当于唐僧手中的通关文谍吧。

几乎所有的网站对登录状态的验证都是通过 `Cookie` 这个东西。所以在处理一些登录情况的时候，我们通常需要将 `Cookie` 绑定在请求中。

所以为了解决这个问题，产生了这个注解。这个注解在使用的过程中有两种使用方法：

1. 直接将 `Cookie` 值配置在注解中。

```annotation
@CookieConfig("这里是cookie的值")
public class App{
  ...
}
```
这种情况下，所配置的 `Cookie` 的值会应用在所有的请求中。

2. 使用 `Cookie` 生成器动态生成 `Cookie`

很多时候我们在一个程序中爬取不同的任务，而这些任务不一定在同一个网站中，所以我们需要针对不同的地址匹配不同的 `Cookie`.所以就产生了 `Cookie` 生成器。
`Cookie` 生成器在框架中叫 `CookieGenerator`，具体的介绍请看这里 [CookieGenerator]()
```annotation
@CookieConfig(cookieGenerator = MyCookieGenerator.class)
public class App{
  ...
}
```
这么做有什么好处呢？ 就是我们可以应对不同的网址使用不同的 `Cookie` ，具体的实例可以看 [CookieGenerator]() 这一小节。

## EnableAutoConfiguration

很多时候，我们非常懒。懒到几个注解都觉得烦。于是便有了这个注解。当我们应用这个注解的时候，程序会加载一些默认设置，会在一定程度上减轻我们的配置工作。

尤其是对新手来说，往往不知道都需要配置什么。这个时候，使用这个注解就对了。

实际上这个注解主要帮我么添加了四个基本配置

* [AppName](/annotations?id=appname)
* [AutoClose](/annotations?id=autoclose)
* [TaskErrorHandlerConfig](/annotations?id=taskerrorhandlerconfig)
* [ThreadConfig](/annotations?id=threadconfig)

## ExecutersListener

有些时候，我们需要在程序启动或者结束的时候做一些事情。在应对这种情况的时候前辈们的经验告诉我，这个工种的名字一般叫 `Listener`。

使用方法：

```annotation
@ExecutersListener(MyExecutersListener.class)
public class App{
  ...
}
```

在 `Listener` 里边，我们提供了 `onStart` 与 `onEnd` 两个方法，具体的介绍请看 [ExecutersListener]() 小节

## HttpHeaderConfig

跟 `Cookie` 类似，有些时候，在我们的请求中，需要对 Http 请求的请求头做一些个性化的配置，最常见的配置就是 `Referer` 与 `User-Agent`。

同样我们也提供了两种配置方式。

1. 直接将 `Header` 的值配置在注解中

```annotation
@HttpHeaderConfig({
        "Referer=http://baidu.com",
        "User-Agent=Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36"
})
public class App{
  ...
}
```

2. 使用 `Header` 生成器

```annotation
@HttpHeaderConfig(headerGenerator = MyHeaderGenerator.class)
public class App{
  ...
}
```

具体实例可以看 [HeaderGenerator]() 这一小节

## ProxyConfig

## Store

## TaskErrorHandlerConfig

## TaskResponseFiltersConfig

## ThreadConfig