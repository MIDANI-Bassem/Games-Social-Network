import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameRoutingModule } from './game-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';
import { GamesListComponent } from './pages/games-list/games-list.component';
import { GameComponent } from './components/game/game.component';


@NgModule({
  declarations: [
    MainComponent,
    MenuComponent,
    GamesListComponent,
    GameComponent
  ],
  imports: [
    CommonModule,
    GameRoutingModule
  ]
})
export class GameModule { }
