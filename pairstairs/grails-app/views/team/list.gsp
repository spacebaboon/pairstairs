<%@ page import="uk.co.mb.pairstairs.Team" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="navbar" role="navigation">
    <div class="navbar-inner">
        <ul class="nav">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
            <li><g:link class="list" controller="coder" action="list"><g:message code="coder.list.label"
                                                                                 args="[entityName]"/></g:link></li>
        </ul>
    </div>
</div>

<div id="list-team" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'team.name.label', default: 'Name')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${teamInstanceList}" status="i" var="teamInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${teamInstance.id}">${fieldValue(bean: teamInstance, field: "name")}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${teamInstanceTotal}"/>
    </div>
</div>
</body>
</html>
