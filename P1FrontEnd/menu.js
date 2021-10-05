if(sessionStorage.getItem("roleId") == 2){
    let s = "<a href=\"newReimbursement.html\">New Request</a><span>   |   </span><a href=\"reviewRequest.html\">Review Requests</a><span>   |   </span><a id=\"logout\"href=\"Login.html\">Logout</a>"
    document.getElementById("heading").innerHTML += s;
}
else{
    let s = "<a href=\"newReimbursement.html\">New Request</a><span>   |   </span><a id=\"logout\"href=\"Login.html\">Logout</a>"
    document.getElementById("heading").innerHTML += s;
}