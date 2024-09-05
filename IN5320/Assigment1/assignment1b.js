var listElements = []
const uListElement = document.getElementById("cl")

function addCountryButton() {
    const inputElement = document.getElementById('ci')
    listElements.push(inputElement.value)
    inputElement.value = ""
    updateList()
}

function addCountry(country) {
    // create list node
    const listNode = document.createElement("li")
    const textNode = document.createTextNode(country)

    listNode.appendChild(textNode)

    // create button that deletes corresponding list element
    const button = document.createElement("button")
    button.textContent = "X"
    button.onclick = () => {
        textNode.remove()
        button.remove()
        listNode.remove()
        listElements.map((listElement) => listElement === listNode)
    }
    button.onmouseenter = () => {
        button.style = "background: red;"
    }
    button.onmouseleave = () => {
        button.style = "background: lightgray;"
    }

    listNode.appendChild(button)

    uListElement.appendChild(listNode)
}

function checkStartOfCountry(country, searchWord) {
    return country.startsWith(searchWord)
}

function filterList(list, searchWord) {
    return list.filter((x) => checkStartOfCountry(x, searchWord))
}

function updateList() {
    uListElement.innerHTML = ""

    for (listElement of listElements) {
        addCountry(listElement)
    }
}