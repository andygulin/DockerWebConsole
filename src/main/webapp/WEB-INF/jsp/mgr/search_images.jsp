<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp"%>

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
              				</td>
              			</tr>
              		</c:forEach>
              	</tbody>
  			</table>
  		</div>
  	</div>
</div>
<script type="text/javascript">
$(function(){
	$("ul.nav.navbar-nav li").removeClass("active");
	$("#li_images").addClass("active");
});
</script>
<%@ include file="/WEB-INF/jsp/inc/footer.jsp"%>