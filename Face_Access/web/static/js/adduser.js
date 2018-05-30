layui.use(['laydate', 'form', 'layer', 'jquery'], function () {
    var laydate = layui.laydate, form = layui.form
        , layer = layui.layer, $ = layui.jquery;

    laydate.render({
        elem: '#startDate'
        , type: 'date'
        , value: (new Date())
        , isInitValue: true
    });

    laydate.render({
        elem: '#endDate'
        , type: 'date'
        , min: 0
        , value: "2018-05-30"
        , isInitValue: true
    });

    form.on('select(userid)', function (data) {
        console.info(data.value);
        $("input[name='username']").val("backname");
        $("input[name='usertel']").val(data.value);

    });

    $("#username").keyup(function () {
        var str = $(this).val();
        var url = '/searchuser?id=' + str;
        console.info(url);
        $("#username").empty();
        // $("#keywords").show();
        $.getJSON(url, function (val) {
            $.each(val.data, function (i, n) {
                $("<option>" + n.name + "</option>").appendTo($("#username"));
                console.info(n.name);
            });
        })
    })


});