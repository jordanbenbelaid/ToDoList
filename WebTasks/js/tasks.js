`use strict`

function expandUpdate() {
    var checkBox = document.getElementById("updateCheck");
    var fields = document.getElementById("hiddenData");

    console.log("This is not working");
    console.log(fields);
    console.log(checkBox);

    if (checkBox.checked == true) {
        fields.style.display = "block";
    } else {
        fields.style.display = "none";
    }
}


function expandUpdateOption() {
    var checkBox = document.getElementById("ucheck");
    var fields = document.getElementById("hide");

    console.log("This is not working");
    console.log(fields);
    console.log(checkBox);

    if (checkBox.checked == true) {
        fields.style.display = "block";
    } else {
        fields.style.display = "none";
    }
}

function expandCreate() {
    var checkBox = document.getElementById("check");
    var fields = document.getElementById("hiddenStuff");

    console.log("This is not working");
    console.log(fields);
    console.log(checkBox);

    if (checkBox.checked == true) {
        fields.style.display = "block";
    } else {
        fields.style.display = "none";
    }
}

const getRequest = () => {

    fetch("http://localhost:8080/task").then(response => {
        console.log(response);
        if (response.status !== 200) {
            console.error(`status: ${response.status}`)
            return;
        }
        response.json()
            .then(useResponseData => {
                console.log(useResponseData);

                let list = document.querySelector(".getData");

                let currentP = list.querySelector("p");

                if (currentP != null) {
                    currentP.remove();
                }

                let p = document.createElement("p");
                p.innerText = JSON.stringify(useResponseData);
                list.append(p);

            }).catch(err => console.log("You have an error" + err));
    })
}

const postRequest = () => {
    let taskname = document.querySelector("#taskname").value
    console.log(taskname);

    let length = document.querySelector("#length").value;
    console.log(length);

    let difficulty = document.querySelector("#difficulty").value;
    console.log(difficulty);

    let description = document.querySelector("#description").value;
    console.log(description);

    let urgency = document.querySelector("#idcheck").value;
    console.log(urgency);

    const obj =
    {
        "name": taskname,
        "length": length,
        "difficulty": difficulty,
        "description": description,
        "urgency":
        {
            "id": urgency
        }
    }

    fetch("http://localhost:8080/task", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(obj)
    })
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(err => console.err(err))
}

const updateRequest = () => {

    let id = document.querySelector("#taskid").value;

    let taskname = document.querySelector("#utaskname").value
    console.log(taskname);

    let length = document.querySelector("#ulength").value;
    console.log(length);

    let difficulty = document.querySelector("#udifficulty").value;
    console.log(difficulty);

    let description = document.querySelector("#udescription").value;
    console.log(description);

    let urgency = document.querySelector("#uidcheck").value;
    console.log(urgency);

    const upObj =
    {
        "name": taskname,
        "length": length,
        "difficulty": difficulty,
        "description": description,
        "urgency":
        {
            "id": urgency
        }
    }

    fetch("http://localhost:8080/task/update/" + id, {
        method: 'PUT',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(upObj)
    })
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(err => console.error(err))
}

const deleteRequest = () => {

    let id = document.querySelector("#delIdCheck").value;

    fetch("http://localhost:8080/task/delete/" + id, {
        method: 'DELETE'
    })
        .then(response => response.json()
            .then(json => { return json; })
        );
}