<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>


<html>

<head>
<title>Web Tools</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
</head>

<body>
<div>
<div style="margin: auto; position: absolute; top: 15%; left: 40%; bottom: 0; right: 0; transform: translate(-50% -50%); display:inline-block;">
<h2 style="margin:0 auto;"><a href="/WebTool/naver/searchAllPending" >발송대기검색</a></h2>
<h2 style="margin:0 auto;"><a href="/WebTool/naver/searchToday" >오늘받은 주문</a></h2>
<h2 style="margin:0 auto;"><a href="/WebTool/naver/searchShippingProcessedToday" >오늘송장처리한 주문</a></h2>
<h2 style="margin:0 auto;"><a href="/WebTool/naver/inventoryListPage" >재고</a></h2>
<h2 style="margin:0 auto;"><a href="/WebTool/naver/inventoryOrderPackageListPage" >재고 주문기준으로 보기</a></h2>
</div>
</div>

</body>
</html>