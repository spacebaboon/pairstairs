<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns:ng="http://angularjs.org" ng-app>
<head>
    <title></title>
</head>

<body ng-controller="PairingListCtrl">
<h1>pairings</h1>

<ul class="pairings">
    <li ng-repeat="pairing in pairings">
        <p>Coder {{pairing}}</p>
        %{--<p>{{pairing.coders[0]}} and {{pairing.coders[1]}}</p>--}%
    </li>
</ul>

1 + 2 = {{ 1 + 2 }}

<script src="${resource(dir: 'lib/angular', file: 'angular.js')}"></script>
<script src="${resource(dir: 'js', file: 'controllers.js')}"></script>
</body>
</html>