<%@page import="com.navertool.util.DataUtil"%>
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

<h3>죠이리빙 주문/발송 검색및 운영 툴 				
[ <a href="/WebTool/naver/startMenu" > 메뉴로 가기</a>] 
[<a href="/WebTool/naver/searchToday" >오늘받은 주문</a>]
[ <a href="/WebTool/naver/searchAllPending" > 발송대기 전부</a>] 
[ <a href="/WebTool/naver/searchShippingProcessedToday" > 오늘송장처리</a>]  


</h3>

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
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;" path="searchField" name="searchField" id="searchField" placeholder="searchField" items="${dropdownSearchFields}" value="${request.searchField}" >
    
        			</form:select>  
        				
					<form:errors path="searchField" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	

		<div style="float: left;" >
		<spring:bind path="option1">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;" path="option1" name="option1" id="option1" placeholder="option1" value="${request.option1}" >
    					<form:option  value="" label="사이즈" />
    					<form:options  items="${dropdownSizeOptions}" />
        			</form:select>  
        				
					<form:errors path="option1" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	

		<div style="float: left;" >
		<spring:bind path="searchDateField">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;"  path="searchDateField" name="searchDateField" id="searchDateField" placeholder="searchDateField" items="${dropdownSearchDateFields}" >
    
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
					
					<form:select style="height:25px; margin: 0px 0px 0px 20px;"  path="searchDateKey" name="searchDateKey" id="searchDateKey" placeholder="searchDateKey" items="${dropdownSearchDates}" >
    
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
    					<form:options  items="${dropdownSearchStatus}" />
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

%>


<table class="summaryTable" >

	<tr>
	
	<td style="width:500px;"> <%="" %>	

