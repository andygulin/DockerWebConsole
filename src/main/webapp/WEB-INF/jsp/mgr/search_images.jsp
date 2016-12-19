<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="container-fluid" style="margin-top:80px;">
    <div class="row">
        <div class="col-md-12">
            <h3>${DOCKER_USER }</h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <c:forEach var="item" items="${headers }">
                        <th>${item }</th>
                    </c:forEach>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${items }">
                    <tr>
                        <td>${item.starCount }</td>
                        <td>${item.official }</td>
                        <td>${item.name }</td>
                        <td>${item.description }</td>
                        <td>
                            <button type="button" class="btn btn-info btn-xs" onclick="pullImage('${item.name}')">安装
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("ul.nav.navbar-nav li").removeClass("active");
        $("#li_images").addClass("active");
    });

    function pullImage(repository) {
        if (confirm("确认安装?")) {
            $.post("${ctx}/mgr/image/pull/" + repository, {},
                function (res) {
                    if (res.success) {
                        alert("安装成功!");
                    } else {
                        alert("安装失败!");
                    }
                });
        }
    }
</script>
<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %>