const url = "http://localhost:8090/" 

window.onload = sessionStorage.clear();
document.getElementById("loginButton").addEventListener("click", loginFunc);

async function loginFunc(){

    console.log("1");
    let usern = document.getElementById("username").value; 
    let userp = document.getElementById("password").value;
    console.log("2");

    let user = {
        username:usern,
        password:userp
    }

    console.log("User: " + user)
    let response = await fetch(url + "Login", {

        method: "POST",
        body: JSON.stringify(user), 
        credentials: "include"

    });
    if(response.status === 200){
        const text = await response.json();
        const vars = JSON.parse(text);
        sessionStorage.setItem('jwt', vars.jwt);
        sessionStorage.setItem('userId', vars.userId);
        sessionStorage.setItem('roleId', vars.roleId);
        console.log("Status: " + response.status);
        if(vars.roleId == 1){
            window.location.replace("HomePage.html");
        }
        else{
            window.location.replace("reviewRequest.html");
        }
    } else {
        document.getElementById("login-row").innerText="Username or password not recognized."
    }

}
