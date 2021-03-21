`use strict`

const getUrgency = () => {

    fetch("http://localhost:8080/urgency").then(response => {
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

const postUrgency = () => {

    let name = document.querySelector("#urgencyname").value;
    console.log(name);

    const myObj =
    {
        "name": ""
    }
    myObj.name = name;

    fetch("http://localhost:8080/urgency", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(myObj)
    })
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(err => console.error(err))

}

const updateUrgency = () => {

    let id = document.querySelector("#updateList").value;

    let name = document.querySelector("#listName").value;

    const myObj =
    {
        "name": name
    }

    fetch("http://localhost:8080/urgency/update/" + id, {
        method: 'PUT',
        headers: {
            "Content-type": "application/json"
        },
        body:JSON.stringify(myObj)
    })
    .then(res => res.json())
    .then(data => console.log(data))
    .catch(err => console.error(err))
}

const deleteUrgency = () => {

    let id = document.querySelector("#deleteList").value;

    fetch("http://localhost:8080/urgency/delete/" + id, {
        method: 'DELETE'
    })
        .then(response => response.json()
            .then(json => { return json; })
        );
}