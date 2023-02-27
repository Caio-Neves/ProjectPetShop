import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Animal } from './animal';
import { AnimalService } from './animal.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  public animals:Animal[] = [];
  public editAnimal:Animal | null | undefined;
  public deleteAnimal:Animal | null | undefined;

  constructor (private animalService: AnimalService){}

  ngOnInit(): void {
      this.getAnimals();
  }

  public getAnimals(): void {
    this.animalService.getAnimals().subscribe(
      (response: Animal[]) => {
        this.animals = response;
        console.log(this.animals);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddAnimal(addForm: NgForm): void{
    this.animalService.addAnimal(addForm.value).subscribe(
      (response: Animal) =>{
        console.log(response);
        this.getAnimals();
        addForm.reset();
      },
      (e: HttpErrorResponse)=>{
        alert(e.message)
      }
    )
  }

  public onUpdateAnimal(animal: Animal): void{
    this.animalService.addAnimal(animal).subscribe(
      (response: Animal) =>{
        console.log(response);
        this.getAnimals();
      },
      (e: HttpErrorResponse)=>{
        alert(e.message)
      }
    )
  }

  public onDeleteAnimal(animalId: number): void{
    this.animalService.deleteAnimal(animalId).subscribe(
      (response: void) =>{
        this.getAnimals();
      },
      (e: HttpErrorResponse)=>{
        alert(e.message)
      }
    )
  }

  public onOpenModal(animal: Animal | null, mode: string): void{
    const container = document.getElementById("container");
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal');
    if(mode === 'add'){
      button.setAttribute('data-target', `#addAnimal`)
    }
    if(mode === 'update'){
      this.editAnimal = animal;
      button.setAttribute('data-target', `#updateAnimal`)
    }
    container?.append(button);
    button.click();
  }
}
