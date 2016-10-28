<%@ page session="false"
	import="com.navertool.web.*,com.navertool.search.*,com.navertool.db.booking.*,java.util.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
    HttpSession session = request.getSession();

    List<NaverOrder> list = null;
    SearchContext ctx = (SearchContext) session.getAttribute("SearchContext");

    if (ctx != null)
        list = ctx.getSearchResults();
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<spring:url value="/resources/core/css/naver.css" var="coreCss" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<body style="margin:0 auto;">

<spring:url value="/naver/checkmeout" var="findCenterUrl" />
<div>
<div  style="margin: auto; position: absolute; top: 50%; left: 50%; bottom: 0; right: 0; transform: translate(-50% -50%); display:inline-block;">

	<form:form class="form-horizontal" method="post"
		modelAttribute="AuthRequest" action="${findCenterUrl}">

		<div style="float: left;">
			<spring:bind path="phrase">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10">
						<form:input path="phrase" type="text" class="form-control "
							id="phrase"  placeholder="phrase" />
						<form:errors path="phrase" class="control-label" />
					</div>
				</div>
			</spring:bind>
		</div>

		<div style="float: left;">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">체크</button>
				</div>
			</div>
		</div>
	</form:form>
</div>
</div>
</body>
</html>