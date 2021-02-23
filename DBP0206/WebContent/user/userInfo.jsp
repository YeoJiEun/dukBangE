<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<script>
function changeInfo2() {
	if (form1.cupassword.value == "") {
		alert("비밀번호를 입력하십시오.");
		form1.cupassword.focus();
		return false;
	}
	if (form1.password.value != form1.repassword2.value) {
		alert("확인 비밀번호가 일치하지 않습니다.");
		form1.name.focus();
		return false;
	}
	if (form1.cupassword.value != form1.cuPWD.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form1.name.focus();
		return false;
	}
	
	var phoneExp = /^\d{2,3}\d{3,4}\d{4}$/;
	if(phoneExp.test(form1.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form1.phone.focus();
		return false;
	}
	if (form1.addr.value == "") {
		alert("주소를 입력하십시오.");
		form1.addr.focus();
		return false;
	}
	form1.submit();
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>내 정보</title>
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
<div id="doc" class="yui-t1">
  <div id="bd">
    <div id="yui-main">
      <div class="yui-b">
        <div class="content"><div class="border-box02">
			<form name="form1" method="POST" action="<c:url value='/user/userInfo/update' />">
							<input type="hidden" name="userType" value="${sessionScope.type}">
							<input type="hidden" name="email" value="${sessionScope.user}">
							<input type="hidden" name="name" value="${userInfo.NAME}">
							<input type="hidden" name="u_no" value="${sessionScope.userNo}">
							<input type="hidden" name="a_no" value="${sessionScope.userNo}">
							<input type="hidden" name="c_no" value="${sessionScope.userNo}">
							<input type="hidden" name="cuPWD" value="${userInfo.PWD}">
						
				<div class="bg-contents">
					<div>
					
						<table cellspacing="0" class="request"  style="margin-left:40px; width:90%; text-align:left;"  >
							<tr> <td colspan="6"><p></td></tr>
							<tr> <td colspan="6"><h3 id="header_j">내 정보</h3></td></tr>
							<tr>
								<th class="tableHeader" style="text-align:left;" width=150>성명</th>
								<td class="tableContent" style="text-align:left;" >${userInfo.NAME}</td>

							</tr>
							<tr>
								<th class="tableHeader" style="text-align:left;">아이디</th>
								<td class="tableContent" style="text-align:left;">${sessionScope.user}</td>

							</tr>
							<c:if test="${sessionScope.type==2}">
									<tr>
									<th class="tableHeader" style="text-align:left;">중개사 명</th>
									<td class="tableContent" style="text-align:left;">${userInfo.OFFICE}</td>
									</tr>    
											
							</c:if>
							<tr>
								<th class="tableHeader" style="text-align:left;">주소</th>
								<td class="tableContent" style="text-align:left;"><input type="text" width=200 value="${userInfo.ADDR}" name="addr"></td>

							</tr>
							<tr>
								<th class="tableHeader" style="text-align:left;">연락처</th>
								<td class="tableContent" style="text-align:left;"><input type="text" value="${userInfo.PHONE}" name="phone"></td>
							</tr>     
							
							<tr>
								<th class="tableHeader" style="text-align:left;">현재 비밀번호</th>
								<td class="tableContent" style="text-align:left;"><input type="password" name="cupassword" /></td>
							</tr>
							<tr>
								<th class="tableHeader" style="text-align:left;">변경 비밀번호</th>
								<td class="tableContent" style="text-align:left;"><input type="password" name="password" /></td>
							</tr>
							<tr>
								<th class="tableHeader" style="text-align:left;">변경 비밀번호 확인</th>
								<td class="tableContent" style="text-align:left;"><input type="password" name="repassword2" /></td>
							</tr>  
							
							<tr> <td colspan="2" style="text-align:right;"><input type="button" value="회원정보 변경" title="회원정보 변경"  onclick="changeInfo2()"/></td></tr>
							
							
							</table>
							
							
							</div>
							
			
					
					
					</div>
					</form>
				</div>
			</div>
      </div>
    </div>
    <div class="yui-b">
      <div id="category">카 테 고 리
      	<div ><p></div> 
			<div id="cate-input" style="border-top:white 1px solid"><a href="<c:url value="/user/userInfo" ><c:param name='userType' value='${sessionScope.type}' />		
											<c:param name='u_no' value='${sessionScope.userNo}' /><c:param name='ID' value='${sessionScope.user}' /></c:url>">내 정보 </a></div>
		<div id="cate-input"><a href="<c:url value='/user/counselList'><c:param name='c_no' value='${sessionScope.userNo}' /></c:url>"> 상담내역</a> </div>

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