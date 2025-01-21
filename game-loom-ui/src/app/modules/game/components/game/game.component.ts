import { Component, Input } from '@angular/core';
import { GameResponse } from 'src/app/services/models';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent {


  private _game: GameResponse = {};
  private _gamePic : string|undefined;
  private _manage: boolean=false;

  @Input()
  set game(value: GameResponse) {
    this._game = value;
  }
  get game(): GameResponse {
    return this._game;
  }
  get gamePic():string|undefined{
    if(this._gamePic){
      return 'data:image/jpg;base64, '+this._gamePic
    }
    return this._gamePic
  }
  get manage(): boolean {
    return this._manage;
  }
  @Input()
  set manage(value: boolean) {
    this._manage = value;
  }


  onShowDetails() {

  }

  onBorrow() {

  }

  onAddToWishlist() {

  }

  onEdit() {

  }

  onShare() {

  }

  onArchive() {

  }
}
