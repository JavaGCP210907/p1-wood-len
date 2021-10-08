if(sessionStorage.getItem("roleId") == 2){
    let s = "</span></span><a id=\"logout\"href=\"Login.html\">Logout</a>"
    document.getElementById("heading").innerHTML += s;
}
else{
    let s = "<a href=\"HomePage.html\">Home</a><span>   |   <a id=\"review\" href=\"newReimbursement.html\">New Request</a><span>   |   </span><a id=\"logout\"href=\"Login.html\">Logout</a>"
    document.getElementById("heading").innerHTML += s;
}