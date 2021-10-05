const url = "http://localhost:8090/";

/*                <div class="form-group buffer">
                    <label for="fname"><b>What's your first name?</b></label>
                    <input class="form-control" type="text" name="fname" required>
                </div>
                <div class="form-group buffer">
                    <label for="lname"><b>What's your last name?</b></label>
                    <input class="form-control" type="text" name="lname" required>
                </div>
                <div class="form-group buffer">
                    <label for="email"><b>What's your email?</b></label>
                    <input class="form-control" type="email" name="email" required>
                </div>
                <div class="form-group buffer">
                    <input type="checkbox" id="manager" name="manager" value="Manager">
                    <label for="manager"> <b>I am a Financial Manager</b></label><br>
                </div>
                <div class="form-group buffer">
                    <button class="form-control btn my-button" id="create">Create Account</button>
                </div> */

document.getElementById("next").onclick = checkUsername;
document.getElementById("create").onclick = createUser;

async function checkUsername(){

    //if result is true, username is already in use
    //if result if false, contine to more info w/ html above.
}