<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品信息</title>

<!-- 引入js 注意：如果spring mvc的拦截规则设置为 / ，则需要在spring mvc的配置文件中设置
mvc:resources mapping="/resources/**" location="/resources/" 来过滤对静态资源的拦截    或百度参考其他方法实现js引入  -->
<!-- js引入方式一,通过jstl表达式的c标签  （注意需要在spring mvc配置文件中设置mvc:resources mapping="/resources/**" location="/resources/" 来过滤对静态资源的拦截） -->
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.4.4.min.js"/>'></script>

<!-- js引入方式二  普通方式实现 （注意需要在spring mvc配置文件中设置mvc:resources mapping="/resources/**" location="/resources/" 来过滤对静态资源的拦截） -->
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.4.4.min.js"></script> --%>
	
<!-- 页面初始化加载的时候，执行js函数的写法 -->
<script type="text/javascript">
$(function() {
	//alert(1);
	//json格式的字符串
	var param ='{"id": 1,"name": "测试商品",	"price": 99.9,	"detail": "测试商品描述",	"pic": "123456.jpg"	}';
	$.ajax({
		url : "${pageContext.request.contextPath }/json/json" ,
		data : param ,
		contentType : "application/json;charset=UTF-8" ,//设置发送的数据格式
		type : "post",
		dataType : "json" , //回调数据格式为json
		success : function(data) {
			//回调函数部分
			alert(data.id +" " + data.name +" " + data.price +" " + data.detail +" " + data.pic +" " + data.createtime);
		}
	});
});
</script>
</head>
<body> 
	<!-- 上传图片是需要指定属性 enctype="multipart/form-data" -->
	<!-- <form id="itemForm" action="" method="post" enctype="multipart/form-data"> -->
	<form id="itemForm"	action="${pageContext.request.contextPath }/json/update" method="post">
		<input type="hidden" name="id" value="${item.id }" /> 修改商品信息11111111111：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" value="${item.name }" /></td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="price" value="${item.price }" /></td>
			</tr>
			
			<tr>
				<td>商品生产日期</td>
				<td><input type="text" name="createtime"
					value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /></td>
			</tr>
			<%-- 
			<tr>
				<td>商品图片</td>
				<td>
					<c:if test="${item.pic !=null}">
						<img src="/pic/${item.pic}" width=100 height=100/>
						<br/>
					</c:if>
					<input type="file"  name="pictureFile"/> 
				</td>
			</tr>
			 --%>
			<tr>
				<td>商品简介</td>
				<td><textarea rows="3" cols="30" name="detail">${item.detail }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" />
				</td>
			</tr>
		</table>

	</form>
</body>

</html>