var aVideo=document.getElementById('video');
var aCanvas=document.getElementById('canvas');
var ctx=aCanvas.getContext('2d');

function startvideo() {
    navigator.getUserMedia  = navigator.getUserMedia ||
        navigator.webkitGetUserMedia ||
        navigator.mozGetUserMedia ||
        navigator.msGetUserMedia;//获取媒体对象（这里指摄像头）
    navigator.getUserMedia({video:true}, gotStream, noStream);//参数1获取用户打开权限；参数二成功打开后调用，并传一个视频流对象，参数三打开失败后调用，传错误信息
    aVideo.play();
}
function gotStream(stream) {
    video.src = URL.createObjectURL(stream);
    video.onerror = function () {
        stream.stop();
    };
    stream.onended = noStream;
}
function noStream(err) {
    alert(err);
}

function takepic() {
    ctx.drawImage(aVideo, 0, 0, 350, 350);
    console.log(1,aCanvas.toDataURL("image/jpeg"));
    $.post("/checkpic", {type: "data", img: aCanvas.toDataURL("image/jpeg")},
        function(data,status){
            alert(data);
        }
    );
}