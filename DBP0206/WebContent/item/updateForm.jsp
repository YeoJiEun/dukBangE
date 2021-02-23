<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>물건 수정 관리</title>
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
function itemModify() {
	if (form.item_addr.value == "") {
		alert("주소를 입력하십시오.");
		form.item_addr.focus();
		return false;
	}
	if (form.item_deal_type.value == "") {
		alert("거래 종류를 입력하십시오.");
		form.deal_type.focus();
		return false;
	}
	if (form.item_type.value == "") {
		alert("물건 종류를 입력하십시오.");
		form.item_type.focus();
		return false;
	}
	if (form.item_cost.value == "") {
		alert("가격을 입력하십시오.");
		form.item_cost.focus();
		return false;
	}
	if (form.item_deposit.value == "") {
		alert("보증금을 입력하십시오.");
		form.item_deposit.focus();
		return false;
	}
	if (form.item_layer.value == "") {
		alert("건물 층수를 입력하십시오.");
		form.item_layer.focus();
		return false;
	}
	if (form.item_layer.value == "") {
		alert("물건 크기를 입력하십시오.");
		form.item_size.focus();
		return false;
	}
	if (form.item_manage_cost.value == "") {
		alert("관리비를 입력하십시오.");
		form.item_manage_cost.focus();
		return false;
	}
	if (form.item_park_tf.value == "") {
		alert("주차 가능 여부를 입력하십시오.");
		form.item_park_tf.focus();
		return false;
	}
	if (form.item_ele_tf.value == "") {
		alert("엘레베이터 유무를 입력하십시오.");
		form.item_ele_tf.focus();
		return false;
	}
	if (form.item_pet_tf.value == "") {
		alert("반려동물 가능 여부를 입력하십시오.");
		form.item_pet_tf.focus();
		return false;
	}
	if (form.item_avail_date.value == "") {
		alert("입주가능일을 입력하십시오.");
		form.item_avail_date.focus();
		return false;
	}
	if (form.item_close_tf.value == "") {
		alert("거래 완료 여부를 입력하십시오.");
		form.item_close_tf.focus();
		return false;
	}

	form.submit();
}

function itemList(targetUri) {
	form.action = targetUri;
	form.submit();
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

<!-- Update Form  -->
<form name="form" method="POST" action="<c:url value='/item/update' />">
  <input type="hidden" name="item_no" value="${item.item_no}"/>	  
  <input type="hidden" name="a_no" value="${item.a_no}"/>
  <input type="hidden" name="item_hits" value="${item.item_hits}"/>
  <input type="hidden" name="address" value="${address}"/>
  <table style="width: 50%" border="0" align="center">
	<tr>
	  <td width="20"></td>
	  <td>
	    <table align="center">
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>물건 관리 - 수정</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  
	    <table border="1" bordercolor="#1a8cff" style="background-color: #1a8cff">
	      <tr><td colspan="2" align = "left" bgcolor="#99ccff">가격 단위 : 10,000원</td></tr>
	  	  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">주소</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 350" name="item_addr" value="${item.item_addr}">
			</td>
		  </tr>
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">거래 종류</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_deal_type" value="${item.item_deal_type}">
			</td>
		  </tr>
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">물건 종류</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_type" value="${item.item_type}"> 
			</td>
		  </tr>		  
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">가격</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_cost" value="${item.item_cost}">
			</td>
		  </tr>		  
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">보증금</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_deposit" value="${item.item_deposit}">
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">건물 층수</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_layer" value="${item.item_layer}">	
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">물건 크기</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_size" value="${item.item_size}">
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">관리비</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_manage_cost" value="${item.item_manage_cost}">
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">주차 가능 여부</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<select id="itemParkSelect" name="item_park_tf" style="width: 200">	
					<option value = "TRUE" <c:if test="${item.item_park_tf eq 'TRUE'}">selected="selected"</c:if>>TRUE</option>
					<option value = "FALSE" <c:if test="${item.item_park_tf eq 'FALSE'}">selected="selected"</c:if>>FALSE</option>
				</select>
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">엘레베이터 유무</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<select id="itemEleSelect" name="item_ele_tf" style="width: 200">	
					<option value = "TRUE" <c:if test="${item.item_ele_tf eq 'TRUE'}">selected="selected"</c:if>>TRUE</option>
					<option value = "FALSE" <c:if test="${item.item_ele_tf eq 'FALSE'}">selected="selected"</c:if>>FALSE</option>
				</select>
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">반려동물 가능 여부</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<select id="itemPetSelect" name="item_pet_tf" style="width: 200">	
					<option value = "TRUE" <c:if test="${item.item_pet_tf eq 'TRUE'}">selected="selected"</c:if>>TRUE</option>
					<option value = "FALSE" <c:if test="${item.item_pet_tf eq 'FALSE'}">selected="selected"</c:if>>FALSE</option>
				</select>
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">입주가능일</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 200" name="item_avail_date" value="${item.item_avail_date}">
			</td>
		  </tr>	
		  <tr>
			<td width="210" align="center" bgcolor="#99ccff" height="22">거래 완료 여부</td>
			<td width="400" bgcolor="ffffff" style="padding-left: 10">
				<select id="itemCloseSelect" name="item_close_tf" style="width: 200">	
					<option value = "TRUE" <c:if test="${item.item_close_tf eq 'TRUE'}">selected="selected"</c:if>>TRUE</option>
					<option value = "FALSE" <c:if test="${item.item_close_tf eq 'FALSE'}">selected="selected"</c:if>>FALSE</option>
				</select>
			</td>
		  </tr>		
	    </table>
	    <br>	  
	    <table style="width:100%">
		  <tr>
			<td align="center">
			<input type="button" value="수정" onClick="itemModify()"/> &nbsp; &nbsp; &nbsp;
			<input type="button" value="목록" onClick="itemList('<c:url value='/item/usersItemView'><c:param name='where' value='usersItem'/>
             <c:param name='item_no' value='${item.item_no}'/></c:url>') "/>
			</td>
		  </tr>
		 </table>
		</td>
	</tr>
  </table>  
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