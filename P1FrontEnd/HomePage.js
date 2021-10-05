if(sessionStorage.length == 0){
    document.getElementById("logout").click();
}

const url = "http://localhost:8090/" 

console.log("HELLO");
window.onload = getMyRequests;

async function getMyRequests(){

    let r = {
        userId: sessionStorage.getItem("userId")
    };

    let response = await fetch(url + "HomePage", {

        method: "POST",
        body: JSON.stringify(r),
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

        }
        document.getElementById("requests").appendChild(pDiv);
        document.getElementById("requests").appendChild(aDiv);
        document.getElementById("requests").appendChild(dDiv);

    }

}