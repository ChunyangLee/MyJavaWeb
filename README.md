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
