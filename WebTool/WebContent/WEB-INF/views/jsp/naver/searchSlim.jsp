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
	
	SearchRequest searchRequest = ctx.getSearchRequest();
	SortByRequest sortReq 		= ctx.getSortRequest();
	
	

%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<spring:url value="/resources/core/css/naver.css" var="coreCss" />
	<link href="${coreCss}" rel="stylesheet" />
	
<style>
div.controls {
    width: 90%;
    padding: 10px;
    border: 1px solid gray;
    margin: 0;
    display: inline-block;
}
</style>	
</head>

<h3>오늘송장처리 간편리스트 				[ <a href="/WebTool/naver/startMenu" > 메뉴로 가기</a>] [ <a href="/WebTool/naver/searchAllPending" > 발송대기 전부</a>]  [ <a href="/WebTool/naver/searchShippingProcessedToday" > 오늘송장처리</a>]</h3>


<spring:url value="/naver/searchByFilter" var="findCenterUrl" />
<spring:url value="/naver/sortByFilter" var="sortCenterUrl" />

<div class="controls">


<form:form class="form-horizontal" method="post" modelAttribute="request" action="${findCenterUrl}" >

		<div style="float: left;" >
		검색:
		</div>
		<div style="float: left;margin: 0px 0px 0px 20px;" >
		<spring:bind path="keyword">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input style="height:20px; "  path="keyword" type="text" class="form-control " id="keyword" value="${request.keyword}" placeholder="검색어" />
					<form:errors path="keyword" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>
		</div>

		<div style="float: left;" >
		<spring:bind path="searchField">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;" path="searchField" name="searchField" id="searchField" placeholder="searchField" items="${searchFields}" value="${request.searchField}" >
    
        			</form:select>  
        				
					<form:errors path="searchField" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	

		<div style="float: left;" >
		<spring:bind path="searchDateField">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;"  path="searchDateField" name="searchDateField" id="searchDateField" placeholder="searchDateField" items="${searchByDateFields}" >
    
        			</form:select>  
        				
					<form:errors path="searchDateField" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	


		<div style="float: left;" >
		<spring:bind path="searchDateKey">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;"  path="searchDateKey" name="searchDateKey" id="searchDateKey" placeholder="searchDateKey" items="${searchDates}" >
    
        			</form:select>  
        				
					<form:errors path="searchDateKey" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	
		
		
		<div style="float: left;" >
		<spring:bind path="searchStatus">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;" path="searchStatus" name="searchStatus" id="searchStatus" placeholder="searchStatus" >
    					<form:options  items="${searchStatus}" />
        			</form:select>  
        				
					<form:errors path="searchStatus" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>			


		
		<div style="float: left;" >
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right" style="height:25px; margin: 0px 0px 0px 20px;" >검색</button>
				
				
			</div>
		</div>
		</div></div><div style="clear: both;" > 

</form:form>
</div>



<div class="controls">

<form:form class="form-horizontal" method="post" modelAttribute="sort" action="${sortCenterUrl}" >

		<div style="float: left;" >
		정렬변경:
		</div>


		<div style="float: left;" >
		<spring:bind path="sortByField">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;"  path="sortByField" name="sortByField" id="sortByField" >
    					<form:options  items="${sortByDateFields}" />
        			</form:select>  
        				
					<form:errors path="sortByField" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	


		<div style="float: left;" >
		<spring:bind path="sortType">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;"  path="sortType" name="sortType" id="sortType" placeholder="sortType" >
    					<form:options  items="${sortTypes}" />
        			</form:select>  
					<form:errors path="sortType" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	
		
		<div style="float: left;" >
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right" style="height:25px; margin: 0px 0px 0px 20px;" >정렬변경</button>
			</div>
		</div>
		</div></div><div style="clear: both;" > 

</form:form>
</div>


<h4> 검색결과 <%= list == null?0:list.size() %></h4>

