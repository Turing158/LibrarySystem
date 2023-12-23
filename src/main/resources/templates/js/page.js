function GET(url){
    let ajax = new XMLHttpRequest();
    ajax.open("GET", "http://localhost:8080"+url, true);
    ajax.onreadystatechange = function() {
        if (ajax.readyState === 4 && ajax.status === 200) {
            console.log("success");
        }
    };
    ajax.send();
}
function link(url){
    window.location.href = url;
}
function jump(a){
    let input = document.querySelector(".pagination-input");
    if(input.value != ""){
        let page = input.value;
        link('/'+a+'?page='+page);
    }
}
function first(a,page){
    if(page != 1){
        link('/'+a+'?page=1');
    }
    else{
        alert("已经是第一页了");
    }
}
function previous(a,page){
    if(page != 1){
        link('/'+a+'?page='+(page-1));
    }
    else{
        alert("已经是第一页了");
    }
}
function next(a,page,endPage){
    if(page != endPage){
        link('/'+a+'?page='+(page+1));
    }
    else{
        alert("已经是最后一页了");
    }
}
function end(a,page,endPage){
    if(page != endPage){
        link('/'+a+'?page='+endPage);
    }
    else{
        alert("已经是最后一页了");
    }
}