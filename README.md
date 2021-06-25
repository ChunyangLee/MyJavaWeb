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

EL表达式取数组元素
``` jsp
    <%
        List list = new ArrayList();
        list.add(123);
        list.add(13);
        list.add(23);
        request.setAttribute("list", list);
    %>
    ${requestScope.list.get(1)}
    <hr>
```

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
forEach标签
``` jsp
    <%
        request.setAttribute("m", map);
    %>
    <c:forEach items="${requestScope.m}" var="item">
            ${item} <br>
            ${item.key} .运算会找到map中响应的getKey方法和getvalue方法
    </c:forEach>
    
    <c:forEach begin="1" end="10" var="i">
        <tr>
        <c:forEach begin="1" end="5" var="j">
            <td>${i*j}</td>
        </c:forEach>
        </tr>
    </c:forEach>

    <c:forEach items="${requestScope.stus}" var="stu" varStatus="status">
        <tr>
            <td>${stu.id}</td>
            <td>${stu.username}</td>
            <td>${stu.password}</td>
            <td>${stu.age}</td>
            <td>${stu.phone}</td>
            <td>${status.index}</td>
        </tr>
    </c:forEach>
```
    * 可以组合使用begin 和items， 其他 
    * step 步长
    * varStatus实现了LoopTagStatus接口规范，里面有获取当前遍历对象的状态的方法，如索引index,当前对象current，begin等

##  2021.6.22 上
### 1. 文件的上传和下载

#### 1.1 文件上传
客户端浏览器上传数据， 服务器解析，将数据写入磁盘。
```html
<form action="/08_jsp/uploadServlet" method="post" enctype="multipart/form-data">
</form>
```
    * ServletFileUpload类 用来解析上传的数据
    
``` java
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("test");
//        //因为是表单数据多段拼接，以流的方式存储的，因此直接getParameter得不到数据
//        ServletInputStream is = request.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while ( (len=is.read(bytes))!=-1 ){
//            bos.write(bytes,0,len);
//        }
//        System.out.println(bos.toString());

        //使用第三方jar包，解析
        //判断是不是多段格式的，
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        //1。 是多段格式才处理
        if(ServletFileUpload.isMultipartContent(request)){
            List<FileItem> list=null;
            //2。 获取上传到的数据
            try {
                 list = fileUpload.parseRequest(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //3。 遍历上传到的每项
            for(FileItem item: list){
                //4。 判断是否是普通的表单项，否则是上传的文件类型
                if(item.isFormField()){
                    System.out.println(item.getFieldName());
                    System.out.println(item.getString("utf-8"));
                }else {
                    System.out.println(item.getFieldName());
                    System.out.println(item.getName());
                    try {
                        item.write(new File("/Users/lichunyang/Documents/1.jpg"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
```    

#### 1.2 文件下载
服务器读取要下载的数据，然后通过响应流给客户端浏览器，

通过设置响应头告诉浏览器数据类型，和要下载用，不是显示出来的，指定下载到客户端浏览器的filename。
    `response.setContentType(mimeType);`
    `response.setHeader("Content-Disposition", "attachment;filename="+downloadFileName);`
在浏览器端下载的文件名filename如果要为中文则要进行编码，
  `response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("豪车.jpg", "utf-8"));`

```java

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取要下载的文件名
        String downloadFileName = "1.jpg";
        //2。 读取要下载的文件内容
        ServletContext servletContext = getServletContext();
        InputStream is = servletContext.getResourceAsStream("/" + downloadFileName);
        //4.  通过响应头告诉客户端数据类型
        String mimeType = servletContext.getMimeType("/" + downloadFileName);
        response.setContentType(mimeType);
        System.out.println(mimeType);
        response.setHeader("Content-Disposition", "attachment;filename="+downloadFileName);
        //3.  传数据到客户端
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);

        //5.  通过响应头告诉客户端（浏览器），收到的数据是下载用，否则直接显示到浏览器上了
    }
```
在浏览器端下载的文件名如果要为中文则要进行编码，
  `response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("豪车.jpg", "utf-8"));`
  
    响应头如下：
    HTTP/1.1 200
    Content-Disposition: attachment;filename=%E8%B1%AA%E8%BD%A6.jpg
    Content-Type: image/jpeg
    Transfer-Encoding: chunked
    Date: Tue, 22 Jun 2021 03:43:58 GMT
    Keep-Alive: timeout=20
    Connection: keep-alive
    
