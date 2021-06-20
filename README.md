# MyJavaWeb 
[HTML5参考手册](https://www.w3cschool.cn/html5_reference.html)

## 正则表达式，
[w3cschool菜鸟教程 新版本.chm](https://github.com/ChunyangLee/MyJavaWeb/blob/master/w3cschool%E8%8F%9C%E9%B8%9F%E6%95%99%E7%A8%8B%20%E6%96%B0%E7%89%88%E6%9C%AC.chm)

## Tomcat服务器配置问题
执行`startup.sh`命令后， 可以启动且显示 Tomcat started。
但是，访问localhost:8080失败， 
同时执行关闭命令`shutdown.sh`命令后，显示找不到找不到jdk目录。
### 解决：
重新配置jdk目录即可

## IDEA集成Tomcat
### 问题描述
每次新建Web工程，都要配置Tomcat目录的conf配置文件，使Tomcat服务器能找到Web导出的`.../out/artifacts/xxx_war_exploded`路径，
### 解决
可以设置导出路径，直接导出到Tomcat目录的workapps中， 但是如果不导出到workapps中，
在配置时，把`deploy applications configured in Tomcat instance`勾上就行，否则默认不部署到Tomcat服务器上，才需要上面手动部署。

## 2021.6.20
### 1. HttpServletRequest类
每次只要有请求进入 Tomcat 服务器，Tomcat 服务器就会把请求过来的 HTTP 协议信息解析好封装到 `Request` 对象中。 
然后传递到 `service` 方法(`doGet` 和 `doPost`)中给我们使用。
我们可以通过 `HttpServletRequest` 对象，获取到所有请求的信息。

####  1.1 常用方法
``` java
getRequestURI()
getRequestURL()
getRemoteHost()
getHeader()
getMethod()

getParameter(String name)     // 在获取参数之前，指定编码字符集，可解决Post请求中文乱码问题，
getParameterValues(String name)

//请求转发可以用
setAttribute(String key, String value)
getAttribute(String key)
getRequestDispatcher(String url)

```

####   1.2 请求转发
浏览器地址栏中的地址不变，

####   1.3 base标签
写在&lt;head&gt;里面，作为相对路径的基路径


### 2. HttpServletResponse类
返回给客户端信息

#### 2.1 获得字符/字节流
``` java
resp.getWriter()
resp.getOutPutStream()
```
响应默认字符集是ISO-8859-1不支持中文，可以设置字符集。`setCharacterEncoding("utf-8")`
同时要设置响应头，让浏览器用utf-8解析才行。 一个是设置编码时，一个是设置解码时。
或直接使用`resp.setContentType("text/html; charset=UTF-8")`，但是要在流对象之前。

#### 2.2 请求重定向
![请求重定向框图](/images/2.2.1.png)

重定向，相当于浏览器又访问了一次，只不过地址是给定的。
因此可以访问工程外的资源，不能访问WEB-INF中的资源，也不共享Request中的数据，