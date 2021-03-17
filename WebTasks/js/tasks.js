`use strict`

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
            let list = document.querySelector(".getList");
            let p = document.createElement("p");
            p.innerText = JSON.stringify(useResponseData);
            list.append(p);
        }).catch(err => console.log("You have an error" + err));
})
}