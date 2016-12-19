<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="DOCKER_USER" value="${sessionScope.DOCKER_WEB_CONSOLE }"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Docker Web Console</title>

    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header"><a class="navbar-brand" href="${ctx }">Docker Web Console</a></div>
        <c:if test="${not empty DOCKER_USER }">
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li id="li_info"><a href="${ctx }/mgr/info">信息</a></li>
                    <li id="li_version"><a href="${ctx }/mgr/version">版本</a></li>
                    <li id="li_images"><a href="${ctx }/mgr/images">镜像</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${ctx }/logout">Logout</a></li>
                </ul>
                <form class="navbar-form navbar-right" action="${ctx }/mgr/image/search">
                    <input type="text" class="form-control" placeholder="搜索镜像..." name="term" style="width:300px;"
                           value="${param.term }">
                </form>
            </div>
        </c:if>
    </div>
</nav>