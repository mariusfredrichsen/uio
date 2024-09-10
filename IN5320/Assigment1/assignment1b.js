var listElementsString = []
var listElementsItem = []
const uListElement = document.getElementById("cl")
var countries = []

const countriesURL = "https://d6wn6bmjj722w.population.io/1.0/countries/"
async function getCountries() {
    try {
        const response = await fetch(countriesURL)
        if (!response.ok) {
            throw new Error("Countries fetch error")
        }
        const data = await response.json()
        return data.countries
    } catch (error) {
        console.error("There was an error: ", error)
        return null
    }
}
(async () => {
    countries = await getCountries()
})();

console.log(countries)

function addCountryButton() {
    const inputElement = document.getElementById('ci')
    if (countries.includes(inputElement.value)) {
        fetch("https://d6wn6bmjj722w.population.io/1.0/population/" + inputElement.value + "/today-and-tomorrow/")
        .then((response) => {
            if (!response.ok) {
                throw new Error("failed to fetch country population")
            }
            return response.json()
        })
        .then((data) => {
            listElementsString.push(inputElement.value + " - " + data.total_population[0].population + " ")
            inputElement.value = ""
            updateList()
        })
        hideModalBox()
    } else {
        showModalBox(inputElement.value)
    }
}

function showModalBox(inputError) {
    const modalBox = document.getElementById("mb")
    modalBox.style = "display: block;"
    modalBox.textContent = inputError + " is not a valid input"
}

function hideModalBox() {
    const modalBox = document.getElementById("mb")
    modalBox.style = "display: none;"
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

    for (const listElement of listElementsString) {
        if (filteredListElement.includes(listElement)) {
            addCountry(listElement)
        } else {
            listElementsItem.push(null)
        }
    }
    console.log(listElementsItem)
    console.log(listElementsString)
}