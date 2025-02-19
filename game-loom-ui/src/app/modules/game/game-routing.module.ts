import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import * as path from 'path';
import { MainComponent } from './pages/main/main.component';
import { GamesListComponent } from './pages/games-list/games-list.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {  
        path: 'games',
        component: GamesListComponent,

      }]
}
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameRoutingModule { }
