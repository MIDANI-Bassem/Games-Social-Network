import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PageResponseGameResponse } from 'src/app/services/models';
import { GameService } from 'src/app/services/services';

@Component({
  selector: 'app-games-list',
  templateUrl: './games-list.component.html',
  styleUrls: ['./games-list.component.scss']
})
export class GamesListComponent implements OnInit {
  gamesResponse:PageResponseGameResponse = {};

  constructor(
    private gameService: GameService,
    private router: Router
  ) { }

  ngOnInit(): void {
      this.getGames();
  }
  getGames() {
    this.gameService.findAllGames({
    }).subscribe({
      next: (res) => {
        console.log(res);
        this.gamesResponse = res;

      },
      error: (err) => {
        console.error(err);
      }
    });
  }
  
  

}
