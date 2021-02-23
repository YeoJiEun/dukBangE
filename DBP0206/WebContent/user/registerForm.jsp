<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>회원가입</title>
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

	<!-- <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'> -->
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="<c:url value='/css/animate.css '/>">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="<c:url value='/css/icomoon.css '/>">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="<c:url value='/css/bootstrap.css '/>">
	<!-- Theme style  -->
	<link rel="stylesheet" href="<c:url value='/css/style.css '/>">

	<!-- Modernizr JS -->
	<script src="<c:url value='/js/modernizr-2.6.2.min.js '/>"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="<c:url value='/js/respond.min.js '/>"></script>
	<![endif]-->
<script>
function chooseForm(radioName) {
	  var radios = document.getElementsByName(radioName);
	  for (var i = 0, length = radios.length; i < length; i++) {
	    document.getElementById('form_' + radios[i].value).style.display = 'none';
	    if (radios[i].checked) {
	      document.getElementById('form_' + radios[i].value).style.display = 'block';
	    }
	  }
	}
	
function userCreate1() {
	if (form1.email.value == "") {
		alert("이메일을 입력하십시오.");
		form1.email.focus();
		return false;
	}
	if (form1.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form1.password.focus();
		return false;
	}
	if (form1.password.value != form1.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form1.name.focus();
		return false;
	}
	if (form1.name.value == "") {
		alert("이름을 입력하십시오.");
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

function userCreate2() {
	if (form2.email.value == "") {
		alert("이메일을 입력하십시오.");
		form2.email.focus();
		return false;
	}
	if (form2.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form2.password.focus();
		return false;
	}
	if (form2.password.value != form2.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form2.name.focus();
		return false;
	}
	if (form2.name.value == "") {
		alert("이름을 입력하십시오.");
		form2.name.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}\d{3,4}\d{4}$/;
	if(phoneExp.test(form2.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form2.phone.focus();
		return false;
	}
	if (form2.property.value == "") {
		alert("부동산 이름을 입력하십시오.");
		form2.property.focus();
		return false;
	}
	if (form2.addr.value == "") {
		alert("주소를 입력하십시오.");
		form2.addr.focus();
		return false;
	}
	form2.submit();
}
</script>
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

	<table align="center">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>회원가입</b>&nbsp;&nbsp;</td>
		  </tr>
   </table>
   
   <br>
<center>
 <input type="radio" name="userType" checked="checked" value="1" checked onclick="chooseForm(this.name)"  /> 개인
 <input type="radio" name="userType" value="2" onclick="chooseForm(this.name)" /> 중개인
</center>
<form name="form1" method="POST" action="<c:url value='/user/register' />">
<input type="hidden" name = "type" value="1"/>
  <div id="form_1">
  <table border="0" align="center">	
    <tr align="center"><td colspan="2">
   	<!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
	<br>
	</td></tr>
    <tr>
	  <td>	  
	    <table border="1" bordercolor="#1a8cff">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">EMAIL</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240;" name="email">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">비밀번호</td>
			<td width="250"  style="padding-left: 10">
				<input type="password" style="width: 240" name="password">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">비밀번호 확인</td>
			<td width="250" style="padding-left: 10">
				<input type="password" style="width: 240" name="password2">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">이름</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240" name="name" >
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">전화번호</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240" name="phone" >
			</td>
		  </tr>  
		   <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">주소</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240" name="addr" >
			</td>
		  </tr> 
	    </table>
	  </td>
    </tr>
    <tr><td colspan="2" align="center"><br>
		<input type="button" value="회원 가입" onClick="userCreate1()"> &nbsp;
	</td></tr>
  </table>  
	</div>
 </form>
 
<div id="form_2" style="display:none;">
<form name="form2" method="POST" action="<c:url value='/user/register' />">
<input type="hidden" name = "type" value="2"/>
  <table border="0" align="center">
     <tr align="center"><td colspan="2">
	    <!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
	    <br>
	 </td></tr>
	 <tr>
	   <td>   	  
	    <table border="1" bordercolor="#1a8cff">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">EMAIL</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240;" name="email">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">비밀번호</td>
			<td width="250"  style="padding-left: 10">
				<input type="password" style="width: 240" name="password">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">비밀번호 확인</td>
			<td width="250" style="padding-left: 10">
				<input type="password" style="width: 240" name="password2">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">이름</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240" name="name" >
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">전화번호</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240" name="phone" >
			</td>
		  </tr>  
		  <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">부동산 이름</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240" name="property" >
			</td>
		  </tr>
		   <tr height="40">
			<td width="150" align="center" bgcolor="#99ccff">주소</td>
			<td width="250" style="padding-left: 10">
				<input type="text" style="width: 240" name="addr" >
			</td>
		  </tr>  
	    </table>
	    </td>
	    </tr>
	    <tr><td colspan="2" align="center"><br>
			<input type="button" value="회원 가입" onClick="userCreate2()"> &nbsp;
		</td></tr>
  </table>  
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