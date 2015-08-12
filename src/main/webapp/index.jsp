<%@ page import="com.bikiegang.ridesharing.utilities.FakeGroup.FakeUser" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <title>CLOUD BIKE</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="js/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.css" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript" src="js/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js"></script>
    <script src="//www.parsecdn.com/js/parse-1.0.18.min.js"></script>
</head>
<body>
<div align="center">
    <h1>Welcome to CloudBike Server ! Have a nice day and try your best !!</h1>
</div>
<div>
    <div style="float: left; width: 200px">
        APIName:
    </div>
    <div style="float: none">
        <input type="text" name="apiName" id="apiName" style="width: 500px;">
    </div>
</div>
<div>
    <div style="float: left; width: 200px">
        Insert Your RequestJSON:
    </div>
    <div style="float: none">
        <textarea name="jsonData" id="jsonData" style="width: 500px; height: 100px"></textarea>
    </div>
</div>
<div align="center">
    <input value="GO" title="go" type="button" onclick="testAPI()" style="width: 100px">
</div>
<hr>
<h1>Fake data</h1>
<div align="center">
    <input value="Create tester" title="ctest" type="button" onclick="createTester()" style="width: 100px">
    <input value="Create angel" title="cangel" type="button" onclick="createAngel()" style="width: 100px">
</div>
</body>
<script type="text/javascript">
    function testAPI() {
        var jsonData = document.getElementById('jsonData').value;
        var apiName = document.getElementById('apiName').value;
        $.ajax({
            url: apiName,
            data: jsonData,
            type: 'POST',
            error: function () {
                console.log("error occured!!!");
            },
            success: function (response) {
                alert(response);
                console.log(response);
            }
        });
    }function createTester() {
        <%
            FakeUser.createTester();
        %>
    }
    function createAngel() {
       <%
           FakeUser.createAngel();
       %>
    }
</script>
</html>
