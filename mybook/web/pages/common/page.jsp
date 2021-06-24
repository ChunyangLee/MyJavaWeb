<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/24
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <c:choose>
        <c:when test="${requestScope.page.pageTotalNo<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotalNo}" var="i">
                <c:choose>
                    <c:when test="${i==requestScope.page.pageNo }">
                        【${requestScope.page.pageNo}】
                    </c:when>
                    <c:otherwise>
                        <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:when>
        <c:otherwise>
                <c:choose>
        <%--             当前页小于2，显示前5页即可--%>
                    <c:when test="${requestScope.page.pageNo-2<=0}">
                        <c:if test="${requestScope.page.pageNo==1}">
                            【${requestScope.page.pageNo}】
                            <a href="${requestScope.page.url}&pageNo=2">2</a>
                        </c:if>
                        <c:if test="${requestScope.page.pageNo==2}">
                            <a href="${requestScope.page.url}&pageNo=1">1</a>
                            【${requestScope.page.pageNo}】
                        </c:if>
                        <a href="${requestScope.page.url}&pageNo=3">3</a>
                        <a href="${requestScope.page.url}&pageNo=4">4</a>
                        <a href="${requestScope.page.url}&pageNo=5">5</a>
                    </c:when>
                    <c:when test="${requestScope.page.pageNo+2<= requestScope.page.pageTotalNo}">
                        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-2}">${requestScope.page.pageNo-2}</a>
                        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>
                        【${requestScope.page.pageNo}】
                        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>
                        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+2}">${requestScope.page.pageNo+2}</a>
                    </c:when>
                    <c:otherwise>
                        <c:forEach begin="0" end="4" var="i">
                            <c:choose>
                                <c:when test="${requestScope.page.pageTotalNo-4+i>0 && requestScope.page.pageTotalNo-4+i==requestScope.page.pageNo }">
                                    【${requestScope.page.pageNo}】
                                </c:when>
                                <c:when test="${requestScope.page.pageTotalNo-4+i>0 && requestScope.page.pageTotalNo-4+i!=requestScope.page.pageNo}">
                                    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotalNo-4+i}">${requestScope.page.pageTotalNo-4+i}</a>
                                </c:when>
                            </c:choose>

                        </c:forEach>
                    </c:otherwise>
                </c:choose>
        </c:otherwise>
    </c:choose>

    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotalNo}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotalNo}">末页</a>
    </c:if>

    共${requestScope.page.pageTotalNo}页，${requestScope.page.pageTotalItems}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" id="searchPageButton" value="确定">
</div>
