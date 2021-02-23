<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <script>
    function insertItem(targetUri) {
      form.action = targetUri;
      form.submit();
 	}
  </script>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>중개사가 올린 물건 정보</title>
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
	</head>
	
	<body>
	
	<div class="fh5co-loader"></div>

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
	
	<table border="1" bordercolor="#1a8cff" style="background-color: #1a8cff" align = "center">
	  <tr><td colspan="7" align = "left" bgcolor="#99ccff">  가격 단위 : 10,000원</td></tr>
		<tr align = "center">
		  <td width="50" bgcolor="#99ccff">번호</td>
		  <td width="300" bgcolor="#99ccff">주소</td>
		  <td width="90" bgcolor="#99ccff">거래 종류</td>
		  <td width="90" bgcolor="#99ccff">매물 종류</td>
		  <td width="90" bgcolor="#99ccff">가격</td>
		  <td width="90" bgcolor="#99ccff">보증금</td>
		  <td width="90" bgcolor="#99ccff">조회수</td>
		</tr>
		
		<c:forEach var="item" items="${itemList}">
		  <tr align = "center">
		   <td width="30" bgcolor="ffffff">${item.item_no}</td>
		   <td width="350" bgcolor="ffffff">
		    <a href="<c:url value='/item/usersItemView'><c:param name='where' value='usersItem'/>
		    	<c:param name='item_no' value='${item.item_no}'/><c:param name='hits' value='${item.item_hits}'/></c:url>">
		       ${item.item_addr}</a></td>
		   <td width="70" bgcolor="ffffff">${item.item_deal_type}</td>
		   <td width="70" bgcolor="ffffff">${item.item_type}</td>
		   <td width="40" bgcolor="ffffff">${item.item_cost}</td>
		   <td width="40" bgcolor="ffffff">${item.item_deposit}</td>
		   <td width="40" bgcolor="ffffff">${item.item_hits}</td>
		  </tr>
		</c:forEach>
	  </table>
	  
	  <br>
	  
	 <div align = "center">
	  <form name = "form" method = "post" >
         <input type="hidden" name="a" value="${address}"/>
        <input type="button" value="물건 추가" onClick="insertItem('<c:url value='/item/insert/form'/>')"/>
     </form>
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