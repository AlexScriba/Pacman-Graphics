package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Game.Direction;
import Game.Game;
import Game.PacMan;
import Game.Ghost;
import Game.Maze;
import Game.GameObject;

public class GameEngine extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Stage stage;
	
	Skin mySkin;
	
	Game game;
	PacMan pac;
	ArrayList<Ghost> ghosts;
	Maze maze;
	
	float xScale, yScale;

	Texture floor;
	Texture wall;
	Texture food;
	Texture powerUp;
	Texture gate;
	Texture frightenedGhost;
	
	boolean gameOver;
	boolean gameStart;
	
	float pacmanRotation = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		game = Game.getInstance();
		
		// Setup menu buttons
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		mySkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
		
		float buttonWidth = 100;
		float buttonHeight = 50;
		
		float gap = 60;
		
		float centreX = Gdx.graphics.getWidth()/2 - buttonWidth/2;
		float centreY = Gdx.graphics.getHeight()/2;
		
		TextButton easyButton = new TextButton("Easy", mySkin, "small");
		easyButton.setSize(buttonWidth, buttonHeight);
		easyButton.setPosition(centreX, centreY + gap);
		easyButton.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				game.gameInit("Easy", "Chase");
				gameStart = true;
				setupGame();
				return true;
			}
		});
		
		TextButton mediumButton = new TextButton("Medium", mySkin, "small");
		mediumButton.setSize(buttonWidth, buttonHeight);
		mediumButton.setPosition(centreX, centreY);
		mediumButton.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				game.gameInit("Medium", "Chase");
				gameStart = true;
				setupGame();
				return true;
			}
		});
		
		TextButton hardButton = new TextButton("Hard", mySkin, "small");
		hardButton.setSize(buttonWidth, buttonHeight);
		hardButton.setPosition(centreX, centreY - gap);
		hardButton.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				game.gameInit("Hard", "Chase");
				gameStart = true;
				setupGame();
				return true;
			}
		});
		
		stage.addActor(easyButton);
		stage.addActor(mediumButton);
		stage.addActor(hardButton);

		// Load game tiles
		floor = new Texture("Maze Tile.png");
		wall = new Texture("Wall Tile.png");
		food = new Texture("Food.png");
		powerUp = new Texture("PowerUp.png");
		gate = new Texture("Gate Tile.png");
		frightenedGhost = new Texture("Frightened1.png");
		
		gameOver = false;
		gameStart = false;
		
	}
	
	private void setupGame() {
		maze = Maze.getInstance();
		
		// Scales for tile sizes
		xScale = ((float)Gdx.graphics.getWidth()/(float)maze.getN());
		yScale = ((float)Gdx.graphics.getHeight()/(float)maze.getM());
		
		// Get pacman and set texture
		pac = PacMan.getInstance();
		pac.setTexture(new Texture("Pacman2.png"));
		
		// Get ghost textures
		ghosts = Ghost.getGhosts();
		ghosts.get(0).setTexture(new Texture("Red 33.png"));
		ghosts.get(1).setTexture(new Texture("Pink 43.png"));
		ghosts.get(2).setTexture(new Texture("Cyan 33.png"));
		ghosts.get(3).setTexture(new Texture("Orange 43.png"));
		
		for(int i = 4; i < ghosts.size(); i++) {
			ghosts.get(i).setTexture(new Texture("Orange 43.png"));
		}
				
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
	
		batch.begin();
		
		
		if(!gameOver && gameStart) {
			playGame();
		} else if(!gameStart) {
			showMenu();
		} else {
			showGameOver();
		}
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.end();
	}
	
	private void showMenu() {
		stage.act();
		stage.draw();
	}
	
	private void playGame() {
		stage.clear();
		
		boolean keyPressed = false;
		float rotation = 0;

		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			game.gameTick("Left");
			keyPressed = true;
			pacmanRotation = 180;
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			game.gameTick("Right");
			keyPressed = true;
			pacmanRotation = 0;
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			game.gameTick("Up");
			keyPressed = true;
			pacmanRotation = 90;
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			game.gameTick("Down");
			keyPressed = true;
			pacmanRotation = 270;
		}
		
		if(keyPressed && game.isGameOver()) {
			gameOver = true;
		}
		
		// Draw maze
		for(int i = 0; i < maze.getM(); i++) {
			for(int j = 0; j < maze.getN(); j++) {
				char symbol = maze.getSymbol(i, j);
				
				float xCoord = toXCoord(j);
				float yCoord = toYCoord(i);
				
				if(symbol == 'W') {
					batch.draw(wall, xCoord, yCoord, xScale, yScale);
				} else if(symbol == 'G') {
					batch.draw(gate, xCoord, yCoord, xScale, yScale);
				} else if(symbol == 'F') {
					batch.draw(food, xCoord, yCoord, xScale, yScale);
				} else if(symbol == 'U') {
					batch.draw(powerUp, xCoord, yCoord, xScale, yScale);
				} else {
					batch.draw(floor, xCoord, yCoord, xScale, yScale);
				}
			}
		}	
		
		// Draw Pacman	
		batch.draw(new TextureRegion(pac.getTexture()), toXCoord(pac.getTuple().getFirst()), toYCoord(pac.getTuple().getSecond()), xScale/2, yScale/2, xScale, yScale, 1f, 1f, pacmanRotation);
		
		// Draw Ghosts
		for(GameObject ghost : ghosts) {
			Texture tex;
			
			if(game.isInFrightenedMode()) {
				tex = frightenedGhost;
			} else {
				tex = ghost.getTexture();
			}
			batch.draw(tex, toXCoord(ghost.getTuple().getFirst()), toYCoord(ghost.getTuple().getSecond()), xScale, yScale);
		}
		
		font.draw(batch, "Score: " + pac.getScore(), Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 20);
		font.draw(batch, "Lives: " + pac.getLives(), 10, Gdx.graphics.getHeight() - 20);
		
	}
	
	private void showGameOver() {
		// Show game over screen
		
		float spacing = 20;
		
		float centerX = Gdx.graphics.getWidth()/2 - 100;
		float centerY = Gdx.graphics.getHeight()/2 + 50;
		
		font.draw(batch, "Game Over!", centerX, centerY + spacing/2);
		font.draw(batch, "Your Score was: " + pac.getScore(), centerX, centerY - spacing/2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		floor.dispose();
		wall.dispose();
		gate.dispose();
		powerUp.dispose();
		food.dispose();
		frightenedGhost.dispose();
		
		pac.getTexture().dispose();
		
		for(Ghost ghost: ghosts) {
			ghost.getTexture().dispose();
		}
	}
	
	public float toXCoord(int x) {
		return (x) * xScale;
	}
	
	public float toYCoord(int y) {
		return (maze.getM()-(y) - 1) * yScale;
	}
	
	public void printMouse() {
		System.out.println(Gdx.input.getX() + " "+ Gdx.input.getY());
	}
}


// https://libgdx.info/buttons-scene2d/
