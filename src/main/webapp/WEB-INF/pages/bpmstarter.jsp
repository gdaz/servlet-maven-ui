<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="testTemplate">
    <tiles:putAttribute name="testBody" value="/pages/execution/home.jsp" />
</tiles:insertDefinition>