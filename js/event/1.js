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

function checkAll() {
    var hobbyCheckboxes = document.getElementsByName("hobby");
    // var i = hobbyCheckboxes[1].checked;
    // alert(typeof(hobbyCheckboxes[0].checked));  //boolean
    // alert(hobbyCheckboxes[0].checked+1); //true+1=2   false+1=1
    // alert(i^(0^1));   // i数值运算时按0，1处理， 同上， 因此可用这种方式取反选
    for (let i = 0; i < hobbyCheckboxes.length; i++) {
        hobbyCheckboxes[i].checked=true;
    }
}
function checkNone() {
    var hobbyCheckboxes = document.getElementsByName("hobby");
    for (let i = 0; i < hobbyCheckboxes.length; i++) {
        hobbyCheckboxes[i].checked=false;
    }
}
function checkReverse() {
    var hobbyCheckboxes = document.getElementsByName("hobby");
    for (let i = 0; i < hobbyCheckboxes.length; i++) {
        hobbyCheckboxes[i].checked=hobbyCheckboxes[i].checked^1;
    }
}