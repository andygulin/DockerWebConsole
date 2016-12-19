<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<div class="container-fluid" style="margin-top:100px;">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h1>Login</h1>
            <form>
                <div class="form-group">
                    <input class="form-control input-lg" type="text" placeholder="http://主机地址：端口" id="serverUrl">
                </div>
                <div class="form-group">
                    <input class="btn btn-success btn-lg" type="button" value="Connect" style="width:200px"
                           id="connect">
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#serverUrl").focus();
        $("#serverUrl").val("tcp://192.168.209.130:2375");
        $("#connect").click(function () {
            var serverUrl = $("#serverUrl").val();
            $.post("${ctx}/connect", {serverUrl: serverUrl}, function (res) {
                if (res.success) {
                    window.location.href = "${ctx}/mgr/info";
                } else {
                    alert("登录失败");
                }
            });
        });
    });
</script>
<%@ include file="/WEB-INF/jsp/inc/footer.jsp" %>