let ajax = new XMLHttpRequest();
let user = document.querySelector(".user_input")
let pwd = document.querySelector(".pass_input")
let email = document.querySelector(".email_input")
let tips = document.querySelector(".tips");
function checkPwd() {
    if(pwd.value.length > 5){
        tips.style.color = "darkgreen";
        tips.innerHTML = "密码可用";
    }
    else{
        tips.style.color = "darkred";
        tips.innerHTML = "密码长度不得小于6";
    }
}

function Change() {
    var img = document.getElementById("img_code");
    var date = new Date().getTime();
    img.src="jpegCode?"+date;
}