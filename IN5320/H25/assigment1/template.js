




function addCurrency() {
    const ul = document.getElementById("currencies")
    const inputNode = document.getElementById("inputCurrency")

    const li = document.createElement("li");
    li.textContent = inputNode.value;
    ul.appendChild(li);

    const removeButton = document.createElement("button")
    removeButton.textContent = "X";
    removeButton.onclick = () => {
        ul.removeChild(li);
    };
    li.appendChild(removeButton);

    inputNode.value = ""
}