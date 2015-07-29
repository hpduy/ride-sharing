<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: hpduy17
  Date: 1/21/15
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"/>
</head>
<body class="skin-blue">
<jsp:include page="header.jsp"/>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="left-side.jsp"/>
    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Home
                <small>it all starts here</small>
            </h1>
            <ol class="breadcrumb">
                <li class="active"><i class="fa fa-dashboard"></i> Home</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class='row'>
                <div class='col-md-12'>
                    <div class='box box-success'>
                        <div class='box-header'>
                            <h3 class='box-title'>Angel Active Code Generator</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class='box-body pad' align="center">
                            <div class="form-group has-success">
                                <label class="control-label" for="numberOfCode"><i class="fa fa-check"></i>Number of
                                    code:</label>
                                <input type="text" class="form-control" id="numberOfCode" placeholder="Enter a number"/>
                            </div>
                            <button type="submit" class="btn btn-success" onclick='generate()'>Generate</button>
                        </div>
                        <div align="center" id="resultDiv">

                        </div>
                    </div>

                </div>
                <!-- /.col-->
            </div>
            <!-- ./row -->
        </section>
        <!-- /.content -->
    </aside>
    <!-- /.right-side -->
</div>
<!-- ./wrapper -->
<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript">
    function generate() {
        var jsonData = '{"numberOfCode":' + document.getElementById('numberOfCode').value + '}';
        var apiName = "GetAngelActiveCodesForWebAPI";
        $.ajax({
            url: apiName,
            data: jsonData,
            type: 'POST',
            error: function () {
                alert("error occured!!!");
            },
            success: function (response) {
                console.log(response);
                $('#resultDiv').html(response)
            }
        });
    }
</script>
</body>
</html>