<%

	String [] sizeList = new String[]{"NoSizeSet", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12"}; 
	for(int i = 0; i < sizeList.length;i++) {
%>
		<td> <%= sizeList[i].equals("NoSizeSet")? "없음":sizeList[i] %> </td>
<%
    }
%>

	<td> 합계 </td>
	</tr>


<%
	Map<String, Map<String, List<NaverOrder>>> summary = OrderSortUtil.summarizeOrderList(list);

    for (Map.Entry<String, Map<String, List<NaverOrder>>> entry : summary.entrySet()) {
        String key = (String) entry.getKey();

        Map<String, List<NaverOrder>> mapBySize = (Map<String, List<NaverOrder>>) entry.getValue();

%>


	<tr>
	
	<td style="width:00px;"> <%=key %>	
<%

	int total = 0;

	for(int i = 0; i < sizeList.length;i++) {

	    List<NaverOrder> orders = mapBySize.get(sizeList[i]);
	    String count = (orders == null || orders.size()==0)? "": ""+ orders.size();
	    
	    if ( orders != null)
	    	total += orders.size();
	    
%>

		<td> <%= count %> </td>
	
<%
    }
%>
	
		<td> <%= total==0?"":total %>
	</tr>

<%
    }
%>




</table>






<%
	
	

	for (NaverOrder result : list) {

		String titleStyle = "toprow";
		
		if ( DataUtil.isProcessingDone(result)) 
		    titleStyle="toprowProcessed";
		else if ( DateUtil.warnForShippingTime(result.getDateSendBy()))
		    titleStyle="toprowAlert";
		
		String tableAdditional = "";

		if ( DataUtil.isTerminalNoPurchaseState(result)) 
		    tableAdditional="background-color: #a0a0a0;";
		
		
%>

<table class="resultTable" style="<%=tableAdditional %>">

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
			<%= ValueUtil.display(result.getDatePaid()) %> <br> <%= ValueUtil.display(result.getDateSendBy()) %><br>
			<span style="color: red; font-weight: bold"> <%= DateUtil.warnForShippingTime(result.getDateSendBy()) && !DataUtil.isProcessingDone(result)?"발송기한 임박":"" %>   </span>
		</td>
		
	</tr >
	
	<!-- 현지 주문 상태 -->
	<tr >
		<td class="label">
			주문 묶음 번호 					
		</td>
		<td>
			 <%=ValueUtil.display(result.getOrderPlacementNumber(), "<해당정보 없음>") %>
		</td>
		<td class="label">
			주문 스토어 <br> 스토어 주문 넘버
		</td>
		<td>
			 <%=ValueUtil.display(result.getStoreName(), "<해당정보 없음>") %> <br>
			 <%=ValueUtil.display(result.getStoreOrderNumber(), "<해당정보 없음>") %>
		</td>
			
		<td class="label">
			주문 시간 <br> 스토어 발송 시간 <br> 도착 예상날짜
		</td>
		<td>
			 <%=ValueUtil.display(result.getDatePlaced(), "<해당정보 없음>") %> <br>
			 <%=ValueUtil.displayDate(result.getDateShipment(), "<해당정보 없음>") %> <br>
			 <%=ValueUtil.displayDate(result.getDateExpected(), "<해당정보 없음>") %>
		</td>
		
	</tr >
	
		
	<!-- 2 -->
	<tr >
		<td class="label">
			상품넘버	<br> 옵션				
		</td>
		<td colspan="3">
				
			<span class="font: normal nomal normal 8px verdana;"><%= result.getProductName()%></span>		
			<b><a target="_blank" href="http://storefarm.naver.com/joyofliving/products/<%= result.getProductNum()%>"    > <%= result.getProductNum()%> </a></b>
			
			<br>
			<%=result.getOptionInfo() %> [<%=result.getOptionCode() %> ] 
			
			<div style="padding: 10px 0px 0px 0px">
				<form:form class="form-horizontal" method="post" modelAttribute="request" action="${findCenterUrl}" >
				
							<form:hidden path="keyword" value="<%= result.getProductNum()%>" />
							<form:hidden path="searchField" value="productNum" />
							<form:hidden path="option1" value="${request.option1}" />
							<form:hidden path="searchDateField" value="${request.searchDateField}" />
							<form:hidden path="searchDateKey" value="${request.searchDateKey}" />
							<form:hidden path="searchStatus" value="${request.searchStatus}" />
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn-lg btn-primary pull-right" style="height:25px; margin: 0px 0px 0px 20px;" >이신발만 검색하기</button>
								
								
							</div>
						</div>
				
				</form:form>	
			</div>		
		</td>
		
			
		<td >
			<img alt="이미지 없음" width="100" height="100"  src="/WebTool/resources/images/products/<%=result.getProductNum()  %>.png">
		</td>
		<td>
			사이즈<br>
			<h1><%= ValueUtil.display(result.getProductInfo1(), result.getOptionCode()) %></h1>
		</td>
		
	</tr >

	<!-- 3 -->

	<tr >
		<td class="label">
			주문상태					
		</td>
		<td>
			<%=result.getOrderStatus() %> |<br> <%=ValueUtil.display(result.getOrderDetailStatus()) %>
		</td>
		<td class="label">
			 송장번호 (네이버) <br> 발송처리일					
		</td>
		<td>
				<%=ValueUtil.display( result.getTrackNum() )%> <br> <%= ValueUtil.display( result.getDateSent2() )  %>
		</td>
			
		<td class="label">
			임시송장번호 <br> 임시송장번호처리일							
		</td>
		<td>
			<%=ValueUtil.display(result.getTrackNumEntered()) %> <br> <%= ValueUtil.display( result.getDateTrackNumEntered() )  %>
			<br> 
			<span style="color: red; font-weight: bold"> <%= result.getEnteredWithoutSending() == null?"":"보내지않고 송장만 만들었슴" %>   </span>
		</td>
		
	</tr >
	
	<!-- 4 row -->
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

		
</table>

<br>
<%
	}
%>

</html>

