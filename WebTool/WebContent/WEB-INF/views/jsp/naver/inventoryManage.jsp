<%@ page session="false" import="com.navertool.web.*,com.navertool.search.*,com.navertool.db.booking.*,java.util.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%

	HttpSession session = request.getSession();

	List<NaverOrder> list = null;
	SearchContext ctx = (SearchContext) session.getAttribute("SearchContext");

	if (ctx != null)
	    list = ctx.getSearchResults();

	NaverOrder result = (NaverOrder) request.getAttribute("order");
	List<NaverOrderNote> notes = (List<NaverOrderNote>) request.getAttribute("notes");
	
	
%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<spring:url value="/resources/core/css/naver.css" var="coreCss" />
	<link href="${coreCss}" rel="stylesheet" />
</head>
<body>

</body>
</html>