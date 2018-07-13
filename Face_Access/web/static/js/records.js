layui.use(['jquery', 'laypage', 'table', 'layer', 'element', 'laydate'], function () {
    var $ = layui.$
        , table = layui.table, layer = layui.layer
        , element = layui.element;
    layer.load();
    tableIns=table.render({
        elem: '#recordTable'
        , url: '/recordsjson'
        , page: true
        ,limit:10
        ,limits:[5, 10, 20, 30, 40, 50]
        , cols: [[
            // {field: 'Id', title: 'ID', align: "center"}
            // , {field: 'doorId', title: '门的id', align: "center"}
             {field: 'doorLocation', title: '门的位置', align: "center"}
            // , {field: 'userID', title: '用户id', align: "center"}
            , {field: 'userName', title: '用户名', align: "center"}
            , {field: 'openDate', title: '开门日期', align: "center"}
            , {field: 'openResult', title: '结果', align: "center"}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolBar'}
        ]],
        done:function(res, curr, count){
            layer.closeAll('loading');
        }
    });
    //监听工具条 tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    table.on('tool(recordTable)', function (obj) {
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'showpic') {
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                offset: 'auto',
                content: '<img src="/static/images/666.jpg" style="width: 100%;"/>'
            });
        }
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
