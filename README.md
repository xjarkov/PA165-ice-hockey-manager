# Ice Hockey Manager

## Description

Several human players (at least two) can manage their hockey teams out of a list of real ice hockey teams of several championships across Europe. Human players can pick their team and add / remove ice hockey players from a list of available free agents. There is a schedule of games and results will be generated taking into account the players characteristics (no need to have some advanced algorithm simulating games: just a simple randomization will do it!). Admin can put new hockey players in the main list of free agents and change their attributes before they are selected by other human players. If you want, you can implement a budget system for each team, so that players can be bought and sold based on the financial availability of teams.

## REST API endpoints
### Get all hockey players
```
curl -i -X GET http://localhost:8080/pa165/hockey_players
```
### Get all teams
```
curl -i -X GET http://localhost:8080/pa165/teams
```
### Get team by id
```
curl -i -X GET http://localhost:8080/pa165/team/{id}
```
### Get all users
```
curl -i -X GET http://localhost:8080/pa165/users
```
### Get user by id
```
curl -i -X GET http://localhost:8080/pa165/user/{id}
```
### Remove user by id
```
curl -i -X DELETE http://localhost:8080/pa165/user/{id}
```
### Get all matches
```
curl -i -X GET http://localhost:8080/pa165/matches
```
### Get match by id
```
curl -i -X GET http://localhost:8080/pa165/match/{id}
```
### Get nearest match
```
curl -i -X GET http://localhost:8080/pa165/match/nearest
```
### Get score of match
```
curl -i -X GET http://localhost:8080/pa165/match/{id}/score
```
### Get winner of match
```
curl -i -X GET http://localhost:8080/pa165/match/{id}/winner
```