`use strict`

// function buttonExpand(){
//     var button = document.getElementById("openDelete");
//     var field = document.getElementById("deleteFunction");

//     console.log("This is not working");
//     console.log(field);
//     console.log(button);

//     if(button.onclick == true){
//         field.style.display = "block";
//     } else {
//         field.style.display = "none";
//     }
// }

function expandCheckbox(){
    var checkBox = document.getElementById("check");
    var fields = document.getElementById("hiddenStuff");

    console.log("This is not working");
    console.log(fields);
    console.log(checkBox);

    if(checkBox.checked == true){
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
            
            if(currentP != null){
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

    let id = document.querySelector("").value;

    fetch("http://localhost:8080/task/update/" + id, {
        method: 'UPDATE'
      })
}

const deleteRequest = () => {

    let id = document.querySelector("#delIdCheck").value;

    fetch("http://localhost:8080/task/delete/" + id, {
        method: 'DELETE'
      })
      .then(response =>response.json()
        .then(json => {return json;})
      );
}