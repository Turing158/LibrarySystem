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

let borrowBtn = document.querySelectorAll(".borrow-button");
let returnBtn = document.querySelectorAll(".return-button");
let editBtn = document.querySelectorAll(".edit-button");
let deleteBtn = document.querySelectorAll(".delete-button");
let book_idStr = document.querySelectorAll(".book_id");
let logDateStr = document.querySelectorAll(".log_date");
for(let i=0;i<borrowBtn.length;i++){
    borrowBtn[i].onclick = function(){
        book_id = book_idStr[i].textContent
        GET("/borrowBook?id="+book_id);
        
    }
}
for(let i=0;i<returnBtn.length;i++){
    returnBtn[i].onclick = function(){
        book_id = book_idStr[i].textContent
        logDate = logDateStr[i].textContent
        GET("/returnBook?id="+book_id+"&date="+logDate);
    }
}
for(let i=0;i<deleteBtn.length;i++){
    deleteBtn[i].onclick = function(){
        book_id = book_idStr[i].textContent
        GET("/deleteBook?id="+book_id);
    }
}
for(let i=0;i<editBtn.length;i++){
    editBtn[i].onclick = function(){
        book_id = book_idStr[i].textContent
        link("/editBookPage?id="+book_id);
    }
}
clearTips();
function clearTips(){
    let ajax = new XMLHttpRequest();
    ajax.open("GET", "http://localhost:8080/clearTips", true);
    ajax.send();
}
