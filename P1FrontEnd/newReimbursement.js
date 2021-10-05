if(sessionStorage.length == 0){
    document.getElementById("logout").click();
}

const url = "http://localhost:8090/" 

document.getElementById("submitRequest").addEventListener("click", createRequest);


async function createRequest(){
    console.log("1");
    let r = {
        userId: Number(sessionStorage.getItem("userId")),
        type: document.getElementById("type").value,
        amount: Number(document.getElementById("amount").value),
        description: document.getElementById("description").value
    };

    console.log(r);
    let response = await fetch(url + "newReimbursement", {

        method: "POST",
        body: JSON.stringify(r),
        credentials: "include"
    });

    console.log(response);

    if(response.status === 200) {
        console.log(response.status);
        document.getElementById("card").innerHTML = "<h5 class=\"buffer text-center\">Request Submitted!</h5>";

    }
    else{
        document.getElementById("card").innerHTML = "<h5 class=\"buffer text-center\">Something went wrong. Please try again.</h5>";
    }
}