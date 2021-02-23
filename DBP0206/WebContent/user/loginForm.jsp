<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, model.*, controller.*"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>DataBase Programming 0206</title>
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
	<link rel="stylesheet" href="<c:url value='/css/animate.css' />">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="<c:url value='/css/icomoon.css' />">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="<c:url value='/css/bootstrap.css' />">
	<!-- Theme style  -->
	<link rel="stylesheet" href="<c:url value='/css/style.css '/>">

	<!-- Modernizr JS -->
	<script src="<c:url value='/js/modernizr-2.6.2.min.js '/>"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
<script>
function login() {
	if (form.ID.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.ID.focus();
		return false;
	} 
	if (form.PW.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.PW.focus();
		return false;
	}		
	form.submit();
}

function userCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<body>
<div class="fh5co-loader"></div>
	
	<div id="page">
	<nav class="fh5co-nav" role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-xs-2">
					<div id="fh5co-logo"><a href="<c:url value='/'></c:url>">덕방이</a></div>
				</div>
			</div>
			
		</div>
	</nav>

	<header id="fh5co-header" class="fh5co-cover" role="banner" style="background-image:url(<c:url value='/images/img_bg_2.jpg '/>);">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<div class="display-t">
						<div class="display-tc animate-box" data-animate-effect="fadeIn">
							<h1>로그인</h1>
							<div class="row">
								<form class="form-inline" id="fh5co-header-subscribe" name="form" method="POST" action="<c:url value='/user/login' />">
									<div class="col-md-8 col-md-offset-2" >
										<div class="form-group">
											<select name="userType">
											<option value="1">개인</option>
											<option value="2">중개사</option>
											</select>	
											<br>			
											아 이 디
											<input type= "text" name="ID" />
											<br>
											비밀번호
											<input type="password" name="PW" />
											<br>
											<input type="button" onClick = "login()" value="로그인" class="btn btn-default">
											<input type="button" value="회원가입" onClick="userCreate('<c:url value='/user/register/form' />')" />
											<c:if test="${loginFailed}">
	  	 									<br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	   										</c:if>
										</div>
									</div>
									</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
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