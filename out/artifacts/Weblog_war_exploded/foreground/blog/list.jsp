<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="data_list">
		<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/list_icon.png"/>
		最新博客</div>
		<div class="datas">
			<ul>
			  <c:forEach var="blog" items="${blogList}">
				<li style="margin-bottom: 30px">
					<%--${pageContext.request.contextPath} 绝对路径 相当于项目名--%>
					<span class="date"><a href="${pageContext.request.contextPath}/blogServlet?way=getBlogById&id=${blog.id}"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy年MM月dd日"/></a></span>
					<span class="title"><a href="${pageContext.request.contextPath}/blogServlet?way=getBlogById&id=${blog.id}">${blog.title }</a></span>
						<span class="summary"><a href="${pageContext.request.contextPath}/blogServlet?way=getBlogById&id=${blog.id}">摘要: ${blog.summary }...</a></span>
					<span class="info">发表于 <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/> 阅读(${blog.clickHit}) 评论(${blog.replyHit}) </span>
				</li>
				<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
			</c:forEach>
				 <%--<li style="margin-bottom: 30px">
					 <span class="date"><a href="">2020-05-09 12：12：12</a></span>
					 <span class="title"><a href="">习近平用这句话评价我国疫情防控工作成果！</a></span>
					 <span class="summary">摘要:5月8日，中共中央在中南海召开党外人士座谈会，就新冠肺炎疫情防控工作听取各民主党派中央、全国工商联和无党派人士代表的意见和建议。中共中央总书记习近平主持座谈会并发表重要讲话。...</span>
					 <span class="info">发表于 2020-05-09 12：12：12 阅读(66) 评论(88) </span>
				 </li>
				 <hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />--%>
			</ul>
		</div>
   </div>
