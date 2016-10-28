<%@ page session="false" import="com.navertool.web.*,com.navertool.search.*,com.navertool.db.booking.*,java.util.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%

	HttpSession session = request.getSession();
	List<NaverOrderInventory> list = (List<NaverOrderInventory> ) request.getAttribute("inventoryList");
	
	
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
<spring:url value="/naver/inventoryUpdate" var="inventoryUpdateUrl" />

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
					<form:input style="height:20px; " size="5" path="price" type="price" class="form-control " id="price" placeholder="가격" />
					<form:errors path="price" class="control-label" /><br>
				</div>
			</div>
		</spring:bind>
		</div>
		</div>	
		
		<!-- 
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
		 -->
		 
		<div style="float: left;" >
		<spring:bind path="orderStore">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >스토어</span>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10" >
					<form:select style="height:25px" path="orderStore" name="orderStore" id="orderStore"  placeholder="orderStore" items="${dropdownStoreNames}"  >
					</form:select>
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
		
		<div style="float: left;margin: 0px 0px 0px 5px;" >
		<spring:bind path="note">
			<span style="font: normal normal bold 10px verdana;margin: 0px 0px 0px 5px;" >노트</span>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<div class="col-sm-10">
					<form:input style="height:20px; "  path="note" class="form-control " id="note" placeholder="note" />
					<form:errors path="note" class="control-label" /><br>
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

<%
	if ( list == null )
	    return;

	InventoryOrderMap inventoryOrderMap = new InventoryOrderMap(list, "orderNum", "option1");

	
	
%>

<table class="summaryTable" >


<%
	Map<String, Map<String, List<NaverOrderInventory>>> summary = inventoryOrderMap.getMap();

    for (Map.Entry<String, Map<String, List<NaverOrderInventory>>> entry : summary.entrySet()) {
        
        String key = (String) entry.getKey();
        Map<String, List<NaverOrderInventory>> mapBySize = (Map<String, List<NaverOrderInventory>>) entry.getValue();
        
		List<NaverOrderInventory> invForOrder = new ArrayList();
        
        for (Map.Entry<String, List<NaverOrderInventory>> entryBottom : mapBySize.entrySet()) {
            String size = (String) entryBottom.getKey();
        
            List<NaverOrderInventory> invs = (List<NaverOrderInventory>) entryBottom.getValue();
            
            if (invs == null || invs.size() == 0 )
                continue;
            
            invForOrder.addAll(invs);
        }
        
        if (invForOrder.size() == 0 )
            continue;
        
        NaverOrderInventory noinv = invForOrder.get(0);
        
%>

		<tr>
			<td> <span style="font: normal normal bold 20px verdana;" >스토어주문번호: <%= noinv.getOrderNum() %> &nbsp;&nbsp;&nbsp;&nbsp;  죠이리빙 주문번호: <%= ValueUtil.display(noinv.getOrderPlacementNum()) %> &nbsp;&nbsp;&nbsp;&nbsp;  주문날짜:<%= noinv.getDatePurchased() %> &nbsp;&nbsp;&nbsp;노트: <%= ValueUtil.display(noinv.getNote()) %></span></td>
		</tr>

		<tr>
			<td>	

				<table class="summaryTable" >
						<tr>
								<td> 상품번호 </td>
								<td> 상품이름</td>
								<td> 스토어</td>
								<td> 사이즈 </td>
								<td> Action </td>
						</tr>

<%


            
		for (NaverOrderInventory naverOrderInventory : invForOrder) {
%>	
			<tr>
					<td> <%= naverOrderInventory.getProductNum() %> </td>
					<td> <%= ValueUtil.display(naverOrderInventory.getProductDescription()) %> </td>
					<td> <%= naverOrderInventory.getOrderStore() %></td>
					<td> <%= naverOrderInventory.getOption1() %></td>
					<td>
					
<form:form class="form-horizontal" method="post" modelAttribute="NewInventory"  action="${inventoryUpdateUrl}" >

			<form:hidden path="option2" value="<%= naverOrderInventory.getId()%>"/>
			<form:hidden path="removed" value="true"/>
					
			<div style="float: left;" >
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn-lg btn-primary pull-right" style="height:25px; margin: 0px 0px 0px 20px;" >없애기</button>
					</div>
				</div>
			</div><div style="clear: both;" ></div> 
		
</form:form>
		
					
					
					</td>
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



</body>
</html>