<%@ page session="false" import="com.webtool.model.*,java.util.*" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%

	List<PriceCheckResult> list = (List) request.getAttribute("prices");

	PriceCheckRequest checkRequest = (PriceCheckRequest) request.getAttribute("request");



%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" >
	<spring:url value="/resources/core/css/hello.css" var="coreCss" />
	<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/price/findCenter" var="findCenterUrl" />

<div style="">
<form:form class="form-horizontal" method="post" modelAttribute="checkForm" action="${findCenterUrl}">

		<div style="float: left;" >
		<spring:bind path="price">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">MSRP ($)</label>
				<div class="col-sm-10">
					<form:input path="price" type="text" class="form-control " id="price" value="${request.price}" placeholder="Price" />
					<form:errors path="price" class="control-label" />
				</div>
			</div>
		</spring:bind>
		</div>

		<div style="float: left;" >
		<spring:bind path="discountRate">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Discount (%)</label>
				<div class="col-sm-10">
					<form:input path="discountRate" type="text" class="form-control " id="discountRate" value="${request.discountRate}" placeholder="discountRate" />
					<form:errors path="discountRate" class="control-label" />
				</div>
			</div>
		</spring:bind>	
		</div>	

		<div style="float: left;" >
		<spring:bind path="targetShippingWon">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Target Shipping (W)</label>
				<div class="col-sm-10">
					<form:input path="targetShippingWon" type="text" class="form-control " id="targetShippingWon" value="${request.targetShippingWon}" placeholder="targetShippingWon" />
					<form:errors path="targetShippingWon" class="control-label" />
				</div>
			</div>
		</spring:bind>
		</div>

		<div style="float: left;" >
		<spring:bind path="discountPrice">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">FXRate</label>
				<div class="col-sm-10">
					<form:input path="fxRate" type="text" class="form-control " id="fxRate" value="${request.fxRate}" placeholder="FX" />
					<form:errors path="fxRate" class="control-label" />
				</div>
			</div>
		</spring:bind>
		</div>

		<div style="float: left;" >
		<spring:bind path="rebatePercent">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Rebate (%)</label>
				<div class="col-sm-10">
					<form:input path="rebatePercent" type="text" class="form-control " id="rebatePercent" value="${request.rebatePercent}" placeholder="rebatePercent" />
					<form:errors path="rebatePercent" class="control-label" />
				</div>
			</div>
		</spring:bind>		
		</div> 
		
		<div style="float: left;" >
		<div class="form-group">
			<label class="col-sm-2 control-label">.</label>
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
				<a href="/WebTool" >>>  HOME</a>
			</div>
		</div>
		</div></div><div style="clear: both;" > 

</form:form>
</div>


<!-- ========================================================= -->

<div>

<% 	if (list == null) return; 
 %>

<table class="resultTable" >
	<thead>

	<td>Original $ 	</td>
	<td>Purchase $ 	</td>
	<td>Disount % 	</td>
	<td>ShipIn$  </td>
	<td>ShipOuu$e  </td>
	<td>Paid Price  </td>
	<td>Total Cost  </td>
	<td>Fx  </td>
	<td>TotalExpense Won  </td>
	<td>Price Won  </td>
	<td>Shipping Won  </td>
	<td>Total Won  </td>
	<td>Fee Won  </td>
	<td>After Fee Won  </td>
	<td>Profits Won  </td>
	<td>Profits  </td>
	<td>Rebate  </td>

	</thead>



<%

	double purchasePrice = 0.0;
	double prevProfits = 0.0;

	for (PriceCheckResult result : list) {

		String style = "";
		
		if ( prevProfits < 0 &&  result.getProfits().doubleValue() >= 0.0)
		    style +="border-top : 4px #000000 solid;";
		    
		if ( result.getProfits().doubleValue() < 0.0 )
		    style +="background-color: #fa9191;";

		if ( result.getProfits().doubleValue() > 0.0 )
		    style +="background-color: #f0fe53;";
		    
		if ( result.getProfits().doubleValue() > 10.0 )
		    style +="background-color: #bcfc9c;";
		    
		if ( result.getProfits().doubleValue() > 20.0 )
		    style +="background-color: #70ca44;";

		if ( result.getProfits().doubleValue() > 30.0 )
		    style +="background-color: #356f18;";

		    
		if ( result.getProfits().doubleValue() > 30.0 )
		    style +="background-color: #287bd7;";
			    
		    
		    
%>
	<tr>

		<td style="<%=style %>"><%= result.getRegularPrice() %>	</td>
		<td style="<%=style %>;font-weight: bolder;"><%= result.getPurchasePrice() %>	</td>
		<td style="<%=style %>"><%= result.getSpecialDisountRate() %>	</td>
		<td style="<%=style %>"><%= result.getShippingInCharge() %> </td>
		<td style="<%=style %>"><%= result.getShippingOutCharge() %> </td>
		<td style="<%=style %>"><%= result.getFinalPrice() %> </td>
		<td style="<%=style %>"><%= result.getTotalExpense() %> </td>
		<td style="<%=style %>"><%= result.getFx() %> </td>
		<td style="<%=style %>"><%= result.getTotalExpenseWon() %> </td>
		<td style="<%=style %>;font-weight: bolder;"><%= result.getChargePriceWon() %> </td>
		<td style="<%=style %>"><%= result.getChargeShiipingWon() %> </td>
		<td style="<%=style %>"><%= result.getChargeCustomerWon() %> </td>
		<td style="<%=style %>"><%= result.getFeeAmountWon() %> </td>
		<td style="<%=style %>"><%= result.getTotalWon() %> </td>
		<td style="<%=style %>"><%= result.getProfitInWon() %> </td>
		<td style="<%=style %>"><%= result.getProfits() %> </td>
		<td style="<%=style %>"><%= result.getRebateAmount() %> </td>

	</tr>
<%

		purchasePrice =  result.getPurchasePrice().doubleValue();
		prevProfits = result.getProfits().doubleValue();
	}
%>




</table>


<div>
<p>Click the iFrame to open the URL in a separate browser window/tab.</p>

<iframe src="http://www.yahoo.com" width="400" height="400"></iframe>
</div>


</div> 


