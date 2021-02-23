<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  var d_selbox = new Array('월세', '전세', '매매');

  var s_selbox = new Array();
  s_selbox[0] = new Array(' ~ 30', ' ~ 50',  ' ~ 70', ' ~ 90', '91 ~ ');
  s_selbox[1] = new Array(' ~ 5000', ' ~ 10000', ' ~ 15000', '15001 ~ ');
  s_selbox[2] = new Array(' ~ 10000', ' ~ 20000', ' ~ 30000', ' ~ 40000', ' ~ 50000', '50001 ~ ');
  s_selbox[3] = new Array(' ~ 50000', ' ~ 100000', ' ~ 150000', '150001 ~ ');

  function init(f) {
	var d_sel = f.dealType;
	var f_sel = f.costRange;
	var s_sel = f.depositRange;

	d_sel.options[0] = new Option("거래 종류 선택", "");
	f_sel.options[0] = new Option("가격 범위 선택", "");
	s_sel.options[0] = new Option("보증금 범위 선택", "");
	
	for(var i = 0; i < d_selbox.length; i++) {
		d_sel.options[i + 1] = new Option(d_selbox[i], d_selbox[i]);
	}
  }

  function itemChange(f) {
	var d_sel = f.dealType;
	var f_sel = f.costRange;
	var s_sel = f.depositRange;
	
	var sel = d_sel.selectedIndex;
	for(var i = f_sel.length; i >= 0; i--) {
		f_sel.options[i] = null;
	}
	for(var i = s_sel.length; i >= 0; i--) {
		s_sel.options[i] = null;
	}

	f_sel.options[0] = new Option("가격 범위 선택", "");
	s_sel.options[0] = new Option("보증금 범위 선택", "");

	if(sel == 1) {
		for(var i = 0; i < s_selbox[sel - 1].length; i++) 
			f_sel.options[i + 1] = new Option(s_selbox[sel - 1][i], s_selbox[sel - 1][i]);
		for(var i = 0; i < s_selbox[sel].length; i++)
			s_sel.options[i + 1] = new Option(s_selbox[sel][i], s_selbox[sel][i]);
	}
	else if(sel == 2) {
		for(var i = 0; i < s_selbox[sel].length; i++) 
			f_sel.options[i + 1] = new Option(s_selbox[sel][i], s_selbox[sel][i]);
	}
	else if(sel == 3) {
		for(var i = 0; i < s_selbox[sel].length; i++) 
			f_sel.options[i + 1] = new Option(s_selbox[sel][i], s_selbox[sel][i]);
	}
  }
  
  function search() {
	form.submit();
  }
</script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>주소로 검색된 물건 정보</title>
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
	
	<body onload = "init(this.form);">
	
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
	
	 <div align = "center">
	  <form name = "form" method = "POST" action = "<c:url value='/item/list' />">
			<input type = "text" name = "address" value = "${address}" />
			<input type = "hidden" name = "condition" value = "true" />
			<input type = "button" value = "검색" onClick = "search()">
			
			<br><br>
			<input type = "hidden" name = "updateFailed" value = "${updateFailed}" />
			<input type = "hidden" name = "deleteFailed" value = "${deleteFailed}" />

	  	<select id = "dealType" name = "dealType" onchange = "itemChange(this.form);"></select>
		<select name = "roomType">
			<option value = "" selected>물건 종류 선택</option>
			<option value = "아파트">아파트</option>
			<option value = "오피스텔">오피스텔</option>
			<option value = "원룸">원룸</option>
		</select>
		<select id = "costRange" name = "costRange"></select>
		<select id = "depositRange" name = "depositRange"></select>
	  </form>
		
	  <hr>
	  
	  <table border="1" bordercolor="#1a8cff" style="background-color: #1a8cff">
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
		    <a href="<c:url value='/item/view'><c:param name='item_no' value='${item.item_no}'/>
		    	<c:param name='address' value='${address}'/></c:url>">
		       ${item.item_addr}</a></td>
		   <td width="70" bgcolor="ffffff">${item.item_deal_type}</td>
		   <td width="70" bgcolor="ffffff">${item.item_type}</td>
		   <td width="40" bgcolor="ffffff">${item.item_cost}</td>
		   <td width="40" bgcolor="ffffff">${item.item_deposit}</td>
		   <td width="40" bgcolor="ffffff">${item.item_hits}</td>
		  </tr>
		</c:forEach>
	  </table>
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