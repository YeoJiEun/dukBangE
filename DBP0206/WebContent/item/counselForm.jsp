<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>상담 예약 페이지</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<script>
function reservation() {
	if(!checkDate()){
		alert("날짜를 선택하세요");
		return false;
	}
	
	if (form.detail.value == "") {
		alert("상담할 내용을 입력하십시오.");
		form.detail.focus();
		return false;
	} 
		
	form.submit();
}

function checkDate() {
	num_temp = document.getElementsByName("checkDate").length; 
	for (i=0;i<num_temp ;i++) { 
		if (document.getElementsByName("checkDate")[i].checked == true) { 
			break;
			} 
	} 
	if (i == num_temp) {  
		return false;
	} 
	return true;
}

</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
  
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
	<table align="center">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>상담예약</b>&nbsp;&nbsp;</td>
		  </tr>
   </table>
<!-- counselReservation form  -->
<form name="form" method="POST" action="<c:url value='/item/counsel/form' />">
<input type="hidden" name="item" value="${item}"/>	  
<input type="hidden" name="agent" value="${agent}"/>
<div align = "center">
	<table border="1" bordercolor="#1a8cff" style="background-color: #1a8cff">
		<tr align = "center">
		  <td width="300" bgcolor="#99ccff">날짜</td>
		  <td width="50" bgcolor="#99ccff">체크</td> 
		</tr>
		
		<c:forEach var="item" items="${sList}">
		  <tr align = "center">
		   <td width="350" bgcolor="ffffff">${item.avaliable_date}</td>
		   <td width="30" bgcolor="ffffff">
		   	<input type="radio" name="checkDate" value="${item.avaliable_date}">
		   </td>
		  </tr>
		</c:forEach>
	  </table>
	  <br>
	<table border="1" bordercolor="#1a8cff" style="background-color: #1a8cff">
	<tr>
		<td bgcolor="#99ccff" height="22" colspan="2">&nbsp;&nbsp;<b><예약 정보></b>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="140" align="center" bgcolor="#99ccff" height="22">상담 내용</td>
		<td width="350" bgcolor="ffffff" style="padding-left: 10">
			<textarea name="detail" cols="40"></textarea>
		</td>
	</tr>
  </table>
	  
	  <br>
	 
	 <input type="button" value="상담예약" onClick="reservation()"/>
	  
	 </div>	
	 </form>
	 
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