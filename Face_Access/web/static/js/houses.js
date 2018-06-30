layui.use(['jquery', 'laypage', 'table', 'layer', 'element'], function () {
    var $ = layui.$, laypage = layui.laypage
        , table = layui.table, layer = layui.layer
        , element = layui.element;
    layer.load();
    var tableIns=table.render({
        elem: '#houseTable'
        , url: '/housesjson'
        , page: true
        , cols: [[
            {field: 'houseId', title: '房间号', align: "center"}
            // ,{field: 'userId', title: '业主ID', align: "center"}
            , {field: 'username', title: '业主名字', align: "center"}
            // , {field: 'doorId', title: '门号', align: "center"}
            , {field: 'doorlocation', title: '门的位置', align: "center"}

            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolBar'}
        ]],
        done:function(res, curr, count){
            layer.closeAll('loading');
        }
    });
    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(houseTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') { //删除
            layer.open({
                content: '<div style="text-align: center">确定要删除该门禁吗？</div>'
                , btn: ['取消', '删除'] //按钮
                , btnAlign: 'c'
                , yes: function () {
                    layer.msg("取消删除");
                }
                , btn2: function () {
                    layer.msg("确认删除");
                    obj.del();
                    $.post("/delhouse",
                        {
                            houseid:data.houseId
                        },
                        function(data,status){
                            // layer.msg(data);
                        });
                }
            });
        } else if (layEvent === 'edit') { //编辑;
            layer.open({
                type: 2,
                content: ['/updatehouseview?houseid=' + data.houseId+'&userid='+data.userId+'&doorid='+data.doorId, 'no'],
                title: "修改信息",
                shade: 0,
                btn: ['确认', '取消'],
                area: ['500', '580'],
                btnAlign: 'c',
                id: 'first',
                resize: false,
                yes: function (index, layero) {
                    var test = $('#' + layero.find('iframe')[0]['name']).get(0);
                    var doc = test.contentDocument;
                    var id = $("#houseid", doc).val();
                    var doorid = $("#doorid", doc).val();
                    var userid = $("#userid", doc).val();
                    var password = $("#password", doc).val();
                    $.post("/updatehouse",
                        {
                            "Content-Type:text/html;charset":"utf8",
                            houseid:id,
                            doorid:doorid,
                            userid: userid,
                            housepassword:password
                        },
                        function(data,status){
                            if(data=='fail'){
                                layer.msg("密码错误");
                                return false;
                            }
                            layer.close(index); //如果设定了yes回调，需进行手工关闭
                        });
                    // layer.msg('修改成功'+ip);
                }
            })
        }
    });

    $("#add").click(function () {
        layer.open({
            type: 2,
            content: ['/addhouseview', 'no'],
            title: '添加房间',
            btn: ['确认', '取消'],
            area: ['500', '580'],
            resize: false,
            btnAlign:'c',
            shade: 0,
            id: "second",
            yes: function (index, layero) {
                var test = $('#' + layero.find('iframe')[0]['name']).get(0);
                var doc = test.contentDocument;
                var id = $("#houseid", doc).val();
                var doorid = $("#doorid", doc).val();
                var userid = $("#userid", doc).val();
                var password = $("#password", doc).val();
                $("#houseid", doc).removeClass("wrong");
                if (!isValidID(id)) {
                    layer.msg("请输入正确的房间号");
                    $("#houseid", doc).addClass("wrong");
                    $( "#houseid", doc ).effect( 'shake', 500 );
                    return false
                }
                if (!isValidID(doorid)) {
                    layer.msg("请输入正确的门号");
                    return false
                }
                if (!isValidID(userid)) {
                    layer.msg("请输入正确的用户账号");
                    return false
                }
                // if (!isValidpwd(password)) {
                //     layer.msg("密码必须为6-18位字母、数字、特殊符号的");
                //     return false
                // }
                $.post("/addhouse",
                    {
                        "Content-Type:text/html;charset":"utf8",
                        houseid:id,
                        doorid:doorid,
                        userid: userid,
                        housepassword:password
                    },
                    function(data,status){
                        if(data=='wronguid'){
                            layer.msg("用户的id不存在");
                            return false;
                        }
                        if(data=='wrongdid'){
                            layer.msg("门号不存在");
                            return false;
                        }
                        if(data=='fail'){
                            layer.msg("房间号已存在");
                            return false;
                        }
                        layer.close(index); //如果设定了yes回调，需进行手工关闭
                    });
            }
        });
    });
    $("#searchbtn").click(function () {
        var txt=$("#searchtxt").val();
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                keyword:txt
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });
    var lastTime;
    $("#searchtxt").keyup(function (event) {
        lastTime = event.timeStamp;
        setTimeout(function(){
            if(lastTime - event.timeStamp == 0){
                var txt=$("#searchtxt").val();
                tableIns.reload({
                    where: { //设定异步数据接口的额外参数，任意设
                        keyword:txt
                    }
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }
        },100);
    });
});
function isValidID(id)
{
    var reg =  /^\d{1,5}$/;
    return reg.test(id);
}
function isValidpwd(id)
{
    var reg =  /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,18}$/;
    return reg.test(id);
}