#####  火狐浏览器要使用Base64编码， 别的都用url编码，
    可通过getHeader("User-Agent") 获取浏览器引擎，弄个判断就行了   
  
##  2021.6.22 下

### 2. 书城demo登陆和注册优化

#### 2.1 静态html界面中公共部分抽取出来，用jsp静态包含 
base地址，也要动态获取，要获得服务器的，不能写成localhost的，否则客户端访问的时候就要从他本地找static下的样式了
```jsp
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    
    <%
       String basePath =  request.getScheme() + "://"+request.getServerName()+":"
                        +request.getServerPort()+request.getContextPath()+"/";
    
    //   request.setAttribute("basePath", basePath);
    %>
    <%=basePath%>
    <%--${requestScope.basePath}--%>
    <base href=<%=basePath%>>
    <%--<base href=${requestScope.basePath}>--%>
```
<% xx %>中的变量只能在<%= xx %>中使用，要在${}中用的话需要放在域中，

#### 2.2 用jsp代码替换之前的js代码，关于用户名密码不合法提示，且回显内容

#### 2.3 合并登陆和注册servlet程序，
* 添加隐藏域， 

* 通过反射调用login和regist程序，这样以后再增加新方法，不用再改调用的语句，直接将新方法加入进去就行。通过action，获取方法字符串，通过反射得到对应的方法，  
    
* 别的模块，如图书模块也是如此，因此抽象出父类，抽取公共方法，让UserServlet等继承即可。

* BeanUtils工具类， 将注册时获取到的参数封装，


##  2021.6.23

### 1. MVC概念

### 2. 表单重复提交Bug，
``` java
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamsToBean(request.getParameterMap(), new Book());
        System.out.println("检测是否将请求参数注入bean\n"+book);
        bsi.addBook(book);
        //用请求转发的话会出Bug，F5刷新浏览器， 浏览器会自动提交记录的最后一次请求，
        //浏览器会记录最后一次请求的全部信息
//        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
        //使用重定向的话，记录的最后一次请求是action=list， 而转发记录的是action=add， 转发浏览器地址不变，还是add。
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }
```
### 3. 图书模块中的删除，修改
修改时，book_edit.jsp中的表单，隐藏域提交和添加时提交的操作action="add",action="update"冲突了。

动态修改隐藏域
```html
<input type="hidden" name="action" value="add">
```
*   请求时，method=add带参数过到book_edit.jsp中， `${param.emthod}`获取。

*   也可以，`${empty param.update_bookName==false?"update":"add"}` 两个都是使用`${param}`获取请求参数来解决的



### 4.分页处理
属于查询中的，分页展示。

js中页可以跳转页面，
```javascript
    $("#searchPageButton").click(function () {
        var pageValue = $("#pn_input").val();
        location.href="manager/bookServlet?action=page&pageNo="+pageValue;
    });
```

##  2021.6.24
前台和后台分页合并，前台查询`client/index.jsp`。

### 1. Cookie
服务器可以将客户端浏览器的请求参数保存成Cookie（键值对），发送给客户端浏览器，

这样下次浏览器在访问服务器的时候，由于在请求里会带着Cookies，因此服务器可以使用这些Cookies做一些事情，如用户名密码自动填充。
## 2021.6.25

### 2. Session

![session和cookie](/images/session.png)


### 3.书城Demo，登陆后的状态保持和注销登陆
显示欢迎xxx，用到了cookie，
 
登陆成功后，应该将信息保存到session中，使得整个会话都可以使用这些信息。 
因为会点到别的请求中去，只保存到request域中不行

### 4.表单重复提交的三种常见情况
*   使用请求转发的话， 刷新浏览器会自动提交记录的最后一次请求，
*   网络延迟，收不到服务器响应，然后重复提交。
*   提交后，回退浏览器再提交

#### 使用验证码解决
 
### 5. 购物车板块
基本的添加，修改数量，清空和删除完成。

还有点小问题，翻到别的页加入购物车后，后回到第一页了，涉及到添加完购物车之后的请求分发问题。 还有添加后库存-1，销量+1，






