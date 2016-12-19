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
                <c:forEach var="item" items="${imageVOs }">
                    <tr>
                        <td>${item.created }</td>
                        <td title="${item.id }">${fn:substring(item.id,0,20) }</td>
                        <td title="${item.parentId }">${fn:substring(item.parentId,0,20) }</td>
                        <td>${fn:join(item.repoTags,",") }</td>
                        <td>${item.size }</td>
                        <td>${item.virtualSize }</td>
                        <td>
                            <button type="button" class="btn btn-danger btn-xs" onclick="removeImage('${item.id}')">删除
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

    function removeImage(imageId) {
        if (confirm("确认删除?")) {
            $.post("${ctx}/mgr/image/remove/" + imageId, {},
                function (res) {
                    if (res.success) {
                        alert("删除成功!");
                        window.location.reload();
                    } else {
                        alert("删除失败!");
                    }
                });
        }
    }
</script>
<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %>