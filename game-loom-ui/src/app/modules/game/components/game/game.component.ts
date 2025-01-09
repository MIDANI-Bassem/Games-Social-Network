import { Component, Input } from '@angular/core';
import { GameResponse } from 'src/app/services/models';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent {

  private _game: GameResponse = {};

  @Input()
  set game(value: GameResponse) {
    this._game = value;
  }
  get game(): GameResponse {
    return this._game;
  }


}
