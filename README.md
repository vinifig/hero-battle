# Hero's Battle

> A Rest API developed as example for a Tech Talk

This is a base application, created using [Spring Initializr](https://start.spring.io/) and with the base configuration defined 

## Challenge

Create an Rest API that you could:

* Create and find a `hero` with `name` and `skills(name, power)`
* Create a `hero battle` with `winner hero`, `loser hero`, `timestamp`
    * The `winner hero` is the one with `greater power`, the sum of all `skills power`

You could use this repository as base to develop the application. 
Here, we already have developed the Hero endpoints
    
## Proposal of Domain:
![domain proposal](./docs/domain%20proposal.png)

## Requirements

* Intellij IDEA
* Docker

## Running

Start the docker-compose running this command in: 
```sh
/path/to/project  docker-compose up -d
```

## Challenge Steps

* [Hero Developed](/vinifig/hero-battle)
* [Hero Tested](/vinifig/hero-battle/tree/hero-tested)
* [Battle Developed](/vinifig/hero-battle/tree/battle-developed)
* [Battle Tested](/vinifig/hero-battle/tree/battle-tested)