function link(url){
    window.location.href = url;
}
function GET(url){
    let ajax = new XMLHttpRequest();
    ajax.open("GET", "http://localhost:8080"+url, true);
    ajax.onreadystatechange = function() {
        if (ajax.readyState === 4 && ajax.status === 200) {
            console.log("success");
            location.reload();
        }
    };
    ajax.send();
}


clearTips();
function clearTips(){
    let ajax = new XMLHttpRequest();
    ajax.open("GET", "http://localhost:8080/clearTips", true);
    ajax.send();
}


let editBtn = document.querySelectorAll(".edit-button");
let deleteBtn = document.querySelectorAll(".delete-button");
let user_idStr = document.querySelectorAll(".user_id");
for(let i=0;i<deleteBtn.length;i++){
    deleteBtn[i].onclick = function(){
        user_id = user_idStr[i].textContent
        GET("/deleteUser?id="+user_id);
    }
}
for(let i=0;i<editBtn.length;i++){
    editBtn[i].onclick = function(){
        user_id = user_idStr[i].textContent
        link("/editUser?id="+user_id);
    }
}