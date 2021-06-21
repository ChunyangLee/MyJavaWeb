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

### 3. 书城Demo
书城项目，登陆和注册业务实现， 了解JavaEE的三层架构。
#### 3.1 JavaEE三层架构

##### Druid数据库连接池，连接mysql失败
 Access denied for user ''@'localhost' (using password: YES)
 `解决` 配置文件中username=root写成了user=root，

##### Servlet程序和数据库交互时出的错
`ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");`这种方式加载jdbc配置文件，部署到Tomcat上会报异常。

##  2021.6.21 上

### 1. JSP
解决servlet回传html数据的繁琐问题，

#### 1.1 什么是jsp文件

#### 1.2 引出的问题，内容和html类似，但是可以像普通的servlet程序那样写东西么？
##### jsp的头部page指令，可修改jsp界面一些属性
> import
> errorPage
> ...
>
##### 声明脚本
声明 属性，方法，代码块等等，在jsp翻译后的类中加入。
##### 表达式脚本
用于输出到页面上，代码脚本中写的sysout是输出到控制台的
##### 代码脚本
* 翻译到`_jspservice`方法中，因此可以使用`request`，`response`等现有九个内置对象。
``` Java
 public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {}
```
* 可以配合表达式脚本，输出到页面上。
* 一个语句也可以拆开写，
```jsp
<table border="3">
    <%
        for (int i = 0; i < 10; i++) {
    %>
    <tr>
        <td><%="第"+i+"行"%></td>
    </tr>

    <%
        }
    %>
```
 
#### 1.3 九大内置对象，四大域对象
* 四个域对象
```java
        //当前jsp页面有效
        pageContext.setAttribute("key", "PageContext对象，pageContext"); 
        //一次请求， 分发可以， 
        request.setAttribute("key", "request");
        //浏览器关了才无效
        session.setAttribute("key", "HttpSession对象session");
        //整个工程都有效
        application.setAttribute("key", "ServletContext对象，application");
```
* out对象 （javax.servlet.jsp.JspWriter out）
```Java
    //一般使用out来输出，因为jsp底层翻译用的都是out输出， 用response的write，可能会打乱，
    //out会在response的缓冲区后追加，最后一起输出给客户端。
```

#### 1.4 常用标签
##### 静态包含
直接将footer的翻译内容放到main的翻译内容里

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Title</title>
    </head>
    <body>
            头部信息 <br>
            主题内容 <br>
            <%@include file="/include/footer.jsp"%>
    </body>
    </html>
##### 动态包含,(现在基本上不用了)
* 在main的翻译内容里，只包含一条语句，`JspRuntimeLibrary.include(request, response, "/include/footer.jsp", out, false);`，
    可以看到将main的out传过去了，因此footer可以在main的out缓冲区写东西，最后一期输出给客户端

* 此外，因为传了request，因此可以传递参数，给footer中使用
```jsp
    <body>
            头部信息 <br>
            主题内容 <br>
            <%@include file="/include/footer.jsp"%>
    
            动态包含，传递参数给footer使用 <br>
            <jsp:include page="/include/footer.jsp">
                <jsp:param name="username" value="root"/>
                <jsp:param name="password" value="123456"/>
            </jsp:include>
    </body>
```

##### 请求转发  
    <jsp:forward page="/scope2.jsp"></jsp:forward>
    
##  2021.6.21 下

### 2.  类的加载起加载jdbc资源报错
断点找到错误是，`URL url = classLoader.getResource("jdbc.properties");` 语句得到的是`null`，
从别的module复制的包进新的module，  在原来的module中是好用的，新的module就不行了。


### 3. Listenner
`javax.servlet.ServletContextListener ServletContext` 监听器
监听器的使用步骤。 
* 第一步:我们需要定义一个类。然后去继承生命周期的监听器接口。 
* 第二步:然后在 Web.xml 文件中配置。

### 4. EL表达式
替换jsp中的表达式输出，`<%= %>`
主要用来输出域中的值。
#### 4.1 取key的顺序
    1. 先从PageContext中找 <br>
    2.  request <br>
    3.  session <br>
    4. application <br>
        
#### 4.2 输出复杂Bean中的属性
本质是找类中的get方法，
#### 4.3 EL里的运算

##### empty运算
${empty key} ， 
> "没有内容的"都为空， 空串，数组长度为0等

##### .运算和[]运算
* 取Bean中的属性的时候用.  `${p.name}`   setAttribute("p",person)
* map中key为特殊字符，可以用[]，如`map['a.a']`  map.a.a是不行的。
  普通的key的话，对于map.key是可以的

##### EL中的11个隐藏对象
    * pageContext     PageContextimpl类型       可以获取jsp中九大内置对象
    * pageScope       Map类型, (下面都是)          获取域中的数据
    * requestScope
    * sessionScope
    * applicationScope

    param                                       请求参数
    paramValues                                  多个值的时候
     
    header
    headerValues

    cookie                                      获取当前请求的cookie信息
    
    initParam                                   web.xml中配置的<context-param>中的参数
pageContext 对象，可以获得jsp中内置的对象， 一般使用request获得协议，ip，端口等

    ${pageContext.request.contextPath}， 获取工程路径，  /08_jsp

### 5. JSTL标签库
Jsp Standard Tag Library， 替换JSP中的代码脚本，上面的EL是替换表达式脚本，这样编写jsp代码就更加简洁了。

#### 5.1 怎么使用
> 先导入JSTL标签库的jar包，
> taglib指令导入包
>

#### 5.2 导入jar包后也无法使用的情形， 在problem中修复， 确保artifacts里面有才行。

#### 5.3 常用标签
set 标签
`<c:set scope="page" var="key" value="value" />`
if 标签
``` jsp
    <c:if test="${12==12}">
        \${}里面可以写运算
    </c:if>
```
choose when 实现多路判断
```jsp
    <c:choose>
        <c:when test="${pageScope.key == 'value'}">
            key=value;
    </c:when>
        <c:when test="${pageScope.key !='value'}">
            key!=value
        </c:when>
    </c:choose>
```
foeEach标签




