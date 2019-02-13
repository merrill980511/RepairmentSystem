$(function () {
    //选择选项
    $(".form").on("change",".select",function () {
        $(this).parents(".step").nextAll().remove();
        var selectedOptionId = $(this).find("option:selected").val();
        getNextStep(selectedOptionId);
    });
    //提交信息
    $(".form").on("change",".submit",function () {
        submitSteps();
    });
    //获取下一选项步骤
    function getNextStep(selectedOptionId) {
        if(selectedOptionId != '-1'){
            $.ajax({
                "url": "/nextStep",
                "method": "post",
                "headers": {
                    "Content-Type": "application/json",
                },
                "data": '{\"optionId\":\"'+selectedOptionId+'\"}',
                "dataType": "json",
                "success": function (data) {
                    if(data.step != null){
                        $(".form").append(getStepHtml(data.step));
                    }else{
                        $(".form").append('<input type="button" value="提交" class="submit">');
                    }
                },
                "fail": function () {
                    alert("服务器繁忙，请稍后再试");
                    return false;
                },
            });
        }
    };
    //结果提交
    function submitSteps() {
        $.ajax({
            "url": "/user/submitSteps",
            "method": "post",
            "headers": {
                "Content-Type": "application/json",
            },
            "data": '{\"result\":\"'+getResult()+'\"}',
            "dataType": "json",
            "success": function (data) {
                if(data.message == 'true'){
                    alert("提交成功!");
                }else{
                    alert(data.message);
                }
            },
            "fail": function () {
                alert("服务器繁忙，请稍后再试");
                return false;
            },
        });
    }
    //格式化选项步骤代码
    function getStepHtml(step) {
        var optionsHtml = '';
        for(var i in step.options){
            optionsHtml += '<option class="option" value="'+step.options[i].id+'">'+step.options[i].content+'</option>\n';
        }
        var linkHtml = '<a class="link"></a>';
        if(step.link.content != null&&step.link.content != ''){
            linkHtml += '<a class="link" href="'+step.link.name+'" title="'+step.link.content+'"><img src="../images/questionMark.png">'+step.link.content+'</a>\n';
        }
        var stepHtml = ' <div class="step">\n' +
            '            <label class="name">'+step.content+'</label>\n' +
            '            <div class="select-view">\n' +
            '                <select class="select">\n' +
            '                    <option class="option default" value="-1">请选择</option>\n' +
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
        $(".step").each(function () {
            var name = $(this).find(".name").text();
            var option = $(this).find("option:selected").text();
            result += name+":"+option+";"
        });
        return result;
    };
});