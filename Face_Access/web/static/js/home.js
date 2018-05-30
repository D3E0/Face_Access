layui.use(['jquery', 'layer', 'element'], function () {
    var $ = layui.$, layer = layui.layer, element = layui.element;
    var h = $(window).height() - 60;
    var w = $(window).width() - 200;
    var index = layer.open({
        type: 2,
        content: '/users',
        title: false,
        closeBtn: 0,
        shade: 0,
        offset: ['60px', '200px'],
        area: [w + 'px', h + 'px']
    });

    //监听侧边导航栏点击
    element.on('nav(sidenav)', function (obj) {
        var event = $(obj).attr('lay-event');
        console.info(index + " " + event);
        layer.iframeSrc(index, event)
    });

    $('#quit').click(function () {
        layer.msg("用户退出");
    });

    //监听顶部导航栏点击
    $('#profile').click(function () {
        layer.iframeSrc(index, '/user?id=2');
        // layer.msg("用户退出");
    });

});