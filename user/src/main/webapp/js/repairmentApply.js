$(function () {
    var userID = $("#userID").val();
    pageInit();
    //界面初始化
    function pageInit() {
        if(!infoPanelInit()){
            formInit();
        }
        var user = getUserInfo(userID);
        $("input.phone").val(user.phone);
    }
    //表单初始化
    function formInit(){
        $(".form").html('<div class="step">\n' +
            '            <label class="name">报修地点</label>\n' +
            '            <div class="select-view">\n' +
            '                <input class="select location" placeholder="请输入保修地点" maxlength="250"/>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="step">\n' +
            '            <label class="name">手机号码</label>\n' +
            '            <div class="select-view">\n' +
            '                <input class="select phone" placeholder="请输入手机号码" maxlength="20"/>\n' +
            '            </div>\n' +
            '        </div>');
        $(".infoPanel").hide();
        $(".form").show();
        return getNextStep(-1);
    };
    //信息面板初始化
    function infoPanelInit(){
        $(".infoPanel").html(' <div class="info"><label class="title">申&ensp;请&ensp;人：</label><label class="user"></label></div>\n' +
            '        <div class="info"><label class="title">地&emsp;&emsp;点：</label><label class="location"></label></div>\n' +
            '        <div class="info"><label class="title">电&emsp;&emsp;话：</label><label class="phone"></label></div>\n' +
            '        <div class="info"><label class="title">预约时间：</label><label class="beginTime"></label></div>\n' +
            '        <div class="info"><label class="title">状&emsp;&emsp;态：</label><label class="status"></label></div>\n' +
            '        <div class="info"><label class="title">处&ensp;理&ensp;人：</label><label class="operator"></label></div>\n' +
            '        <div class="info"><label class="title">备&emsp;&emsp;注：</label><label class="userDescription"></label></div>\n'+
            '        <div class="info"><label class="title">报修信息：</label><label class="repairment"></label></div>\n');
        return getOrder(userID);
    };
    //选择选项
    $(".form").on("change","select.select",function () {
        $(this).parents(".step").nextAll().remove();
        var selectedOptionID = $(this).find("option:selected").val();
        getNextStep(selectedOptionID);
    });
    //提交信息
    $(".form").on("click",".submit",function () {
        submitOrder();
    });
    //获取信息面板详细信息
    function getOrder(userID) {
        var getOrder = false;
        if(userID != null && userID != '' && userID != 'undefined'){
            $.ajax({
                "url": "/user/getOrder",
                "async": false ,
                "method": "post",
                "headers": {
                    "Content-Type": "application/json",
                },
                "data": '{\"userID\":\"'+userID+'\"}',
                "dataType": "json",
                "success": function (data) {
                    if(data.id != null){
                        $("label.user").text(data.user.name);
                        $("label.location").text(data.location);
                        $("label.phone").text(data.phone);
                        $("label.beginTime").text(dateLoad(data.beginTime));
                        $("label.status").text(getOrderStatus(data.status));
                        $("label.operator").text(data.operator == null?"":data.operator.name);
                        $("label.repairment").text(data.repairment);
                        $("label.userDescription").text(data.userDescription);
                        $(".form").hide();
                        $(".infoPanel").show();
                        getOrder =  true;
                    }else{

                    }
                },
                "fail": function () {
                    alert("服务器繁忙，请稍后再试");
                },
            });
            return getOrder;
        }
    }
    //获取下一选项步骤
    function getNextStep(selectedOptionID) {
        var getNextStep = false;
        if(selectedOptionID != '-2'){
            $.ajax({
                "url": "/user/nextStep",
                "async": false ,
                "method": "post",
                "headers": {
                    "Content-Type": "application/json",
                },
                "data": '{\"optionID\":\"'+selectedOptionID+'\"}',
                "dataType": "json",
                "success": function (data) {
                    if(data.id != null){
                        $(".form").append(getStepHtml(data));
                    }else{
                        $(".form").append('<div class="step">\n' +
                        '            <label class="name">备注</label>\n' +
                        '            <div class="select-view">\n' +
                        '                <textarea class="select userDescription input" rows="5" placeholder="请输入备注" maxlength="250"></textarea>\n' +
                        '            </div>\n' +
                        '        </div>');
                        $(".form").append('<input type="button" value="提交" class="submit"><br/>' +
                            '<div class="errorMessage"><img src="/user/images/error.png"><label></label></div>');
                    }
                    getNextStep =  true;
                },
                "fail": function () {
                    alert("服务器繁忙，请稍后再试");
                },
            });
            return getNextStep;
        }
    };
    //结果提交
    function submitOrder() {
        var phone = $("input.phone").val();
        var location = $("input.location").val();
        var userDescription = $("input.userDescription").text();
        var submitOrder = false;
        if(!isNotNull(phone)){
            $(".errorMessage>label").text("手机号不能为空！");
            $(".errorMessage").show();
            return false;
        }else if(!isNotNull(location)){
            $(".errorMessage>label").text("报修地点不能为空！");
            $(".errorMessage").show();
            return false;
        }else if(!isPhone(phone)){
            $(".errorMessage>label").text("手机号码格式不正确");
            $(".errorMessage").show();
            return false;
        }else{
            $(".errorMessage>label").text("");
            $(".errorMessage").hide();
            $.ajax({
                "url": "/user/submitOrder",
                "method": "post",
                "headers": {
                    "Content-Type": "application/json",
                },
                "data": '{\"userID\":\"'+userID+'\",\"phone\":\"'+phone+'\",\"location\":\"'+location+'\",\"userDescription\":\"'+userDescription+'\",\"repairment\":\"'+getResult()+'\"}',
                "dataType": "json",
                "success": function (data) {
                    if(data.message == 'true'){
                        pageInit();
                        submitOrder =  true;
                    }else{
                        $(".errorMessage>label").text(data.message);
                        $(".errorMessage").show();
                    }
                },
                "fail": function () {
                    $(".errorMessage>label").text("服务器繁忙，请稍后再试");
                    $(".errorMessage").show();
                },
            });
            return submitOrder;
        }
    }
    //格式化选项步骤代码
    function getStepHtml(step) {
        var optionsHtml = '';
        for(var i in step.options){
            optionsHtml += '<option class="option" value="'+step.options[i].id+'">'+step.options[i].content+'</option>\n';
        }
        var linkHtml = '<a class="link"></a>';
        if(step.link != null &&step.link.content != null&&step.link.content != ''){
            linkHtml += '<a class="link" href="'+step.link.name+'" title="'+step.link.content+'"><img src="../images/questionMark.png">'+step.link.content+'</a>\n';
        }
        var stepHtml = ' <div class="step">\n' +
            '            <label class="name">'+step.content+'</label>\n' +
            '            <div class="select-view">\n' +
            '                <select class="select">\n' +
            '                    <option class="option default" value="-2">请选择</option>\n' +
            optionsHtml+
            '                </select>\n' +
            '            </div>\n' +
            linkHtml+
            '        </div>';
        return stepHtml;
    };
    //格式化选择结果
    function getResult() {
        var result = '';
        var phone = $("input.phone").val();
        var location = $("input.location").val();
        var userDescription = $("input.userDescription").text();
        result += phone+";"+location+";"+userDescription+";";
        $(".step").each(function () {
            var option = $(this).find("option:selected").text();
            if(option != ""&&option != "undefined"){
                result += option+";";
            }
        });
        return result;
    };
});