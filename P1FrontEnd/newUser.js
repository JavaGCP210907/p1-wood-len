// const url = "http://localhost:8090/" 

// document.getElementById("next").addEventListener("click", checkUsername);//document.getElementById("create").onclick = createUser;

// async function checkUsername(){

//     console.log("1");
//     let usern = document.getElementById("username").value; 
//     let userp = document.getElementById("password").value;
//     console.log("2");

//     let user = {
//         username:usern,
//         password:userp
//     }

//     console.log("User: " + user)
//     let response = await fetch(url + "Login", {

//         method: "POST",
//         body: JSON.stringify(user), 
//         credentials: "include"

//     });

//     console.log(response);
//     if(response.status === 200){
//         let data = await response.json();
//         console.log(data);
//         if(data){
//             document.getElementById("new-user-warning").innerText="Username already exsists. Try something else."
//             document.getElementById("uname").value = "";
//             document.getElementById("password").value = "";
//         }
//         else{
//             console.log("Hello");
//             username = document.getElementById("uname");
//             password = document.getElementById("password");
//             document.getElementById("createBlock").innerHTML = 
//                 "<div class=\"form-group buffer\">"+
//                 "<label for=\"fname\"><b>What's your first name?</b></label>"+
//                 "<input class=\"form-control\" type=\"text\" name=\"fname\" required> </div>" +
//                 "<div class=\"form-group buffer\">"+
//                 "<label for=\"lname\"><b>What's your last name?</b></label>"+
//                 "<input class=\"form-control\" type=\"text\" name=\"lname\" required></div>"
//                 "<div class=\"form-group buffer\">" +
//                 "<label for=\"email\"><b>What's your email?</b></label>" +
//                 "<input class=\"form-control\" type=\"email\" name=\"email\" required></div>"+
//                 "<div class=\"form-group buffer\">" +
//                 "<input type=\"checkbox\" id=\"manager\" name=\"manager\" value=\"Manager\">" +
//                 "<label for=\"manager\"> <b>I am a Financial Manager</b></label><br> </div>"+
//                 "<div class=\"form-group buffer\">" +
//                 "<button class=\"form-control btn my-button\" id=\"create\">Create Account</button></div>"
//         }
//     }else{
//         console.log(response.status);
//     }
//     //if result is true, username is already in use
//     //if result if false, contine to more info w/ html above.
// }

// async function createUser(){

// }

const url = "http://localhost:8090/" 

window.onload = sessionStorage.clear();
document.getElementById("next").addEventListener("click", checkUsername);

let uInfo;

async function checkUsername(){

    console.log("1");
    let usern = document.getElementById("username").value; 
    let userp = document.getElementById("password").value;
    console.log("2");

    let user = {
        username:usern,
        password:userp
    }

    console.log("User: " + user)
    let response = await fetch(url + "checkUsername", {

        method: "POST",
        body: JSON.stringify(user), 
        credentials: "include"

    });
    if(response.status === 200){
        const text = await response.json();
        console.log(text);
        if(text){
            document.getElementById("new-user-warning").innerText="Username already in use.";
        }
        else{
            uInfo = user;
            console.log(uInfo);
            createUser(user);
        }
    } else {
        document.getElementById("new-user-warning").innerText="ERROR"
    }

}

async function createUser(user){
    //String username, String password, String fname, String lname, String email, int roleId
    console.log("1");
    let rid = 1;
    if(document.getElementById("manager")){
        rid = 2;
    }
    let u = {
        username: user.username,
        password: user.password,
        fname: document.getElementById("fname").value,
        lname: document.getElementById("lname").value,
        email: document.getElementById("email").value,
        roleId: rid
    };

    console.log(u);
    let response = await fetch(url + "createUser", {

        method: "POST",
        body: JSON.stringify(u),
        credentials: "include"
    });

    console.log(response);

    if(response.status === 200) {
        console.log(response.status);
        document.getElementById("create-block").innerHTML = "<h5 class=\"buffer text-center\">Request Submitted!</h5>";

    }
    else{
        document.getElementById("create-block").innerHTML = "<h5 class=\"buffer text-center\">Something went wrong. Please try again.</h5>";
    }
}