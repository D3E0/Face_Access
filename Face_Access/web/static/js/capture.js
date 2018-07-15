layui.use(['layer'], function () {
    var $ = layui.jquery, layer = layui.layer;

    var video = document.getElementById('video');
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var index;

    document.getElementById('start').addEventListener('click', function (ev) {
        startStreamedVideo();
        console.info("打开摄像头");
    });

    function success(stream) {
        //兼容webkit核心浏览器
        var CompatibleURL = window.URL || window.webkitURL;
        //将视频流设置为video元素的源
        console.log(stream);
        video.srcObject = stream;
        video.play();
        setTimeout(capture, 500);
        index = layer.load(1);
    }

    function capture() {
        context.drawImage(video, 0, 0, 400, 360);
        var data = canvas.toDataURL("image/jpeg");
        // console.info(data);
        $.post(contextPath + '/signIn/process/image', {img: data}, function (obj) {
            layer.close(index);
            stopStreamedVideo();
            var dataObj = eval("(" + obj + ")");
            console.info(dataObj);
            if (dataObj.result === 'success') {
                layer.msg("登陆成功");
                window.location.href = contextPath + '/home';
            } else {
                layer.msg("似乎失败了呢，再试一次吧", {icon: 5});
            }
        })
    }

    function stopStreamedVideo() {
        var stream = video.srcObject;
        if (stream != null) {
            var tracks = stream.getTracks();
            tracks.forEach(function (track) {
                track.stop();
            });
            video.srcObject = null;
        }
    }

    function startStreamedVideo() {
        if (!video.srcObject) {
            if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
                //调用用户媒体设备, 访问摄像头
                getUserMedia({video: {width: 800, height: 600}}, success, error);
            } else {
                alert('不支持访问用户媒体');
            }
        }
    }

    function error(error) {
        console.log('访问用户媒体设备失败');
        alert("访问用户媒体设备失败");
    }

    //访问用户媒体设备的兼容方法
    function getUserMedia(constraints, success, error) {
        if (navigator.mediaDevices.getUserMedia) {
            //最新的标准API
            navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
        } else if (navigator.webkitGetUserMedia) {
            //webkit核心浏览器
            navigator.webkitGetUserMedia(constraints, success, error)
        } else if (navigator.mozGetUserMedia) {
            //firefox浏览器
            navigator.mozGetUserMedia(constraints, success, error);
        } else if (navigator.getUserMedia) {
            //旧版API
            navigator.getUserMedia(constraints, success, error);
        }
    }


});
