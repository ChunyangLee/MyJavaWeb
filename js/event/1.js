function onloadFunction() {
    alert("onload触发！")
}

function onblurFun(){
    console.log("静态注册失去焦点！")
}

window.onload=function () {
    var formObj=document.getElementById("form01");
    var userNameObj= document.getElementById("user01");
    var userNameSpan = document.getElementById("usernamespan");

    //为表单设置提交事件
    formObj.onsubmit=function () {
        alert(userNameObj.value);
        var value = userNameObj.value
        //用正则表达式处理
        var patt=/^\w{5,12}$/;
        if(patt.test(value)){
            // userNameSpan.innerHTML="用户名合法";
            return true;
        }else{
            alert("请重写输入！")
            // userNameSpan.innerHTML="用户名不合法";
            return false; //return false 则不提交表单
        }
    }
    //为用户名框设置焦点失去事件
    //失去焦点时自动校验合法与否
    userNameObj.onblur=function () {
        var patt=/^\w{5,12}$/;
        if(patt.test(userNameObj.value)){
             userNameSpan.innerHTML="用户名合法";
        }else{
             userNameSpan.innerHTML="用户名不合法";
        }
    }


}