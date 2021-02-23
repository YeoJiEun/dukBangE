<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, model.*, controller.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
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
	<script src="<c:url value='js/respond.min.js '/>"></script>
	<![endif]-->
	<script>
	function search() {
		if (form.address.value == "") {
			alert("검색할 주소를 입력하십시오.");
			form.address.focus();
			return false;
		} 		
		form.submit();
	}
	</script>
	</head>
	<body>
		
	<div class="fh5co-loader"></div>
	
	<div id="page">
	<nav class="fh5co-nav" role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-xs-2">
					<div id="fh5co-logo"><a href="<c:url value='/user/main'></c:url>">덕방이</a></div>
				</div>
				<div class="col-xs-10 text-right menu-1">
					<ul>
						<li class="active"><a href="<c:url value='/'></c:url>">Home</a></li>
						<c:choose>
							<c:when test="${sessionScope.user==null}">
									<li class="btn-cta"><a href="<c:url value='/user/login'></c:url>"><span>Login</span></a></li>
							</c:when>
							<c:when test="${sessionScope.type==1}">
									<a href="<c:url value='/user/wish'><c:param name='c_no' value='${sessionScope.userNo}'/></c:url>">찜목록</a>
									<li class="has-dropdown">
									<a href="#">${sessionScope.user} 정보</a>
									<ul class="dropdown">
										<li><a href="<c:url value='/user/userInfo'></c:url>">회원 정보</a></li>
										<li><a href="<c:url value='/user/counselList'></c:url>">상담 내역</a></li>					
									</ul>
								</li>
								<li class="btn-cta"><a href="<c:url value='/user/logout'></c:url>"><span>Logout</span></a></li>
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/item/userItem'></c:url>">내 매물</a>
								<li class="has-dropdown">
									<a href="#">${sessionScope.user} 정보</a>
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

	<header id="fh5co-header" class="fh5co-cover" role="banner" style="background-image:url(<c:url value='/images/img_bg_2.jpg '/>);">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<div class="display-t">
						<div class="display-tc animate-box" data-animate-effect="fadeIn">
							<h1>매물 찾기</h1>
							<div class="row">
								<form name="form" method="POST" class="form-inline" id="fh5co-header-subscribe" action="<c:url value='/item/list' />">
									<div class="col-md-8 col-md-offset-2">
										<div class="form-group">
											<input type="text" name="address" class="form-control" id="find" placeholder="지역를 입력해주세요 ex)서울시 성북구">
											<input type="button" value="검색" class="btn btn-default" onClick="search()">
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
