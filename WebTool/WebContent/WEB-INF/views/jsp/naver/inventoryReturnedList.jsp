<%@ page session="false" import="com.navertool.web.*,com.navertool.search.*,com.navertool.db.booking.*,java.util.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%

	HttpSession session = request.getSession();

	List<NaverOrderInventory> list = (List<NaverOrderInventory> ) session.getAttribute("inventoryList");
	InventoryOrderMap inventoryOrderMap = (InventoryOrderMap) session.getAttribute("inventoryMap");
	
	List<NaverOrder> orders = (List<NaverOrder> ) request.getAttribute("ordersList");
	
	Map<String, Map<String, List<NaverOrder>>> orderSummary = OrderSortUtil.summarizeOrderList2(orders);
	
%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<spring:url value="/resources/core/css/naver.css" var="coreCss" />
	<link href="${coreCss}" rel="stylesheet" />
</head>
<body>

<spring:url value="/naver/inventoryAdd" var="inventoryAddUrl" />

<div class="controls">

<form:form class="form-horizontal" method="post" modelAttribute="NewInventory"  action="${inventoryAddUrl}" >

		<div style="float: left;" >
		<spring:bind path="productNum">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >품목</span>
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 5px;"  path="productNum" name="productNum" id="productNum" >
    					<form:options  items="${productNumbers}" />
        			</form:select>  
        				
					<form:errors path="productNum" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	


		<div style="float: left;" >
		<spring:bind path="option1">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >사이즈</span>
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 5px;"  path="option1" name="option1" id="option1" placeholder="사이즈" >
    					<form:options  items="${sizeOptions2}" />
        			</form:select>  
					<form:errors path="option1" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>	
		
		<div style="float: left;" >
		<spring:bind path="qty">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >수량</span>
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 5px;"  path="qty" name="option1" id="qty" placeholder="갯수" >
    					<form:options  items="${qtyOptions}" />
        			</form:select>  
					<form:errors path="qty" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>			
		
		<div style="float: left;margin: 0px 0px 0px 5px;" >
		<spring:bind path="price">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >가격</span>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input style="height:20px; "  path="price" type="price" class="form-control " id="price" placeholder="가격" />
					<form:errors path="price" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>
		</div>
		</div>	
		
		<div style="float: left;margin: 0px 0px 0px 5px;" >
		<spring:bind path="orderStore">
			<div class="form-group ${status.error ? 'has-error' : ''}">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >구매처</span>
				<div class="col-sm-10">
					<form:input style="height:20px; "  path="orderStore" class="form-control " id="orderStore" placeholder="스토어" />
					<form:errors path="orderStore" class="control-label" /><br>
				</div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			</div>
		</spring:bind>
		</div>
				
		<div style="float: left;margin: 0px 0px 0px 5px;" >
		<spring:bind path="orderNum">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >구매처 주문번호</span>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input style="height:20px; "  path="orderNum" class="form-control " id="orderNum" placeholder="orderNum" />
					<form:errors path="orderNum" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>
		</div>
				
		<div style="float: left;margin: 0px 0px 0px 5px;" >
		<spring:bind path="orderPlacementNum">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >우리 주문번호</span>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input style="height:20px; "  path="orderPlacementNum" class="form-control " id="orderPlacementNum" placeholder="orderPlacementNum" />
					<form:errors path="orderPlacementNum" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>
		</div>

		<div style="float: left;" >
		<spring:bind path="datePurchased">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >구입날짜</span>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<!-- form:input path="searchField" type="text" class="form-control " id="searchField" value="${request.searchField}" placeholder="searchField" /-->
					
					<form:select style="height:25px; margin: 0px 0px 0px 5px;"  path="datePurchased" name="datePurchased" id="datePurchased" placeholder="datePurchased" items="${dateOptions}" >
    
        			</form:select>  
        				
					<form:errors path="datePurchased" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>	
		</div>				
		
		<div style="float: left;" >
		<div class="form-group">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" ></span>
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right" style="height:25px; margin: 0px 0px 0px 20px;" >재고입력</button>
			</div>
		</div>
		</div></div><div style="clear: both;" > 

</form:form>
</div>


<h4> 총 재고 개수 <%= list == null?0:list.size() %></h4>


<table class="summaryTable" >


<%
	Map<String, Map<String, List<NaverOrderInventory>>> summary = inventoryOrderMap.getMap();

    for (Map.Entry<String, Map<String, List<NaverOrderInventory>>> entry : summary.entrySet()) {
        
        String key = (String) entry.getKey();
        Map<String, List<NaverOrderInventory>> mapBySize = (Map<String, List<NaverOrderInventory>>) entry.getValue();
        Map<String, List<NaverOrder>> orderForProduct  =  orderSummary.get(key);

        
        for (Map.Entry<String, List<NaverOrderInventory>> entryBottom : mapBySize.entrySet()) {
            String size = (String) entryBottom.getKey();
        
            List<NaverOrderInventory> invs = (List<NaverOrderInventory>) entryBottom.getValue();
            List<NaverOrder> ordersForSize = orderForProduct == null? null: orderForProduct.get(size);

            
%>
		
<tr>
	<td>	

	<table class="summaryTable" >
<%
			for (NaverOrderInventory naverOrderInventory : invs) {
%>	
			<tr>
					<td> <%= key %> </td>
					<td> <%= size %> </td>
					<td> <%= naverOrderInventory.getOrderNum() %></td>
					<td> <%= naverOrderInventory.getOrderPlacementNum() %></td>
			</tr>
<%
			}
%>
	</table>

	</td>
	<td>
	
	<table class="summaryTable" >
<%
			if ( ordersForSize != null)
			for (NaverOrder ord : ordersForSize) {
%>	
			<tr>
					<td> <%= ord.getCustomerName() %></td>
					<td> <%= ord.getDatePaid() %></td>
					<td> <%= ord.getDateSendBy() %></td>
					<td> <%= ord.getProductName() %></td>
			</tr>
<%
			}
%>
	</table>

	</td>
</tr>

<%
		}

    }
%>

</table>


</body>
</html>