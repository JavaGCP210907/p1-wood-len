const url = "http://localhost:8090/";

document.getElementById("next").onclick = checkUsername;
//document.getElementById("create").onclick = createUser;
let uname;
let password;

async function checkUsername(){
    uname = document.getElementById("uname").value;
    password = document.getElementById("password").value;
    console.log(uname, password);
    let user = {
        username: uname
    };

    let response = await fetch(url + "checkUsername", {
        method: "POST",
        body: JSON.stringify(user), 
        credentials: "include"
    });

    console.log(response);
    if(response.status === 200){
        let data = await response.json();
        console.log(data);
        if(data){
            document.getElementById("new-user-warning").innerText="Username already exsists. Try something else."
            document.getElementById("uname").value = "";
            document.getElementById("password").value = "";
        }
        else{
            console.log("Hello");
            username = document.getElementById("uname");
            password = document.getElementById("password");
            document.getElementById("createBlock").innerHTML = 
                "<div class=\"form-group buffer\">"+
                "<label for=\"fname\"><b>What's your first name?</b></label>"+
                "<input class=\"form-control\" type=\"text\" name=\"fname\" required> </div>" +
                "<div class=\"form-group buffer\">"+
                "<label for=\"lname\"><b>What's your last name?</b></label>"+
                "<input class=\"form-control\" type=\"text\" name=\"lname\" required></div>"
                "<div class=\"form-group buffer\">" +
                "<label for=\"email\"><b>What's your email?</b></label>" +
                "<input class=\"form-control\" type=\"email\" name=\"email\" required></div>"+
                "<div class=\"form-group buffer\">" +
                "<input type=\"checkbox\" id=\"manager\" name=\"manager\" value=\"Manager\">" +
                "<label for=\"manager\"> <b>I am a Financial Manager</b></label><br> </div>"+
                "<div class=\"form-group buffer\">" +
                "<button class=\"form-control btn my-button\" id=\"create\">Create Account</button></div>"
        }
    }else{
        console.log(response.status);
    }
    //if result is true, username is already in use
    //if result if false, contine to more info w/ html above.
}

async function createUser(){

}