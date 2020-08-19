<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*, java.sql.*, javax.servlet.http.*, java.sql.Connection.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link data-require="jqueryui@*" data-semver="1.10.0" rel="stylesheet"
        href="http://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.10.0/css/smoothness/jquery-ui-1.10.0.custom.min.css" />
    <link rel="stylesheet" href="/css/main.css" />
    <link rel="stylesheet" href="/css/bootstrap-select.css" />
    <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script data-require="jqueryui@*" data-semver="1.10.0"
        src="http://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.10.0/jquery-ui.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap-select.js"></script>
    <script src="/js/main.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, shrink-to-fit=no">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7"></script>
</head>

<body>
    <script>
        var tmpArray = new Array();
    </script>
    <% 
    String[] data = (String[])request.getAttribute("mbti_list"); 
    for(int i=0; i<16; i++){
            String tmp = data[i];
    %>
            <script>
                tmpArray.push('<%= tmp%>');
            </script>
    <%
        }
    %>
    <script type="text/javascript">
    
        if ("${isFirst}" == "false") {
            Swal.mixin({
                input: 'text',
                confirmButtonText: 'Next &rarr;',
                showCancelButton: true,
                progressSteps: ['1', '2', '3']
            }).queue([
                {
                    title: "What's ur MBTI?",
                },
                "What's his/her MBTI?",
                "Star rating?",
            ]).then((result) => {
                if (result.value) {
                    var output = JSON.stringify(result.value);
                    var output1 = result.value[0].toUpperCase();
                    var output2 = result.value[1].toUpperCase();
                    var output3 = result.value[2];
                    Swal({
                        title: 'All done!',
                        html:
                            'Your answers: <pre><code>' +
                            output +
                            '</code></pre>',
                        confirmButtonText: 'Good!'
                    });

                    if (tmpArray.includes(output1) && tmpArray.includes(output2) && !isNaN(output3)) {
                        swalCheck(output1, output2, output3);
                    } else {

                        Swal({
                            type: 'error',
                            title: 'Oops...',
                            text: 'Insert Invalid value!',
                            showCancelButton: true,
                            confirmButtonText: 'Insert again!'
                        }).then((result) => {
                            if (result.value) {
                                location.reload();
                            }
                        })

                    }
                }
            })
        }
    </script>
    <div align="center" style="padding-top: 180px;">
        <li style="font-size: 80px; font-weight: bold;">MBTI LAB</li>
    </div>
    <div class="main">
        <div class="search" align="left" style="padding-top: 30px;">
            <select class="selectpicker form-control" data-size="5" data-live-search="true" id="select_form"
                onchange="onClick(this.value,'true');">
                <option value="0" disabled selected> 나의 MBTI</option>
                <c:forEach items="${mbti_list}" varStatus="loop">
                    <option value="${mbti_list[loop.index]}">${mbti_list[loop.index]}</option>
                </c:forEach>
            </select>
            <li style="margin-left: 5px; font-size:16px; font-weight: 500;">는?</li>
        </div> <!-- search-->

        <div class="mbti-boxs" style="padding-top:100px;">
            <div class="mbti-body">
                <c:set var="divcnt" value="0"></c:set>
                <!--개수-->
                <div class="mbti-row">
                    <c:forEach var="item" items="${map}" varStatus="loop">
                        <c:if test="${divcnt != 0 && divcnt%5 == 0}">
                            <!-- 줄바꿈 카운트-->
                </div>
                <div class="mbti-row">
                    </c:if> <!-- 줄바꿈-->
                    <c:if test="${item.key != toMbti}">
                        <!-- 선택한 mbti 제외-->
                        <c:set var="divcnt" value="${divcnt+1}"></c:set>
                        <div class=mbti-box style="text-align: center; margin-block-start: 10px; padding-top:15px;">
                            <li>${item.key}</li> <i class="fa fa-arrow-right"></i>
                            <li>${toMbti}</li></br>
                            <i class="fa fa-star" style="color:gold;"></i>
                            <li style="text-align: center;">${item.value}</li>
                        </div>
                    </c:if>
                    </c:forEach>
                </div>
                <!--mbti-row-->
            </div>
            <!--mbti-body-->
        </div>
        <!--mbti-boxs-->

    </div>
    <!--ㅡmain-->

</body>

</html>