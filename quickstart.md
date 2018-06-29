# 快速开始

## 环境准备

* java8
* maven

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

# 编写代码

引入依赖之后，在项目中新建一个类，这里以 App.java 举例。

```App.java
public static void main(String args[]){
  
}
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

