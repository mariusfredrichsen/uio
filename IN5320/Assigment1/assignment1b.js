var listElementsString = []
var listElementsItem = []
const uListElement = document.getElementById("cl")

function addCountryButton() {
    const inputElement = document.getElementById('ci')
    listElementsString.push(inputElement.value)
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
        listElementsString.splice(listElementsItem.indexOf(listNode), 1)
    }
    button.onmouseenter = () => {
        button.style = "background: red;"
    }
    button.onmouseleave = () => {
        button.style = "background: lightgray;"
    }

    listNode.appendChild(button)

    uListElement.appendChild(listNode)
    listElementsItem.push(listNode)
}

function checkStartOfCountry(country, searchWord) {
    return country.toLowerCase().startsWith(searchWord.toLowerCase())
}

function filterList(list, searchWord) {
    return list.filter((x) => checkStartOfCountry(x, searchWord))
}

function updateList() {
    uListElement.innerHTML = ""
    listElementsItem = []

    const inputText = document.getElementById("cf").value
    const filteredListElement = filterList(listElementsString, inputText)

    for (listElement of listElementsString) {
        if (filteredListElement.includes(listElement)) {
            addCountry(listElement)
        } else {
            listElementsItem.push(null)
        }
    }
    console.log(listElementsItem)
    console.log(listElementsString)
}