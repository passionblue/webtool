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

<h1>개별 주문 상세</h1>

<a href="/WebTool/naver/searchPage"> 검색결과로 돌아가기</a>


<div style="background-color: #fafafa;">
<table class="resultTable" >

	<!-- 1 -->
	<tr class="toprow">
		<td class="label">
			상품주문번호	<br> 주문번호				
		</td>
		<td>
			<b><%= result.getOrderProductNum() %></b> <br>		<%= result.getOrderNum()%>	
		</td>
		<td class="label">
			구매자 <br> 수취인					
		</td>
		<td>
				<%= result.getCustomerName()%> [ <%= result.getCustomerId() %>]	<br> <%= result.getRecipientName() %>
		</td>
		
		<td class="label">
			결제일	<br> 발송기한						
		</td>
		<td>
			<b><%= result.getDateOrder() %> <br> <%= result.getDateSendBy() %> </b>
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
			주문 스토어 <br> 스토어 주문 넘버 <br> 매입가격
		</td>
		<td>
			 <%=ValueUtil.display(result.getStoreName(), "<해당정보 없음>") %> <br>
			 <%=ValueUtil.display(result.getStoreOrderNumber(), "<해당정보 없음>") %><br>
			 <%=ValueUtil.display(result.getPriceOriginalProduct(), "<해당정보 없음>") %>
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
		<td colspan="2" style="font: normal normal normal 18px verdana;">
			<b><a target="_blank" href="http://storefarm.naver.com/joyofliving/products/<%= result.getProductNum()%>"    > <%= result.getProductNum()%> </a></b> <br><br>				
			<span class="font: normal nomal normal 8px verdana;"><%= result.getProductName()%></span>		
			<br><br>
			<%=result.getOptionInfo() %> [<%=result.getOptionCode() %> ] 
			<br>
			<h1><%= ValueUtil.display(result.getProductInfo1(), result.getOptionCode()) %></h1>
		</td>
		
			
		<td colspan="3">
			<img alt="이미지 없음" width="400" height="400"  src="/WebTool/resources/images/products/<%=result.getProductNum()  %>.png">
				
		</td>
		
	</tr >

	<!-- 3 -->

	<tr >
		<td class="label">
			주문상태					
		</td>
		<td>
			<%=result.getOrderStatus() %> | <%=ValueUtil.display(result.getOrderDetailStatus()) %>
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
			<span style="color: red"> <%= result.getEnteredWithoutSending() == null?"":"보내지않고 송장만 만들었슴" %>   </span>
			<%
	if (  result.getEnteredWithoutSending() != null  ) {

%>
	<form:form class="form-horizontal" method="post" modelAttribute="OrderUpdateReuqest" action="${updateOrderUrl}">
			
			<form:hidden path="orderProductNum" value="<%= result.getOrderProductNum()%>"/>
			<form:hidden path="fieldName" value="confirmShipping"/>
			
			<div style="float: left; padding: 2px 0px 2px 0px;" >
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right" style="background-color: yellow">물품발송 확인으로 전환하기 </button>
				</div>
			</div>
			</div></div>
	</form:form>
<%
}
%>
		</td>
		
	</tr >
	
	<!-- 4 row -->
	<tr >
		<td class="label" >
			수취인 주소 및 배송문자					
		</td>
		<td colspan="3">
			<%= result.getRecipientName() %> <br> <%= result.getShippingAddress() %>
			<br> <%= result.getZip() %>
			<br> <%= result.getDeliveryText()==null?"<별도 배송 내용 없슴>":result.getDeliveryText() %>
							
		</td>
		<td class="label" >
			수취인전화1<br>
			수취인전화2					
		</td>
		<td>
			<%= result.getPhoneRecipient() %> <br> <%= ValueUtil.display(result.getPhoneRecipient2()) %>
			
		</td>
		
	</tr >

		
</table>
</div>

<!-- ==================================================================================================================================== -->
<!-- 발송/송장-->
<!-- ==================================================================================================================================== -->


<spring:url value="/naver/orderUpdate" var="updateOrderUrl" />

