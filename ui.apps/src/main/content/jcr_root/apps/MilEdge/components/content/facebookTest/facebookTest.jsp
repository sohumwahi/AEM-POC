<%--

  testComp component.



--%><%
%><%@include file="/libs/foundation/global.jsp"%><%
%><%@page session="false" %><%
%><%
	// TODO add you code here
%>

  <head>
        <meta charset="UTF-8">
        <title>Example of Auto Loading Bootstrap Modal on Page Load</title>
        <script type="text/javascript">
            $(document).ready(function() {
                    $("#fbregister").on("click", function() {


                        checkLoginState();
                    });
            });
        </script>


    </head>

    <body>
        <h1><li id="status"></li></h1>
        <button id="fbregister" >Welcome</button>

    </body>
