<%@ page import="uk.co.mb.pairstairs.Team" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    %{--<g:javascript library="jquery"/>--}%
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css">
    <r:script language="javascript">
        $('td.pairing').click(function () {

            if ($(this).text() != '') {
                $(this).text('');
            } else {
                $(this).append('<span class="show-age days-old-0" title="today">X</span>');
            }

            var col = $(this).parent().children().index($(this));
            var row = $(this).parent().parent().children().index($(this).parent());

            $('#testtext').load('../togglePairing?col=' + col + '&row=' + row);

        });

        $('td.pairing').each(function () {
            var col = $(this).parent().children().index($(this));
            var row = $(this).parent().parent().children().index($(this).parent());
            $(this).load('../showPairing?col=' + col + '&row=' + row);
        });

        $("table").delegate('td.coder', 'mouseover mouseleave', function (e) {
            if (e.type == 'mouseover') {
                $(this).parent().addClass("hovercoder");
                $("colgroup").eq($(this).index()).addClass("hovercoder");
            }
            else {
                $(this).parent().removeClass("hovercoder");
                $("colgroup").eq($(this).index()).removeClass("hovercoder");
            }
        });

        $("table").delegate('td.pairing', 'mouseover mouseleave', function (e) {
            if (e.type == 'mouseover') {
                $(this).parent().addClass("hoverpairing");
                $("colgroup").eq($(this).index()).addClass("hoverpairing");
            }
            else {
                $(this).parent().removeClass("hoverpairing");
                $("colgroup").eq($(this).index()).removeClass("hoverpairing");
            }
        });
        $(".show-age").tooltip();
    </r:script>
</head>

<body>

<div class="navbar" role="navigation">
    <div class="navbar-inner">
        <ul class="nav">
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="list" action="list"><g:message code="default.list.label"
                                                              args="[entityName]"/></g:link></li>
            <li><g:link class="list" controller="coder" action="list"><g:message code="coder.list.label"
                                                                                 args="[entityName]"/></g:link></li>
        </ul>
    </div>
</div>

<div id="show-team" class="content scaffold-show" role="main">

    <table class="table">
        <g:each in="${coders}" var="c">
            <colgroup></colgroup>
        </g:each>
        <g:each in="${coders}" var="coder" status="row">
            <tr>
                <g:if test="${row > 0}">
                    <g:each in="${1..row}">
                        <td class="pairing"></td>
                    </g:each>
                </g:if>
                <td class="coder">${coder?.name?.encodeAsHTML()}</td>
            </tr>
        </g:each>
    </table>
</div>

<div id="testtext"></div>

</body>
</html>
