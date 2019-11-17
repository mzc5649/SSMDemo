$(function(){
    $.ajax(
        {
            url:"getUserInfo",
            type:"post",
            dataType:"json",
            success:function (data) {
                $('#user').text(data.username);
            },
            error:function (e) {
                alert('请先登录');
                window.location.href='login.jsp';
            }

        }

    );
});


