const url = "http://localhost:8090/" 

window.onload = sessionStorage.clear();
document.getElementById("loginButton").addEventListener("click", loginFunc);

async function loginFunc(){

    //gather the user input from the login inputs
    console.log("1");
    let usern = document.getElementById("username").value; 
    let userp = document.getElementById("password").value;
    console.log("2");
    //we want to send the user/pass as JSON, so we need to make a JS object to send
    let user = {
        username:usern,
        password:userp
    }

    console.log("User: " + user)

    //Now I'm going to set up my fetch request to the server
    //Remember the second parameter fetch() can take?
    //It's essentially a configuration object! the settings of our fetch request
    //if you don't include it, it'll send a GET request by default
    let response = await fetch(url + "Login", {

        method: "POST", //send a POST request
        body: JSON.stringify(user), //turn our Javascript into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured
        //future fetches will also require this "include" value to send the cookie back
    });
    const text = await response.json();
    const vars = JSON.parse(text);
    sessionStorage.setItem('jwt', vars.jwt);
    sessionStorage.setItem('userId', vars.userId);
    sessionStorage.setItem('roleId', vars.roleId);

    console.log("Status: " + response.status); //useful for debug :)
    
    //control flow based on success or failed login
    if(response.status === 200){
        //wipe our login row and welcome the user
        document.getElementById("login-row").innerText="Welcome!";
        window.location.replace("HomePage.html");
    } else {
        document.getElementById("login-row").innerText="Login failed! Do better."
    }

}
