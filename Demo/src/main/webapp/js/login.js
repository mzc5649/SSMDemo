
function validate1(){
    var userStr=$("#username").val();
    var pwdStr=$("#password").val();
    if(userStr==null||userStr==""){
        $('#alert-nullusername').fadeIn(500);
        window.setTimeout(function(){
            $('#alert-nullusername').fadeOut(500);},2000);
        return;
    }else if(pwdStr==null||pwdStr=="") {
        $('#alert-nullpassword').fadeIn(500);
        window.setTimeout(function(){
            $('#alert-nullpassword').fadeOut(500);},2000);
        return;
    }else{
        var obj={"username":userStr,"password":pwdStr};
        $.ajax(
            {
                type:"post",
                url:"loginValidate",
                contentType:"application/json;charset=utf-8",
                catch:false,
                dataType:"json",
                data:JSON.stringify(obj),
                success:function (data) {
                    if(data.status=="false"){
                        $('#alert-danger').fadeIn(500);
                        window.setTimeout(function(){
                     $('#alert-danger').fadeOut(500);},2000);
                    }else if(data.status=="true"){
                        $('#alert-success').fadeIn(500);
                        window.setTimeout(function(){
                            window.location.href="toHome";},1000);

                    }
                },
                error: function (e) {
                    alert("error");
                    alert(e.message);
                }
            }
        );
    }

}

function register(){
    var reg_userStr=$("#reg-username").val();
    var reg_pwdStr=$("#reg-password").val();
    if(reg_userStr==null||reg_userStr==""){
        $('#alert-nullusername').fadeIn(500);
        window.setTimeout(function(){
            $('#alert-nullusername').fadeOut(500);},2000);
        return;
    }else if(reg_pwdStr==null||reg_pwdStr=="") {
        $('#alert-nullpassword').fadeIn(500);
        window.setTimeout(function(){
            $('#alert-nullpassword').fadeOut(500);},2000);
        return;
    }else{
       var obj={"reg_username":reg_userStr,"reg_password":reg_pwdStr};
        $.ajax(
            {
                type:"post",
                url:"registerValidate",
                contentType:"application/json;charset=utf-8",
                catch:false,
                dataType:"json",
                data:JSON.stringify(obj),
                success:function (data) {
                    if(data.status=="false"){
                        $('#alert-reg-danger').fadeIn(500);
                        window.setTimeout(function(){
                            $('#alert-reg-danger').fadeOut(500);},2000);
                        $('#reg-username').focus();
                    }else if(data.status=="true"){
                        $('#username').val(reg_userStr);
                        $('#alert-reg-success').fadeIn(500);
                        window.setTimeout(function(){
                            $('#alert-reg-success').fadeOut();
                            backLogin();
                            },2000);
                    }else if(data.status=="exists"){
                        $('#alert-reg-exists').fadeIn(500);
                        window.setTimeout(function(){
                            $('#alert-reg-exists').fadeOut(500);},2000);
                        $('#reg-username').val("");
                        $('#reg-username').focus();
                    }

                },
                error: function (e) {
                    alert("error");
                    alert(e.message);
                }
            }
        );
    }

}
function  goRegister() {
    $('#a_register').removeAttr("href");
    $('#a_login').removeAttr("href");
    $('#l_register').addClass("active");
    $('#l_login').removeClass("active");
    $('#reg-username').val("");
    $('#reg-password').val("");
    $('.form-signin').fadeOut(500);
    window.setTimeout(function(){
        $('.form-register').fadeIn(500);
        $('#a_login').attr("href","javascript:backLogin()");
        },600);


}
function  backLogin() {
    $('#a_register').removeAttr("href");
    $('#a_login').removeAttr("href");
    $('#l_register').removeClass("active");
    $('#l_login').addClass("active");
    $('.form-register').fadeOut(500);
    window.setTimeout(function(){
        $('.form-signin').fadeIn(500);
        $('#a_register').attr("href","javascript:goRegister()");
        },600);


}