<div style="">


	<form:form class="form-horizontal" method="post" modelAttribute="OrderUpdateReuqest" action="${updateOrderUrl}">
	
			<div style="float: left;" >
			<spring:bind path="value1">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10" >
						<form:input style="height:20px" size="30" path="value1" type="text" class="form-control " id="value1" placeholder="송장번호" />
						<form:errors path="value1" class="control-label" />
					</div>
				</div>
			</spring:bind>
			</div>
			
			
			<spring:bind path="value2">
				<form:checkbox path="value2" id="value2" value="1" placeholder="value2" label="보내지않고 일단 송장만 넣기"/>
			</spring:bind>
			
			<form:hidden path="orderProductNum" value="<%= result.getOrderProductNum()%>"/>
			<form:hidden path="fieldName" value="trackNumEntered"/>
			
			<div style="float: left; padding-left: 10px" >
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">송장번호입력</button>
				</div>
			</div>
			</div></div><div style="clear: both;" > 
	</form:form>
</div>	
<br>

<table class="resultTable" >

	<tr>
		<td width="20%"></td>
		<td width="10%"></td>
		<td width="70%">
			<b>노트 및 주요사항 </b>
		</td>
	</tr>
<%
	
	if ( notes == null )
	    return;

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

<!-- ==================================================================================================================================== -->
<!-- Notes -->
<!-- ==================================================================================================================================== -->

<hr> 

<span style="color: red; font: normal normal bold 16px; padding: 0px 0px 20px 0px;"> 노트  </span> <br>


<div style="padding: 20px 0px 0px 0px;"">
	<form:form class="form-horizontal" method="post" modelAttribute="OrderUpdateReuqest" action="${updateOrderUrl}">
	
			<div style="float: left;" >
			<spring:bind path="value1">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10">
						<form:textarea rows="5" cols="100" size="100" path="value1" type="text" class="form-control " value="" id="value1" placeholder="value1" />
						<form:errors path="value1" class="control-label" />
					</div>
				</div>
			</spring:bind>
			</div>
			
			<form:hidden path="orderProductNum" value="<%= result.getOrderProductNum()%>"/>
			<form:hidden path="fieldName" value="note"/>
			
			
			<div style="float: left;padding-left: 20px" >
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">노트입력</button>
				</div>
			</div>

		<div style="float: left;" >
		<spring:bind path="value2">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select path="value2" name="value2" id="value2" placeholder="value2" items="${dropdownNoteTypes}"  >
    
        			</form:select>  
				</div>
			</div>
		</spring:bind>	
		</div>				
			
			</div></div><div style="clear: both;" > 
	</form:form>
</div>		

<br> 

<hr> 

<!-- ==================================================================================================================================== -->
<!-- 주문정보 -->
<!-- ==================================================================================================================================== -->

<span style="color: red; font: normal normal bold 16px"> 주문정보 입력  </span> <br>

<div style="padding: 20px 0px 0px 0px;"">


	<form:form class="form-horizontal" method="post" modelAttribute="OrderUpdateReuqest" action="${updateOrderUrl}">
	
			<form:hidden path="orderProductNum" value="<%= result.getOrderProductNum()%>"/>
			<form:hidden path="fieldName" value="orderPlacement"/>

			<div style="float: left;" >
			<spring:bind path="value1">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10" >
						<form:input style="height:20px" size="30" path="value1" type="text" class="form-control " id="value1" value="<%= ValueUtil.display(result.getOrderPlacementNumber()) %>" placeholder="묶음번호" />
						<form:errors path="value1" class="control-label" />
					</div>
				</div>
			</spring:bind>
			</div>
			
			<div style="float: left;" >
			<spring:bind path="value2">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10" >
						<!-- form:input style="height:20px; margin: 0px 0px 0px 20px;" size="30" path="value2" type="text" class="form-control " id="value2" placeholder="스토어 이름" /-->
						<form:select style="height:25px; margin: 0px 0px 0px 20px;" path="value2" name="value2" id="value2"  placeholder="value2" items="${dropdownStoreNames}"  >
						</form:select>
					</div>
				</div>
			</spring:bind>
			</div>			

			<div style="float: left;" >
			<spring:bind path="value3">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10" >
						<form:input style="height:20px; margin: 0px 0px 0px 20px;" size="30" path="value3" type="text" class="form-control"  value="<%= ValueUtil.display(result.getStoreOrderNumber()) %>" id="value3" placeholder="스토어 오다넘버" />
						<form:errors path="value3" class="control-label" />
					</div>
				</div>
			</spring:bind>
			</div>			

			<div style="float: left;" >
			<spring:bind path="value5">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10" >
						<form:input style="height:20px; margin: 0px 0px 0px 20px;" size="10" path="value5" type="text" class="form-control " value="<%= ValueUtil.display(result.getPriceOriginalProduct()) %>" id="value5" placeholder="매입가격" />
						<form:errors path="value5" class="control-label" />
					</div>
				</div>
			</spring:bind>
			</div>				

			<div style="float: left;" >
			<spring:bind path="value4">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<div class="col-sm-10" >
						<form:input style="height:20px; margin: 0px 0px 0px 20px;" size="30" path="value4" type="text" class="form-control " value="<%= ValueUtil.display(result.getOrderAccount()) %>" id="value4" placeholder="어카운트노트" />
						<form:errors path="value4" class="control-label" />
					</div>
				</div>
			</spring:bind>
			</div>		
			
				
			
			<div style="float: left; padding-left: 10px" >
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">주문정보입력</button>
				</div>
			</div>
			</div></div><div style="clear: both;" > 
	</form:form>
