if(sessionStorage.length == 0){
    document.getElementById("logout").click();
}
else if(sessionStorage.getItem("roleId") != 2){
    document.getElementById("logout").click();
}

const url = "http://localhost:8090/" 

console.log("HELLO");
window.onload = getAllRequests;

let ids = [];

async function getAllRequests(){


    let response = await fetch(url + "allReimbursements", {
        method: "GET",
        credentials: "include"
    });

    console.log(response);

    if(response.status === 200) {

        let data = await response.json();
        console.log(data);
        let pDiv = document.createElement("div");
        pDiv.className = "row";
        pDiv.innerHTML = "<div class=\"col-sm-12\"><h3>Pending Requests</h3></div>";
        let aDiv = document.createElement("div");
        aDiv.className = "row";
        aDiv.innerHTML = "<div class=\"col-sm-12\"><h3>Approved Requests</h3>";
        let dDiv = document.createElement("div");
        dDiv.className = "row";
        dDiv.innerHTML = "<div class=\"col-sm-12\"><h3>Denied Requests</h3>"
        for(let request of data){
            console.log(request);
            let d1 = document.createElement("div");
            d1.className = "col-sm-12 d-flex p-2 justify-content-center card-size";
            let d2 = document.createElement("div");
            d2.className = "request-card shadow card semi-transparent-card";
            let img = document.createElement("img");
            img.src = "header.jpg"
            img.className = "card-img-top";
            let card = document.createElement("div");
            card.className = "card-body text-dark";
            card.innerHTML = "<b>Amount: $" + request.amount + 
                            "</b><br>Request Type: " + request.type.typeName +
                            "<br>Submitted Date: " + request.submit_date;
            if(request.resolved_date){
                card.innerHTML += "<br>Resolved Date: " + request.resolved_date;
            }
            card.innerHTML += "<details><summary>Description</summary>" + request.description + "</details>";
            let item = document.createElement("div");
            if(request.status.statusName == "approved"){
                item.innerHTML = "<div class=\"form-group buffer\">" +
                                "<label for=\"status\"><b>Change Status: </b></label>" +
                                "<select class = \"dropdown form-control\" id=\"status" + request.id + "\" name=\"status\">"+
                                "<option value=\"pending\">Pending</option>" +
                                "<option value=\"approved\" selected hidden>Approved</option>" +
                                "<option value=\"denied\">Denied</option>" +
                                "</select></div>";
            }
            else if(request.status.statusName == "denied"){
                item.innerHTML = "<div class=\"form-group buffer\">" +
                                "<label for=\"status\"><b>Change Status: </b></label>" +
                                "<select class = \"dropdown form-control\" id=\"status" + request.id + "\" name=\"status\">"+
                                "<option value=\"pending\">Pending</option>" +
                                "<option value=\"approved\">Approved</option>" +
                                "<option value=\"denied\" selected hidden>Denied</option>" +
                                "</select></div>";
            }
            else if(request.status.statusName == "pending"){
                item.innerHTML = "<div class=\"form-group buffer\">" +
                                "<label for=\"status\"><b>Change Status: </b></label>" +
                                "<select class = \"dropdown form-control\" id=\"status" + request.id + "\" name=\"status\">"+
                                "<option value=\"pending\" selected hidden>Pending</option>" +
                                "<option value=\"approved\">Approved</option>" +
                                "<option value=\"denied\">Denied</option>" +
                                "</select></div>";
            }
            card.appendChild(item);
            item = document.createElement("div");
            item.className = "form-group buffer";
            item.innerHTML =  "<button class=\"form-control btn my-button\" id=\"change" + request.id + "\">Submit Change</button>";
            card.appendChild(item);
            d2.appendChild(img);
            d2.appendChild(card);
            d1.appendChild(d2);
            if(request.status.statusName == "pending"){
                pDiv.appendChild(d1);
            }
            else if(request.status.statusName == "approved") {
                aDiv.appendChild(d1);
            }
            else{
                dDiv.appendChild(d1);
            }
            ids.push(request.id);
        }
        console.log("This:" + pDiv.innerText);
        if(pDiv.innerText == "Pending Requests"){
            let iDiv = document.createElement("div");
            iDiv.className = "tab";
            iDiv.innerHTML = "<h5>No Pending Requests</h5><br>"
            pDiv.appendChild(iDiv);
        }
        if(aDiv.innerText == "Approved Requests"){
            let iDiv = document.createElement("div");
            iDiv.className = "tab";
            iDiv.innerHTML = "<h5>No Approved Requests</h5><br>"
            aDiv.appendChild(iDiv);
        }
        if(dDiv.innerText == "Denied Requests"){
            let iDiv = document.createElement("div");
            iDiv.className = "tab";
            iDiv.innerHTML = "<h5>No Denied Requests</h5><br>"
            dDiv.appendChild(iDiv);
        }
        document.getElementById("requests").appendChild(pDiv);
        document.getElementById("requests").appendChild(aDiv);
        document.getElementById("requests").appendChild(dDiv);

    }
    trythis();
}
function trythis(){
    for(let i = 0; i < ids.length; i++){
        console.log(i);
        document.getElementById("change"+ids[i]).addEventListener("click",changeStatus);
    }
}

trythis();

async function changeStatus(){
    let id = this.id[this.id.length-1];
    let r = {
        rId: id,
        userId: Number(sessionStorage.getItem("userId")),
        status: document.getElementById("status"+id).value
    };

    console.log(r);
    let response = await fetch(url + "changeStatus", {
        method: "POST",
        body: JSON.stringify(r),
        credentials: "include"
    });

    if(response.status === 200) {
        location.reload();

    }
    else{
        document.getElementById("change"+id).innerHTML = "<h5 class=\"buffer text-center\">Something went wrong. Please try again.</h5>";
    }
}