/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { Feedback } from '../models/feedback';
import { GameCategories } from '../models/game-categories';
import { GameTrack } from '../models/game-track';
import { User } from '../models/user';
export interface Game {
  age?: number;
  createdBy?: number;
  creationDate?: string;
  description?: string;
  feedbacks?: Array<Feedback>;
  gameCategories?: Array<GameCategories>;
  gameDesigner?: string;
  gameVersion?: string;
  id?: number;
  image?: string;
  lastUpdateBy?: number;
  lastUpdateDate?: string;
  maxPlayers?: number;
  minPlayers?: number;
  name?: string;
  owner?: User;
  playingTime?: number;
  rate?: number;
  shareable?: boolean;
  transactions?: Array<GameTrack>;
  yearPublished?: string;
}