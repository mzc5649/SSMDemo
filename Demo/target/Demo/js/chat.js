var nowUserName=null;
    $.ajax(
        {
            async:false,
            url:"getUserInfo",
            type:"post",
            dataType:"json",
            success:function (data) {
                nowUserName=data.username;
                $('#user').text(nowUserName);
            },
            error:function (e) {
                alert('请先登录');
            }

        }
    );
var ws=null;
if ('WebSocket' in window) {
    ws= new WebSocket("ws://106.75.96.134:80/Demo/websocket/"+nowUserName);
} else {
    alert("对不起！你的浏览器不支持webSocket")
}
ws.onopen = function(evt) {
    $('#textarea').append("<div align='center'>连接服务器成功</div>");
};

ws.onmessage = function(evt) {
    $('#textarea').append(evt.data);
};

ws.onclose = function(evt) {
    $('#textarea').append("<div align='center'>断开与服务器的连接</div>");
};
window.onbeforeunload = function () {
    var is = confirm("确定关闭窗口？");
    if (is){
        ws.close();
    }
};

function sendUserMsg(){
    var msg=$('#userMsg').val();
    if(msg==""||msg==null){
        alert('发送的消息不能为空');
        return;
    }else{
        setInnerHtml(msg);
        $('#userMsg').val('');
    }


}
function cleanTextarea() {
    $('#textarea').text('');
}
function setInnerHtml(msg){
    ws.send(msg);
}
