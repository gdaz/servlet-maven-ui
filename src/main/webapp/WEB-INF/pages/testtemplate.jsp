<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <title>ProtoType Project</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-select.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-slider.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/fontawesome/css/font-awesome.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/datatables.css">
<%--    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.jqueryui.css">--%>
<%--    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.dataTables.css">--%>
<%--    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dataTables.bootstrap.css">--%>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/extonline.css" type="text/css">

    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-slider.min.js"></script>

    <script src="<%=request.getContextPath()%>/resources/js/jquery-3.4.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>

    <!-- Bootstrap 3.3.5 -->
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-select.js"></script>

    <script src="<%=request.getContextPath()%>/resources/js/datatables.js"></script>
<%--    <script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.js"></script>--%>
<%--    <script src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap.js"></script>--%>
<%--    <script src="<%=request.getContextPath()%>/resources/js/dataTables.jqueryui.js"></script>--%>
</head>

<body>
<div class="body-wrapper">
    <tiles:insertAttribute name="testHeader"/>
    <%--<tiles:insertAttribute name="testSidebar"/>--%>
    <tiles:insertAttribute name="testBody"/>
    <tiles:insertAttribute name="testFooter"/>

    <%--<div class="control-sidebar-bg"></div>--%>
</div><!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<%--<!-- Bootstrap 3.3.5 -->--%>
<%--<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>--%>
<%--<script src="<%=request.getContextPath()%>/resources/js/bootstrap-select.js"></script>--%>

<%--<script src="<%=request.getContextPath()%>/resources/js/datatables.min.js"></script>--%>
</body>
</html>
