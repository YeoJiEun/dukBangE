<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script>
function back(){
	
	form.submit();
}

</script>
	<title>추천 매물</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="DataBase Programming 0206" />
		<meta name="keywords" content="DataBase Programming 0206" />
		<meta name="author" content="gettemplates.co" />
		
		<meta property="og:title" content=""/>
		<meta property="og:image" content=""/>
		<meta property="og:url" content=""/>
		<meta property="og:site_name" content=""/>
		<meta property="og:description" content=""/>
		<meta name="twitter:title" content="" />
		<meta name="twitter:image" content="" />
		<meta name="twitter:url" content="" />
		<meta name="twitter:card" content="" />
		
 		<!-- Animate.css -->
		<link rel="stylesheet" href="<c:url value='/css/animate.css '/>">
		<!-- Icomoon Icon Fonts-->
		<link rel="stylesheet" href="<c:url value='/css/icomoon.css '/>">
		<!-- Bootstrap  -->
		<link rel="stylesheet" href="<c:url value='/css/bootstrap.css '/>">
		<!-- Theme style  -->
		<link rel="stylesheet" href="<c:url value='/css/style.css '/>">
		
		<script src="<c:url value='/js/modernizr-2.6.2.min.js '/>"></script>
		<script src="<c:url value='/js/respond.min.js '/>"></script>
		<link rel="stylesheet" type="text/css" href="../css/infostyles.css" >
</head>
<body>
<div id="doc" class="yui-t1">
 <nav class="fh5co-nav" role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-xs-2">
					<div id="fh5co-logo"><a href="<c:url value='/user/main'></c:url>"><font color="#1a8cff">덕방이</font></a></div>
				</div>
				<div class="col-xs-10 text-right menu-1">
					<ul>
						<li class="active"><a href="<c:url value='/'></c:url>"><font color="#1a8cff">Home</font></a></li>
						<c:choose>
							<c:when test="${sessionScope.user==null}">
									<li class="btn-cta"><a href="<c:url value='/user/login'></c:url>"><span>Login</span></a></li>
							</c:when>
							<c:when test="${sessionScope.type==1}">
									<a href="<c:url value='/user/wish'><c:param name='c_no' value='${sessionScope.userNo}'/></c:url>"><font color="#1a8cff" >찜목록</font></a>
									<li class="has-dropdown">
									<a href="#"><font color="#99ccff">${sessionScope.user} 정보</font></a>
									<ul class="dropdown">
										<li><a href="<c:url value='/user/userInfo'></c:url>">회원 정보</a></li>
										<li><a href="<c:url value='/user/counselList'></c:url>">상담 내역</a></li>					
									</ul>
								</li>
								<li class="btn-cta"><a href="<c:url value='/user/logout'></c:url>"><span>Logout</span></a></li>
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/item/userItem'></c:url>"><font color="#1a8cff">내 매물</font></a>
								<li class="has-dropdown">
									<a href="<c:url value='/item/userItem'></c:url>"><font color="#99ccff">${sessionScope.user} 정보</font></a>
									<ul class="dropdown">
										<li><a href="<c:url value='/user/userInfo'></c:url>">회원 정보</a></li>
										<li><a href="<c:url value='/user/counselList'></c:url>">상담 내역</a></li>
										<li><a href="<c:url value='/point/pointList'></c:url>">포인트 내역</a></li>						
									</ul>
								</li>
								<li class="btn-cta"><a href="<c:url value='/user/logout'></c:url>"><span>Logout</span></a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</nav>

	<br><br><br><br>
  <div id="bd">
    <div id="yui-main">
      <div class="yui-b">
        <div class="content">
  			<div>
			<div >
			<form name="form" method="GET" action="<c:url value='/user/recommand' />">
			<table cellspacing="0" style="margin-left:40px; width:90%">
			<tr> <td colspan="6"><p></td></tr>
			<tr> <td colspan="6"><h3 id="header_j">추천 매물</h3></td></tr>
			<tr>
				<th class="tableHeader" >매물번호</th>
				<th class="tableHeader" width="250">주소</th>
				<th class="tableHeader">거래 종류</th>
				<th class="tableHeader">면적</th>
				<th class="tableHeader">가격</th>
				<th class="tableHeader">보증금</th>
			</tr>
			<c:forEach var="r" items="${recommand}">  	
			<tr>
				<td class="tableContent">${r.item_no}</td>
				<td class="tableContent"><a href="<c:url value="/item/view" ><c:param name='item_no' value='${r.item_no}' /></c:url>">${r.item_addr}</a></td>
				<td class="tableContent">${r.item_deal_type}</td>	
				<td class="tableContent">${r.item_size}</td>
				<td class="tableContent">${r.item_cost}</td	>
				<td class="tableContent">${r.item_deposit}</td>
			</tr>
			</c:forEach>	
			<tr> <td colspan="6" style="text-align:center;">
			<a href="<c:url value='/user/recommand' ></c:url>" style="color:#245e9a; font:bold; " >[새로고침]</a>
			<a href="<c:url value='/user/wish' ></c:url>" style="color:#245e9a; font:bold; " >[뒤로가기]</a>
					</td>
			</tr>
			</table>
			</form>
				</div>
			</div>
      </div>
    </div>
    </div>
    <div class="yui-b">
      <div id="category">카 테 고 리
      	<div ><p></div> 
		<div id="cate-input" style="border-top:white 1px solid"><a href="<c:url value="/user/userInfo" ><c:param name='userType' value='${sessionScope.type}' />		
											<c:param name='u_no' value='${sessionScope.userNo}' /></c:url>">내 정보 </a></div>
		<div id="cate-input"><a href="<c:url value='/user/counselList' ><c:param name='c_no' value='${sessionScope.userNo }' /></c:url>"> 상담내역</a> </div>
		<div><p> </div>
	  </div>
    </div>
  </div>

</div>
	<!-- jQuery -->
	<script src="<c:url value='/js/jquery.min.js '/>"></script>
	<!-- jQuery Easing -->
	<script src="<c:url value='/js/jquery.easing.1.3.js '/>"></script>
	<!-- Bootstrap -->
	<script src="<c:url value='/js/bootstrap.min.js '/>"></script>
	<!-- Waypoints -->
	<script src="<c:url value='/js/jquery.waypoints.min.js '/>"></script>
	<!-- Main -->
	<script src="<c:url value='/js/main.js '/>"></script>
</body>
</html>