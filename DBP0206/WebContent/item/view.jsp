<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>물건 상세 정보 페이지</title>
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
  function itemList(targetUri) {
	  form.action = targetUri;
	  form.submit();
  }
  
  function counselReservation(targetUri) {
	if(form.type.value == "")
		alert("상담 예약을 하려면 로그인을 먼저 해주세요!!!")  
	else if(form.type.value == "2")
		alert("개인 회원만 상담 예약을 할 수 있습니다!!!")
	else {
		form.action = targetUri;
		form.submit();
	}
  }
  
  function createWish(targetUri){
	if(form.count.value < 6){
		form.action = targetUri;
		form.submit();
	}
	else
		alert("찜 매물은 6개 까지만 등록 가능합니다. 찜 목록 삭제 후 다시시도해주세요");
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
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>물건 관리 - 상세정보 보기</b>&nbsp;&nbsp;</td>
		  </tr>
   </table>
   
  <form name="form" method="GET" action="<c:url value='/item/update' />">
  <input type="hidden" name="item_no" value="${item.item_no}"/>	  
  <input type="hidden" name="item_hits" value="${item.item_hits}"/>
  <input type="hidden" name="address" value="${address}"/>
  <input type="hidden" name="count" id="count" value="${count}"/>

  <br>	   
 	<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
 	<center>
    <c:if test="${updateFailed || deleteFailed}">
      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	</c:if> 
	</center>
  <table style="width: 50%" border="0" align="center">
   <tr>
	 <td>
	   <br>  	    
	  	<table border="1" bordercolor="#1a8cff" style="background-color: #1a8cff">
	  	  <tr>
			<td bgcolor="#99ccff" height="22" colspan="2">&nbsp;&nbsp;<b><물건 정보></b>&nbsp;&nbsp;</td>
		  </tr>
	  	  <tr><td colspan="2" align = "left" bgcolor="#99ccff">가격 단위 : 10,000원<%-- <input type="text" value="${address}"/> --%></td></tr>
	  	  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">주소</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_addr}
			</td>
		  </tr>
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">거래 종류</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_deal_type}
			</td>
		  </tr>
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">방 종류</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_type} 
			</td>
		  </tr>		  
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">가격</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_cost}
			</td>
		  </tr>		  
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">보증금</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_deposit}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">건물 층수</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_layer}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">물건 크기</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_size}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">관리비</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_manage_cost}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">주차 가능 여부</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_park_tf}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">엘레베이터 유무</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_ele_tf}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">반려동물 가능 여부</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_pet_tf}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">입주가능일</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_avail_date}
			</td>
		  </tr>	
		  <tr>
			<td width="140" align="center" bgcolor="#99ccff" height="22">거래 완료 여부</td>
			<td width="450" bgcolor="ffffff" style="padding-left: 10">
				${item.item_close_tf}
			</td>
		  </tr>		
	 	</table>   
	  </td>
	</tr> 
	<tr><td><br></td></tr>
 <tr>
 <td> 	    
  <table border="1" bordercolor="#1a8cff" style="background-color: #1a8cff">
	<tr>
		<td bgcolor="#99ccff" height="22" colspan="2">&nbsp;&nbsp;<b><중개사 정보></b>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="140" align="center" bgcolor="#99ccff" height="22">중개사무소명</td>
		<td width="450" bgcolor="ffffff" style="padding-left: 10">
			${agent.OFFICE}
		</td>
	</tr>
	<tr>
		<td width="140" align="center" bgcolor="#99ccff" height="22">대표명</td>
		<td width="450" bgcolor="ffffff" style="padding-left: 10">
			${agent.NAME} 
		</td>
	</tr>	
	<tr>
		<td width="140" align="center" bgcolor="#99ccff" height="22">주소</td>
		<td width="450" bgcolor="ffffff" style="padding-left: 10">
			${agent.ADDR}
		</td>
	</tr>	  
	<tr>
		<td width="140" align="center" bgcolor="#99ccff" height="22">전화번호</td>
		<td width="450" bgcolor="ffffff" style="padding-left: 10">
			${agent.PHONE}
		</td>
	</tr>		  
	<tr>
		<td width="140" align="center" bgcolor="#99ccff" height="22">이메일</td>
		<td width="450" bgcolor="ffffff" style="padding-left: 10">
			${agent.EMAIL}
		</td>
	</tr>	
  </table>
 </td>
 </tr>
 <tr><td align="center">
 	<br>
	     	<input type="button" value="목록" onClick="itemList('<c:url value='/item/list'>
	     		    <c:param name='address' value='${address}'/></c:url>') "/> 
	     	&nbsp; &nbsp;
	     	 <a href="<c:url value='/item/counsel/form' >
	     	 <c:param name='agent_no' value='${agent.NO}'/>
	     	 <c:param name='item_no' value='${item.item_no}'/></c:url> ">상담 예약</a>	    	 			 	 
 	    	&nbsp; &nbsp;
 	    	<input type="button" value="찜" onClick="createWish('<c:url value='/user/wish/create'><c:param name='item_no' value='${item.item_no}'/></c:url>')" />
			<input type="hidden" id="type" name="type" value="${type}" />
			
 </td></tr>
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