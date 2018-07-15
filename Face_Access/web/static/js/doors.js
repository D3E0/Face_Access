layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
    var $ = layui.$, laypage = layui.laypage
        , table = layui.table, layer = layui.layer
        , element = layui.element;
    layer.load();
    var tableIns=table.render({
        elem: '#doorTable'
        , url: contextPath + '/doorsjson'
        , page: true
        , cols: [[
            {field: 'Id', title: '门号', align: "center"}
            , {field: 'Location', title: '位置', align: "center"}
            , {field: 'Status', title: '状态', align: "center"}
            , {field: 'Ip', title: 'ip地址', align: "center"}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolBar'}
        ]],
        done:function(res, curr, count){
            layer.closeAll('loading');
        }
    });
    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(doorTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') { //删除
            layer.open({
                content: '<div style="text-align: center">确定要删除该门禁吗？</div>'
                , btn: ['取消', '删除'] //按钮
                , btnAlign: 'c'
                , height:'500px'
                , yes: function () {
                    layer.msg("取消删除");
                }
                , btn2: function () {
                    layer.msg("确认删除");
                    obj.del();
                    $.post(contextPath +"/deldoor",
                        {
                            id:data.Id
                        },
                        function(data,status){
                            layer.msg(data);
                        });
                }
            });
        } else if (layEvent === 'edit') { //编辑;
            layer.open({
                type: 2,
                content: [contextPath + '/updatedoorview?id=' + data.Id+ '&location=' + data.Location+ '&ip=' + data.Ip+ '&status=' + data.Status, 'no'],
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
                    var id = $("#id", doc).val();
                    var location = $("#location", doc).val();
                    var ip = $("#ip", doc).val();
                    var status = $("#status", doc).val();
                    if (!isValidID(id)) {
                        layer.msg("请输入正确的id");
                        return false
                    }
                    if (!isValidIP(ip)) {
                        layer.msg("请输入正确的ip地址");
                        return false
                    }
                    $.post(contextPath + "/updatedoor",
                        {
                            "Content-Type:text/html;charset":"utf8",
                            id:id,
                            location:location,
                            ip: ip,
                            status:status
                        },
                        function(data,status){
                            layer.msg(data);
                        });
                    obj.update({
                        Location: location,
                        Ip:ip,
                        Status:status
                    });
                    // layer.msg('修改成功'+ip);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                }
            })
        }
    });

    $("#add").click(function () {
        layer.open({
            type: 2,
            content: [contextPath + '/adddoorview', 'no'],
            title: '添加门禁',
            btn: ['确认', '取消'],
            area: ['500', '580'],
            resize: false,
            btnAlign:'c',
            shade: 0,
            id: "second",
            yes: function (index, layero) {
                var test = $('#' + layero.find('iframe')[0]['name']).get(0);
                var doc = test.contentDocument;
                var id = $("#id", doc).val();
                var location = $("#location", doc).val();
                var ip = $("#ip", doc).val();
                var status = $("#status", doc).val();
                if (!isValidID(id)) {
                    layer.msg("请输入正确的id");
                    return false;
                }
                if (!isValidIP(ip)) {
                    layer.msg("请输入正确的ip地址");
                    return false;
                }
                $.post(contextPath + "/adddoor",
                    {
                        "Content-Type:text/html;charset":"utf8",
                        id:id,
                        location:location,
                        ip: ip,
                        status:status
                    },
                    function(data,status){
                         layer.msg(data);
                    });
                layer.close(index); //如果设定了yes回调，需进行手工关闭
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
function isValidIP(ip)
{
    var reg =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
    return reg.test(ip);
}
function isValidID(id)
{
    var reg =  /^\d{1,5}$/;
    return reg.test(id);
}