<%
	
	if ( list == null )
	    return;

	for (NaverOrder result : list) {

		String titleStyle = "toprow";
		
		if ( DateUtil.warnForShippingTime(result.getDateSendBy()))
		    titleStyle="toprowAlert";
		
		
		List<NaverOrderNote> notes = BookingDbInstance.getInstance().getNaverBookingDao().getNaverOrderNoteByOrderNum(result.getOrderProductNum());		
%>

<table class="resultTable" >

	<tr height="1px" >
		<td class="topnoop"></td>
		<td class="topnoop"></td>
		<td class="topnoop"></td>
		<td class="topnoop"></td>
		<td class="topnoop"></td>
		<td class="topnoop"></td>
	</tr>


	<!-- 1 -->
	<tr class="<%=titleStyle %>">
		<td class="label" width="15%">
			상품주문번호	<br> 주문번호
		</td>
		<td colspan="2" width="30%">
			<a href="/WebTool/naver/detailPage?orderNum=<%=result.getOrderProductNum() %>"> <span style="font: normal normal bold 20px verdana;" > <%= result.getOrderProductNum() %> </span></a>
			<br>		 <span style="font: normal normal bold 10px verdana;" ><%= result.getOrderNum()%>	</span> <br>
			<span style="color: red; font-weight: bold"> <%= result.getFlagged() != null?"주목할만한 사항이 주문노트에 있슴. 확인 요망":"" %> </span>
		</td>

		<td>
				구매자 : <%= result.getCustomerName()%> [ <%= result.getCustomerId() %>]	<br> 수취인 : <%= result.getRecipientName() %>
		</td>
		
		<td class="label">
			결제일	<br> 발송기한						
		</td>
		<td>
			<%= result.getDateOrder() %> <br> <%= result.getDateSendBy() %><br>
			<span style="color: red; font-weight: bold"> <%= DateUtil.warnForShippingTime(result.getDateSendBy())?"발송기한 임박":"" %>   </span>
		</td>
		
	</tr >

	<!-- 3 -->

	<tr >
		<td class="label">
			현재주문상태					
		</td>
		<td>
			<%=result.getOrderStatus() %> |<br> <%=ValueUtil.display(result.getOrderDetailStatus()) %>
		</td>

		<td class="label">
			임시송장번호 <br> 임시송장번호처리일							
		</td>
		<td>
			<%=ValueUtil.display(result.getTrackNumEntered()) %> <br> <%= ValueUtil.display( result.getDateTrackNumEntered() )  %>
			<br> 
			<span style="color: red; font-weight: bold"> <%= result.getEnteredWithoutSending() == null?"":"보내지않고 송장만 만들었슴" %>   </span>
		</td>
		<td class="label">
			 송장번호 (네이버) <br> 발송처리일					
		</td>
		<td>
				<%=ValueUtil.display( result.getTrackNum() )%> <br> <%= ValueUtil.display( result.getDateSent2() )  %>
		</td>
			

		
	</tr >
	
	<!-- 4 row 
	<tr >
		<td class="label" >
			수취인					
		</td>
		<td colspan="3">
			<%= result.getRecipientName() %> <br> <%= result.getShippingAddress() %>
							
		</td>
		<td class="label">
			수취인전화1<br>
			수취인전화2					
		</td>
		<td>
			<%= result.getPhoneRecipient() %> <br> <%= ValueUtil.display(result.getPhoneRecipient2()) %>
			
		</td>
		
	</tr >
	-->

	<!--  Notes Row -->	
	
<%
	if ( notes != null && notes.size() > 0 ) {
%>	
	
	<tr>
		<td colspan="6">
		
<table class="simpleTable" >

	<tr>
		<td width="20%">입력날짜</td>
		<td width="10%"></td>
		<td width="70%">
			<b>노트 및 주요사항 </b>
		</td>
	</tr>
<%

	for (NaverOrderNote note : notes) {

	    String styleCodeBySeverity = "";
	    
	    if ("ALERT".equalsIgnoreCase(note.getType()))
	        styleCodeBySeverity="background-color: #e27a7a;";
	    else  if ("REMINDER".equalsIgnoreCase(note.getType()))
	        styleCodeBySeverity="background-color: #f7ee88;";
	    
	    
%>
		<tr>
			<td width="20%" style="<%=styleCodeBySeverity %>"> <%= ValueUtil.display(note.getDateEntered()) %></td>
			<td width="10%" style="<%=styleCodeBySeverity %>"> <%= ValueUtil.display(note.getType()) %></td>
			<td  width="70%" style="<%=styleCodeBySeverity %>"> <%= ValueUtil.display(note.getNote()) %></td>
		</tr>

<%

	}
%>
</table>		
		
		
		</td>
	
	</tr>
<%
	}
%>

		
</table>





<br>
<%
	}
%>

</html>

