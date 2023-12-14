<%
    Exception e = (Exception) request.getAttribute("exception");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "header.html" %>
<div class="align-items-center h-100 d-flex w-50 mx-auto">
    <div class="mx-auto text-center">
        <h2 class="display-1 m-0 font-weight-bolder text-muted" style="font-size:80px;">Exception</h2>
        <h3 class="mb-3" style="color:red;"><%= e.getMessage() %></h3>
        <h4 class="mb-3" style="color:red;"><%= e.getStackTrace() %></h4>
    </div>
</div>
<%@ include file = "footer.html" %>
