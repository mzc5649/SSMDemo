
var usernameStr;
var u_idStr;
var localObj = window.location;// 这个的意思是获取当前页面的地址

var protocol = location.protocol //获取http或https

var host = localObj.host //获取JSP地址栏IP和端口号 //localhost:8080

var contextPath = localObj.pathname.split("/")[1];// 获取项目名

var basePath = protocol +"//"+host+"/"+contextPath;

$('#progress').hide();
$(function(){

    $.ajax(
        {
            url:"getUserInfo",
            type:"post",
            async:false,
            dataType:"json",
            success:function (data) {
                usernameStr=data.username;
                u_idStr=data.id;
                $('#user').text(usernameStr);
                $('#pathStr').append("<a class='breadcrumb-item' href='#'>"+usernameStr+"</a>");
                listAllFile();




                },
            error:function (e) {
                alert('请先登录');
            }

        }

    );

});

function up(){
    var fileInput=$('#fileInput').val();
    if(fileInput==''||fileInput==null){
        alert('请选择文件！');
        return;
    }

    //定时器
    var timeId=window.setInterval(function () {
        $.ajax(
            {   url:"disk/progress",
                type:"get",
                dataType:"json",
                success:function (data){
                    var p=data.percent;
                    $('#percent').text(p);
                    $('#percentBar').css("width",p);
                    if(p=="100.00%"){
                        window.clearInterval(timeId);
                        window.setTimeout(function () {
                            alert('上传成功');
                            $('#percent').text("0%");
                            $('#percentBar').css("width","0%");
                            listAllFile();
                        },1000);
                    }
                }
            }
        );
    },100);

    //显示进度条
    $('#progress').show();
    //上传文件
    var formData=new FormData($('#uploadForm')[0]);
    $.ajax(
        {url:"disk/fileUpload",
            type:"post",
            contentType:false,
            cache:false,
            async:true,
            data:formData,
            dataType:"json",
            processData: false,
            success:function (data){
                $('#fileInput').val('');

            },
            error:function (e) {
                alert('error');
            }

        }

    );





}
var myInter=function myInterval () {

}
function openUpFrame(){
    $('#percent').text("0%");
    $('#percentBar').css("width","0%");
    $('#fileInput').val('');
    $('#progress').hide();
}
function listAllFile(){
    $('#listFileBody').html("");
    $.ajax({
        type:"post",
        url:"file/listFileByUidAndDid",
        dataType:"json",
        success:function (data) {
         $.each(data,function (index) {
            $('#listFileBody').append(
                "<tr>"+
                "<td id='listName'>"+data[index].filename+"</td>"+
                "<td>"+data[index].size+"</td>"+
                "<td>"+data[index].upload_time+"</td>"+
                "<td><button onclick='delFile("+data[index].id+")'  type=\"button\" class=\"btn btn-danger\">删除</button></td>"+
                "<td><button onclick='downloadFile("+data[index].id+")'  type=\"button\" class=\"btn btn-info\">下载</button></td>"+
                "</tr>"
            );

        });

        }
    });
}

function delFile(file_id){
     var s=confirm("确认删除吗?");
        if(s){
            var obj={"file_id":file_id};
            $.ajax({
                url:"file/deleteFile",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:JSON.stringify(obj),
                dataType:"json",
                success:function (data) {
                    if(data.status){
                        alert("删除成功");
                        listAllFile();
                    }else {
                        alert("删除失败");
                        listAllFile();
                    }
                }
            });
        }
}
function downloadFile(file_id){
    var s=confirm("确认下载吗?");
    if(s) {
        var form=$('<form>');
        form.attr('style','none');
        form.attr('target','');
        form.attr('method','post');
        form.attr('action',basePath+'/file/downloadFile');
        var input1=$('<input>');
        input1.attr('type','hidden');
        input1.attr("name","file_id");
        input1.attr("value",file_id);
        $('body').append(form);
        form.append(input1);
        form.submit();
        form.remove();
    }
}




function showMask(){
    $("#mask").css("display","block")
}

function hideMask(){
    $("#mask").hide();
}
function checkName(){
    //input选择文件后判断
        var file= $('#fileInput').val();
        var fileName=file.split("\\").pop();//返回数组最后一个的元素
    //---------------------
    //检查当前列表重名
    $("td").each(function () {
        var name= $(this).text();
        if(fileName==name){
            $('#fileInput').val('');
            alert("文件名在该目录已存在，请重新选择文件");
            return false;
        }
    });
}




