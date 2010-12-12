
<%@ page import="com.logansrings.booklibrary.Author" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'author.label', default: 'Author')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <%-- <g:sortableColumn property="id" title="${message(code: 'author.id.label', default: 'Id')}" /> --%>
                        
                            <g:sortableColumn property="lastName" title="${message(code: 'author.lastName.label', default: 'Last Name')}" />
                            <g:sortableColumn property="firstName" title="${message(code: 'author.firstName.label', default: 'First Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${authorInstanceList}" status="i" var="authorInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                           <%--  <td><g:link action="show" id="${authorInstance.id}">${fieldValue(bean: authorInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: authorInstance, field: "firstName")}</td>
                        
                             <td>${fieldValue(bean: authorInstance, field: "lastName")}</td> --%>

                            <td><g:link action="show" id="${authorInstance.id}">${fieldValue(bean: authorInstance, field: "lastName")}</g:link></td>
                            <td>${fieldValue(bean: authorInstance, field: "firstName")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${authorInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
