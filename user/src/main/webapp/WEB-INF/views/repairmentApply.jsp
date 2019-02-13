<%--
  Created by IntelliJ IDEA.
  User: 程鹏
  Date: 2019/2/12
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请保修</title>
    <link rel="stylesheet" type="text/css" href="/user/css/repairmentApply.css">
    <script type="text/javascript" src="/user/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/user/js/repairmentApply.js"></script>
</head>
<body>
    <div class="form">
        <div class="step">
            <label class="name">身份</label>
            <div class="select-view">
                <select class="select">
                    <option class="option default" value="-1">请选择</option>
                    <option class="option" value="0">教师</option>
                    <option class="option" value="1">学生</option>
                </select>
            </div>
            <a class="link" href="javascript:;" title="如何查看我的身份"><img src="/user/images/questionMark.png">如何查看我的身份</a>
        </div>
    </div>
</body>
</html>