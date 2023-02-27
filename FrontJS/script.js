//Método POST
function postAnimals(){  
  fetch('http://localhost:8080/animals', {
      method: 'POST',
      headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    name: document.getElementById("nameAnimal").value,
    specie: document.getElementById("specieAnimal").value,
    breed: document.getElementById("breedAnimal").value,
    height: document.getElementById("heightAnimal").value,
    peso: document.getElementById("pesoAnimal").value,
    treatment: document.getElementById("treatmentAnimal").value
  })
})
  .then(resp => console.log(resp.json()))
  .then(getAnimals)
}

//Método GET
let animalsList = document.getElementById("data-set");
function getAnimals(){
    fetch('http://localhost:8080/animals')
    .then(response => response.json())
    .then((animals) => animals.forEach((animal) => {
            animalsList.innerHTML +=
            `
            <tr>
                <td>${animal.name}</td>
                <td>${animal.specie}</td>
                <td>${animal.breed}</td>
                <td>${animal.height}</td>
                <td>${animal.peso}</td>
                <td>${animal.treatment}</td>
                <td><button onClick="deleteById(${animal.id})"><i class="bi bi-pencil-fill"></i></button></td>
                <td><button onClick="openModal(${animal.id})" data-bs-toggle="modal" data-bs-target="#updateAnimal"><i class="bi bi-x"></i></button></td>
            <tr>
            `
        })
    )
}       

//Método DELETE
function deleteById(id){
    fetch(`http://localhost:8080/animals/${id}`, {
        method: 'DELETE'
    })
    .then(response => response.json())
    .then(getAnimals)

}

//Método PUT
function openModal(id){
  fetch(`http://localhost:8080/animals/${id}`)
  .then((response) => response.json())
  .then((animal) => {
    document.getElementById("nameAnimal").value = animal.name;
    document.getElementById("specieAnimal").value = animal.specie
    document.getElementById("breedAnimal").value = animal.breed
    document.getElementById("heightAnimal").value = animal.height
    document.getElementById("pesoAnimal").value = animal.peso
    document.getElementById("treatmentAnimal").value = animal.treatment
    document.getElementById("putAnimal").setAttribute(onClick, `editById('${animal.id}')`)
  })
}

function editById(id){
  fetch(`http://localhost:8080/animals/${id}`, {
      method: 'PUT',
      headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    name: document.getElementById("nameAnimal").value,
    specie: document.getElementById("specieAnimal").value,
    breed: document.getElementById("breedAnimal").value,
    height: document.getElementById("heightAnimal").value,
    peso: document.getElementById("pesoAnimal").value,
    treatment: document.getElementById("treatmentAnimal").value
  })
})
  .then(resp => console.log(resp.json()))
  .then(getAnimals)
}
