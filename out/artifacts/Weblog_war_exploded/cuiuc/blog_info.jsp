<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${pageTitle }</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
    <link href="${pageContext.request.contextPath}/favicon.ico" rel="SHORTCUT ICON">
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>

    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?aa5c701f4f646931bf78b6f40b234ef5";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

    <style type="text/css">
        body {
            padding-top: 10px;
            padding-bottom: 40px;
            background: #f5f6f7;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="/foreground/common/head.jsp"/>

    <jsp:include page="/foreground/common/menu.jsp"/>

    <div class="row">
        <div class="col-md-9">
            <%--所有博文信息展示位置--%>
                <jsp:include page="${pageContext.request.contextPath }/foreground/blog/view.jsp"></jsp:include>
        </div>

        <div class="col-md-3">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>
                    博主信息
                </div>
                <div class="user_image">
                    <img src="${pageContext.request.contextPath}/static/userImages/11.jpg"/>
                </div>
                <div class="nickName">Ha-Ha</div>
                <div class="userSign">综合应用</div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
                    按博文类别
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="blogTypeCount" items="${blogTypes }">
                            <li><span><a href="${pageContext.request.contextPath}/admin/blogTypeServlet?way=getBlogByType&typeId=${blogTypeCount.id }">${blogTypeCount.typeName } (${blogTypeCount.blogCount })</a></span></li>
                        </c:forEach>
                        <%--<li><span><a href="">java基础</a></span></li>
                        <li><span><a href="">spring Boot</a></span></li>
                        <li><span><a href="">前后端分离</a></span></li>--%>
                    </ul>
                </div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
                    按博文日期
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="blogCount" items="${blogDateCount }">
                            <li><span><a href="${pageContext.request.contextPath}/blogServlet?way=getBlogByDate&date=${blogCount.releaseDate }">${blogCount.releaseDate } (${blogCount.blogCount })</a></span></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/link_icon.png"/>
                    友情链接
                </div>
                <div class="datas">
                    <ul>
                        <li><span><a href="https://www.hiyd.com/" target="_blank">Hi运动</a></span></li>
                        <c:forEach var="link" items="${linkList }">
                            <li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

        </div>


    </div>


</div>
</body>
</html>