</div>	

<br>

<!-- ==================================================================================================================================== -->
<!-- Various -->
<!-- ==================================================================================================================================== -->

<hr> 

<span style="color: red; font: normal normal bold 16px; padding: 0px 0px 20px 0px;"> 여러가지 기능  </span> <br>

<div style="padding: 20px 0px 0px 0px;"">

	<form:form class="form-horizontal" method="post" modelAttribute="OrderUpdateReuqest" action="${updateOrderUrl}">
	
			<form:hidden path="orderProductNum" value="<%= result.getOrderProductNum()%>"/>
			<form:hidden path="fieldName" value="forcedStatus"/>
			<form:hidden path="value1" value="취소완료"/>
			
			<div style="float: left; padding-left: 0px" >
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">강제로 취소 하기</button>
				</div>
			</div>
			</div></div><div style="clear: both;" > 
	</form:form>
</div>	
<br>
<hr> 
<span style="color: red; font: normal normal bold 16px; padding: 0px 0px 20px 0px;"> 재고 및 반품  </span> <br>

<spring:url value="/naver/inventoryAdd" var="inventoryAddUrl" />

<div style="padding: 20px 0px 0px 0px;"">

	<form:form class="form-horizontal" method="post" modelAttribute="NewInventory" action="${inventoryAddUrl}">
	

			<form:hidden path="datePurchased" value="<%= ValueUtil.display(result.getDatePlaced())%>"/>
			<form:hidden path="productNum" value="<%= ValueUtil.display(result.getProductNum())%>"/>
			<form:hidden path="productDescription" value="<%= ValueUtil.display(result.getProductName())%>"/>
			<form:hidden path="price" value="<%= ValueUtil.display(result.getPriceOriginalProduct())%>"/>
			<form:hidden path="qty" value="1"/>
			
			<form:hidden path="orderStore" value="<%= ValueUtil.display(result.getStoreName())%>"/>
			<form:hidden path="orderNum" value="<%= ValueUtil.display(result.getStoreOrderNumber())%>"/>
			
			
			<form:hidden path="orderPlacementNum" value="<%= ValueUtil.display(result.getOrderProductNum())%>"/>
			<form:hidden path="option1" value="<%= ValueUtil.display(result.getProductInfo1())%>"/>


			<spring:bind path="needToReturnToStore">
				<form:checkbox path="needToReturnToStore" id="needToReturnToStore" value="1" placeholder="value2" label="보내지않고 일단 송장만 넣기"/>
			</spring:bind>
			
			<div style="float: left; padding-left: 0px" >
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn-lg btn-primary pull-right">주문 취소후 재고로 등록 </button>
					</div>
				</div>
			</div></div><div style="clear: both;" > 
	</form:form>
</div>	


<br>
<br>
<br>
<br>
<br>

</html>

