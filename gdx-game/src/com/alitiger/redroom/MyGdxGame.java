package com.alitiger.redroom;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.audio.*;
import java.util.*; 

public class MyGdxGame implements ApplicationListener
{
	OrthographicCamera camera;
	SpriteBatch batch;
	Music mydream;
	Sound gameover, levelup;
	Texture ball, obstacle, thome, cobs, hole, end;
	Sprite s1, s2, s3, s4, s5, s6, s7, s8;
	float Speed = 200;
	float timer1 = 0;
	int SCREEN_W, SCREEN_H; 
	int level = 51;
	Rectangle wallr, walll, wallb, wallt, home, home1, home2, holeb, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20;
	Circle circle, circle2, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
	Polygon p1, p2, p3, p4, p5, p6, p7, p8;
	List<Rectangle> obstacles;
	List<Circle> circles;
	List<Polygon> polygons;
	List<Sprite> sprites;
	double x = 0, y = 0, z = 0, w = 0;
	float deg1 = 0, deg2 = 0, deg3 = 0, deg4 = 0;
	BitmapFont LEVEL;
	int del = 1;
	Preferences pref;
	int S = 4;
	int ad;

	public MyGdxGame(int ad) {
		this.ad = ad;
	}

	@Override
	public void create()  
	{
		pref = Gdx.app.getPreferences("redroom");
		if (pref.contains("level")) {
			level = pref.getInteger("level") + ad;
			pref.remove("level");
		}

		camera = new OrthographicCamera();
		setCamera();
		batch = new SpriteBatch();

		mydream = Gdx.audio.newMusic(Gdx.files.internal("mydream.mp3"));
		gameover = Gdx.audio.newSound(Gdx.files.internal("gameover.mp3"));
		levelup = Gdx.audio.newSound(Gdx.files.internal("levelup.mp3"));
		mydream.setLooping(true);
		mydream.play();
		mydream.setVolume(0.3f); 

		ball = new Texture(Gdx.files.internal("ball.png"));
		obstacle = new Texture(Gdx.files.internal("obstacle.png"));
		thome = new Texture(Gdx.files.internal("home.png"));
		cobs = new Texture(Gdx.files.internal("cobs.png"));
		hole = new Texture(Gdx.files.internal("hole.png"));
		end = new Texture(Gdx.files.internal("end.png"));

		s1 = new Sprite(obstacle);
		s2 = new Sprite(obstacle);
		s3 = new Sprite(obstacle);
		s4 = new Sprite(obstacle); 
		s5 = new Sprite(obstacle);
		s6 = new Sprite(obstacle);
		s7 = new Sprite(obstacle);
		s8 = new Sprite(obstacle);

		SCREEN_W = 1080;
		SCREEN_H = 2224;

		LEVEL = new BitmapFont();
		LEVEL.setColor(Color.BLACK);
		LEVEL.setScale(2);

		wallb = new Rectangle(0, 0, SCREEN_W, SCREEN_W/21.6f);

		holeb = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);

		circle = new Circle(SCREEN_W/2, SCREEN_W/21.6f + SCREEN_W/72, SCREEN_W/72);
		circle2 = new Circle(SCREEN_W - SCREEN_W/3, circle.y, circle.radius);

		r1 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r2 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r3 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r4 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r5 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r6 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r7 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r8 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r9 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r10 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r11 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r12 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r13 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r14 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r15 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r16 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r17 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r18 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r19 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);
		r20 = new Rectangle(0,0,SCREEN_W/21.6f,SCREEN_W/21.6f);

		c1 = new Circle(0,0,SCREEN_W/43.2f);
		c2 = new Circle(0,0,SCREEN_W/43.2f);
		c3 = new Circle(0,0,SCREEN_W/43.2f);
		c4 = new Circle(0,0,SCREEN_W/43.2f);
		c5 = new Circle(0,0,SCREEN_W/43.2f);
		c6 = new Circle(0,0,SCREEN_W/43.2f);
		c7 = new Circle(0,0,SCREEN_W/43.2f);
		c8 = new Circle(0,0,SCREEN_W/43.2f);
		c9 = new Circle(0,0,SCREEN_W/43.2f);
		c10 = new Circle(0,0,SCREEN_W/43.2f);

		p1 = new Polygon();
		p2 = new Polygon();
		p3 = new Polygon();
		p4 = new Polygon();
		p5 = new Polygon();
		p6 = new Polygon();
		p7 = new Polygon();
		p8 = new Polygon();

		obstacles = new ArrayList<Rectangle>();
		circles = new ArrayList<Circle>();
		polygons = new ArrayList<Polygon>();
		sprites = new ArrayList<Sprite>();

	}

	@Override
	public void render() 
	{        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		switch (level) {
			case 1: level1();
			    break;
			case 2: level2();
			    break;
			case 3: level3();
				break;
			case 4: level4();
				break;
			case 5: level5();
				break;
			case 6: level6();
				break;
			case 7: level7();
				break;
			case 8: level8();
				break;
			case 9: level9();
				break;
			case 10: level10();
				break;
			case 11: level11();
				break;
			case 12: level12();
				break;
			case 13: level13();
				break;
			case 14: level14();
				break;
			case 15: level15();
				break;
			case 16: level16();
				break;
			case 17: level17();
				break;
			case 18: level18();
				break;
			case 19: level19();
				break;
			case 20: level20();
				break;
			case 21: level21();
				break;
			case 22: level22();
				break;
			case 23: level23();
				break;
			case 24: level24();
				break;
			case 25: level25();
				break;
			case 26: level26();
				break;
			case 27: level27();
				break;
			case 28: level28();
				break;
			case 29: level29();
				break;
			case 30: level30();
				break;
			case 31: level31();
				break;
			case 32: level32();
				break;
			case 33: level33();
				break;
			case 34: level34();
				break;
			case 35: level35();
				break;
			case 36: level36();
				break;
			case 37: level37();
				break;
			case 38: level38();
				break;
			case 39: level39();
				break;
			case 40: level40();
				break;
			case 41: level41();
				break;
			case 42: level42();
				break;
			case 43: level43();
				break;
			case 44: level44();
				break;
			case 45: level45();
				break;
			case 46: level46();
				break;
			case 47: level47();
				break;
			case 48: level48();
				break;
			case 49: level49();
				break;
			case 50: level50();
				break;
			case 51: level51();
			    break;
			case 52: level52();
			    break;
			case 53: level53();
			    break;
			case 54: level54();
			    break;
			case 55: level55();
			    break;
			case 56: level56();
			    break;
			case 57: level57();
			    break;
			case 58: level58();
			    break;
			case 59: level59();
			    break;
			case 60: level60();
			    break;

		}
		batch.draw(ball, circle.x - circle.radius, circle.y - circle.radius, circle.radius*2, circle.radius*2);
		if (level == 47) {
			batch.draw(ball, circle2.x - circle2.radius, circle2.y - circle2.radius, circle2.radius*2, circle2.radius*2);
		}
		batch.draw(thome, home.x, home.y, home.width, home.height);
		batch.draw(thome, wallb.x, wallb.y, wallb.width, wallb.height);
		if (level == 49) {
			batch.draw(thome, home1.x, home1.y, home1.width, home1.height);
			batch.draw(thome, home2.x, home2.y, home2.width, home2.height);
		}
		for (Circle c : circles) {
			batch.draw(cobs, c.x - c.radius, c.y - c.radius, c.radius*2, c.radius*2);
		}
		for (Rectangle r : obstacles) {
			batch.draw(obstacle, r.x, r.y, r.width, r.height);
		}
		for (int i = 0;i < sprites.toArray().length;i++) {
			sprites.get(i).draw(batch);
			sprites.get(i).setRotation(polygons.get(i).getRotation());
		}
		if (level == 34 || level == 35 || level == 36) {
			batch.draw(hole, holeb.x, holeb.y, holeb.width, holeb.height);
			LEVEL.setColor(Color.GREEN);
		}
		if (level == 50) {
			LEVEL.draw(batch, "Level:" + level, SCREEN_W/2, SCREEN_H/3);
			batch.draw(end, wallt.x, wallt.y, SCREEN_W, SCREEN_H/3);
		}else{
			LEVEL.draw(batch, "Level:" + level, SCREEN_W/10, SCREEN_H - (SCREEN_H/30));
		}
		batch.end();

		if (Intersector.overlaps(circle, home)) {
			levelup.play();
			levelup.setLooping(0, false);
			if (level == 39 || level == 41 || level == 46) {
				circle.setPosition(SCREEN_W/3,home.height + circle.radius);
			}else{
				circle.setPosition(SCREEN_W/2,home.height + circle.radius);
			}
			level++;
			timer1 = 0;
			del = 1;
			x = 0; y = 0; z = 0; w = 0; deg1 = 0; deg2 = 0; deg3 = 0; deg4 = 0;
		}

		for (Rectangle r : obstacles) {
			if (Intersector.overlaps(circle, r)) {
				gameover.play();
				gameover.setLooping(0, false);
				if (level == 40 || level == 42 || level == 47) {
					circle.setPosition(SCREEN_W/3,home.height + circle.radius);
					circle2.setPosition(SCREEN_W - SCREEN_W/3,home.height + circle2.radius);
				}else{
					circle.setPosition(SCREEN_W/2,home.height + circle.radius);
				}
				if (level == 45) { 
					r11.setHeight(0);
				}
				timer1 = 0;
			}
		}

		for (Circle c : circles) {
			if (Intersector.overlaps(circle, c)) {
				gameover.play();
				gameover.setLooping(0, false);
				if (level == 40 || level == 42 || level == 47) {
					circle.setPosition(SCREEN_W/3,home.height + circle.radius);
					circle2.setPosition(SCREEN_W - SCREEN_W/3,home.height + circle2.radius);
				}else{
					circle.setPosition(SCREEN_W/2,home.height + circle.radius);
				}
				if (level == 45) {
					r11.setHeight(0);
				}
				timer1 = 0;
			}
		}

		for (Polygon p : polygons) {
			if (overlaps(p, circle)) {
				gameover.play();
				gameover.setLooping(0, false);
				if (level == 40 || level == 42 || level == 47) {
					circle.setPosition(SCREEN_W/3,home.height + circle.radius);
					circle2.setPosition(SCREEN_W - SCREEN_W/3,home.height + circle2.radius);
				}else{ 
					circle.setPosition(SCREEN_W/2,home.height + circle.radius);
				}
				if (level == 45) {
					r11.setHeight(0);
				}
				timer1 = 0;
			}
		}
		timer1 += Gdx.graphics.getDeltaTime();
		if (timer1 > 1f) {
			circle.x += -Gdx.input.getAccelerometerX() * Speed * Gdx.graphics.getDeltaTime();
			if (level != 50) {
				circle.y += -Gdx.input.getAccelerometerY() * Speed * Gdx.graphics.getDeltaTime();
			}
		}

		if (circle.y  < SCREEN_W/21.6f + circle.radius) {
			circle.y = SCREEN_W/21.6f + circle.radius;
		}
		if (circle.x > SCREEN_W) {
			circle.x = SCREEN_W;
		}
		if (circle.x < 0) {
			circle.x = 0;
		}
	}

	private void setCamera() {
		camera.setToOrtho(false, 1080, 2224);
	}

	private void level1() {
		obstacles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/2, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		obstacles.add(r1);
	}
	private void level2() {
		obstacles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/10, SCREEN_H/3,SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(0, SCREEN_H - (SCREEN_H/3), SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		obstacles.add(r1);
		obstacles.add(r2);
	}
	private void level3() {
		obstacles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(SCREEN_W/10, SCREEN_H/2, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r3.set(0, (SCREEN_H*3)/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
	}
	private void level4() {
		obstacles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/10, SCREEN_H/5, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(0, (SCREEN_H*2)/5, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r3.set(SCREEN_W/10, (SCREEN_H*3)/5, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r4.set(0, (SCREEN_H*4)/5, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
	}
	private void level5() {
		obstacles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/6, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(SCREEN_W/10, (SCREEN_H*2)/6, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r3.set(0, (SCREEN_H*3)/6, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r4.set(SCREEN_W/10, (SCREEN_H*4)/6, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r5.set(0, (SCREEN_H*5)/6, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
	}
	private void level6() {
		obstacles.clear();
		circles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		c1.set((SCREEN_W/2) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/21.6f);
		c1.setX((float)((SCREEN_W/3) * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)((SCREEN_H/3) * Math.sin(x) + (SCREEN_H/2)));
		x += 0.05;
		circles.add(c1);
	}
	private void level7() {
		obstacles.clear();
		circles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/6, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(SCREEN_W/10, (SCREEN_H*5)/6, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		c1.set((SCREEN_W/2) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/21.6f);
		c1.setX((float)((SCREEN_W/3) * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)((SCREEN_H/3.5) * Math.sin(x) + (SCREEN_H/2)));
		x += 0.05;
		obstacles.add(r1);
		obstacles.add(r2);
		circles.add(c1);

	}
	private void level8() {
		obstacles.clear();
		circles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		c1.set((SCREEN_W/2) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/21.6f);
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)((SCREEN_H/3.5) * Math.sin(x) + (SCREEN_H/2)));
		x += 0.04;
		c2.set((SCREEN_W/2) + SCREEN_W/10.8f, SCREEN_H/2, SCREEN_W/36);
		c2.setX((float)(SCREEN_W/3.6f * Math.cos(y) + (SCREEN_W/2)));
		c2.setY((float)(SCREEN_W/5.4f * Math.sin(y) + (SCREEN_H/2)));
		y -= 0.06;
		circles.add(c1);
		circles.add(c2);
	}
	private void level9() {
		obstacles.clear();
		circles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(SCREEN_W/10, SCREEN_H/2, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r3.set(0, (SCREEN_H * 3)/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		c1.set(SCREEN_W/2, (r1.y + r2.y)/2, SCREEN_W/36);
		c1.setX((float)(SCREEN_W/5.4f * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)(SCREEN_W/5.4f * Math.sin(x) + (r1.y + r2.y)/2));
		x += 0.05;
		c2.set(SCREEN_W/2, (r2.y + r3.y)/2, SCREEN_W/36);
		c2.setX((float)(SCREEN_W/5.4f * Math.cos(y) + (SCREEN_W/2)));
		c2.setY((float)(SCREEN_W/5.4f * Math.sin(y) + (r2.y + r3.y)/2));
		y -= 0.06;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		circles.add(c1);
		circles.add(c2);
	}
	private void level10() {
		obstacles.clear();
		circles.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/9, SCREEN_H/7, SCREEN_W - (SCREEN_W*2)/9, SCREEN_W/36);
		r2.set(0, (SCREEN_H*6)/7, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_W/36);
		r3.set(SCREEN_W/2 + SCREEN_W/43.2f, (SCREEN_H*6)/7, SCREEN_W, SCREEN_W/36);
		c1.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H/2 + SCREEN_W/21.6f, SCREEN_W/21.6f);
		c2.set(SCREEN_W/2 + SCREEN_W/5.4f, SCREEN_H/2, SCREEN_W/36);
		c3.set(SCREEN_W/2 + SCREEN_W/10.8f, SCREEN_H/2, SCREEN_W/54);
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)((r2.y - r1.y - SCREEN_W/10.8f)/2 * Math.sin(x) + (r1.y + r2.y)/2));
		x += 0.02;
		c2.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2)));
		c2.setY((float)(SCREEN_W/5.4f * Math.sin(y) + (r1.y + r2.y)/2));
		y -= 0.04;
		c3.setX((float)(SCREEN_W/10.8f * Math.cos(y) + (SCREEN_W/2)));
		c3.setY((float)((r2.y - r1.y - SCREEN_W/10.8f)/2 * Math.sin(y) + (r1.y + r2.y)/2));
		z += 0.06;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
	}
	private void level11() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		p1.setVertices(new float[] {0,0,SCREEN_W - SCREEN_W/13.5f,0,SCREEN_W - SCREEN_W/13.5f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p1.setOrigin((SCREEN_W - SCREEN_W/13.5f)/2, SCREEN_W/43.2f);
		p1.setPosition(SCREEN_W/21.6f, SCREEN_H/2);
		if (del == 1) {
			s1.setBounds(SCREEN_W/21.6f, SCREEN_H/2, SCREEN_W - SCREEN_W/13.5f, SCREEN_W/21.6f);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		p1.setRotation(deg1);
		deg1 += 3;
		polygons.add(p1);

	}
	private void level12() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(SCREEN_W/10, (SCREEN_H*3)/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		p1.setVertices(new float[] {0,0,SCREEN_W - SCREEN_W/13.5f,0,SCREEN_W - SCREEN_W/13.5f,SCREEN_W/13.5f,0,SCREEN_W/13.5f});
		p1.setOrigin((SCREEN_W - SCREEN_W/13.5f)/2, SCREEN_W/27);
		p1.setPosition(SCREEN_W/21.6f, SCREEN_H/2);
		if (del == 1) {
			s1.setBounds(SCREEN_W/21.6f, SCREEN_H/2, SCREEN_W - SCREEN_W/13.5f, SCREEN_W/13.5f);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		p1.setRotation(deg1);
		deg1 += 2;
		obstacles.add(r1);
		obstacles.add(r2);
		polygons.add(p1);
	}
	private void level13() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		p1.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p1.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
		p1.setPosition(SCREEN_W/21.6f, (SCREEN_H*3)/4);

		p2.setVertices(new float[] {0,0,SCREEN_W/2f,0,SCREEN_W/2f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p2.setOrigin((SCREEN_W/2f)/2, SCREEN_W/43.2f);
		p2.setPosition(SCREEN_W/3.6f, SCREEN_H/2f);

		p3.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p3.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
		p3.setPosition(SCREEN_W/2.03f, (SCREEN_H*3)/4);

		if (del == 1) {
			s1.setBounds(SCREEN_W/21.6f, (SCREEN_H*3)/4, SCREEN_W /2.2f, SCREEN_W/36);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/2, SCREEN_W/21.6f);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			s3.setBounds(SCREEN_W/2.03f, (SCREEN_H*3)/4, SCREEN_W/2.2f, SCREEN_W/36);
			s3.setOrigin(s3.getWidth()/2, s3.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);
		sprites.add(s3);

		p1.setRotation(deg1);
		deg1 += 2;
		p2.setRotation(deg2);
		deg2 += 1;
		p3.setRotation(deg3);
		deg3 += 3;

		polygons.add(p1);
		polygons.add(p2);
		polygons.add(p3);
	}
	private void level14() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/3,SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(SCREEN_W/10, SCREEN_H - (SCREEN_H/3), SCREEN_W - (SCREEN_W/10), SCREEN_W/36);

		p1.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p1.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
		p1.setPosition(SCREEN_W/2.03f, SCREEN_H/5);

		p2.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p2.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
	    p2.setPosition(SCREEN_W/2.03f, SCREEN_H/2.2f);

	    p3.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p3.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
		p3.setPosition(SCREEN_W/21.6f, SCREEN_H/1.8f);

		p4.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p4.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
		p4.setPosition(SCREEN_W/21.6f, (SCREEN_H*4)/5);

		if (del == 1) {
			s1.setBounds(SCREEN_W/2.03f, SCREEN_H/5, SCREEN_W/2.2f, SCREEN_W/36);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/2.03f, SCREEN_H/2.2f, SCREEN_W/2.2f, SCREEN_W/36);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			s3.setBounds(SCREEN_W/21.6f, SCREEN_H/1.8f, SCREEN_W/2.2f, SCREEN_W/36);
			s3.setOrigin(s3.getWidth()/2, s3.getHeight()/2);
			s4.setBounds(SCREEN_W/21.6f, (SCREEN_H*4)/5, SCREEN_W/2.2f, SCREEN_W/36);
			s4.setOrigin(s4.getWidth()/2, s4.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);
		sprites.add(s3);
		sprites.add(s4);

		p1.setRotation(deg1);
		deg1 += 2;
		p2.setRotation(deg2);
		deg2 += 2;
		p3.setRotation(deg3);
		deg3 += 2;
		p4.setRotation(deg4);
		deg4 += 2;
		obstacles.add(r1);
		obstacles.add(r2);
		polygons.add(p1);
		polygons.add(p2);
		polygons.add(p3);
		polygons.add(p4);

	}
	private void level15() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/3,SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_W/36);
		r2.set(SCREEN_W/2 + SCREEN_W/43.2f, SCREEN_H/3, SCREEN_W/2, SCREEN_W/36);
		r3.set(SCREEN_W/10, (SCREEN_H*8)/9, (SCREEN_W*4)/5, SCREEN_W/36);
		c1.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H/4, SCREEN_W/36);
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)(SCREEN_W/10.8f * Math.sin(x) + SCREEN_H/4));
		x += 0.04;
		p1.setVertices(new float[] {0,0,SCREEN_W - SCREEN_W/13.5f,0,SCREEN_W - SCREEN_W/13.5f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p1.setOrigin((SCREEN_W - SCREEN_W/13.5f)/2, SCREEN_W/43.2f);
		p1.setPosition(SCREEN_W/21.6f, SCREEN_H/1.6f);
		if (del == 1) {
			s1.setBounds(SCREEN_W/21.6f, SCREEN_H/1.6f, SCREEN_W - SCREEN_W/13.5f, SCREEN_W/21.6f);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		p1.setRotation(deg1);
		deg1 += 2;
		polygons.add(p1);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		circles.add(c1);

	}
	private void level16() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/21.6f, SCREEN_H/5, SCREEN_W/15.4f, SCREEN_W/15.4f);
		r2.set(SCREEN_W - SCREEN_W/7.2f, (SCREEN_H*2)/5, SCREEN_W/15.4f, SCREEN_W/15.4f);
		r3.set(SCREEN_W/21.6f, (SCREEN_H*3)/5, SCREEN_W/15.4f, SCREEN_W/15.4f);
		r4.set(SCREEN_W - SCREEN_W/7.2f, (SCREEN_H*4)/5, SCREEN_W/15.4f, SCREEN_W/15.4f);
		r1.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		r2.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		r3.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		r4.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		x += 0.1;
		y += 0.1;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
	}
	private void level17() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W/3 - SCREEN_W/43.2f, SCREEN_W/36);
		r2.set(SCREEN_W/3 + SCREEN_W/43.2f, SCREEN_H/4, SCREEN_W/3 - SCREEN_W/43.2f, SCREEN_W/36);
		r3.set((SCREEN_W*2)/3 + SCREEN_W/21.6f, SCREEN_H/4, SCREEN_W/3, SCREEN_W/36);
		r4.set(SCREEN_W - SCREEN_W/7.2f, (SCREEN_H*1.5f)/4, SCREEN_W/10.8f, SCREEN_W/10.8f);
		r5.set(0, SCREEN_H/2, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_W/36);
		r6.set(SCREEN_W/2 + SCREEN_W/43.2f, SCREEN_H/2, SCREEN_W/2, SCREEN_W/36);
		r7.set(SCREEN_W/21.6f, (SCREEN_H*2.5f)/4, SCREEN_W/10.8f, SCREEN_W/10.8f);
		r8.set(SCREEN_W/10, (SCREEN_H*3)/4, SCREEN_W - SCREEN_W/5, SCREEN_W/36);
		r9.set(SCREEN_W - SCREEN_W/7.2f, (SCREEN_H*3.5f)/4, SCREEN_W/10.8f, SCREEN_W/10.8f);
		r4.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		r7.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		r9.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		x += 0.1;
		y += 0.1;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);

	}
	private void level18() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/2, SCREEN_H/3, SCREEN_W/2, SCREEN_W/36);
		r2.set(0, SCREEN_H - (SCREEN_H/3), SCREEN_W/2, SCREEN_W/36);
		r3.set(SCREEN_W/21.6f, SCREEN_H/3, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r4.set(SCREEN_W - SCREEN_W/7.2f, (SCREEN_H*2)/3, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r3.setX((float)(-SCREEN_W/4.9f * Math.cos(x) + (SCREEN_W/4)));
		r4.setX((float)(SCREEN_W/4.9f * Math.cos(y) + (SCREEN_W*3/4 - SCREEN_W/21.6f)));
		x += 0.1;
		y += 0.1;
		p1.setVertices(new float[] {0,0,SCREEN_W/2,0,SCREEN_W/2,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p1.setOrigin((SCREEN_W/2)/2, SCREEN_W/43.2f);
		p1.setPosition(SCREEN_W/3, SCREEN_H/2f);
		if (del == 1) {
			s1.setBounds(SCREEN_W/3, SCREEN_H/2f, SCREEN_W/2, SCREEN_W/21.6f);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		p1.setRotation(deg1);
		deg1 += 2;
		polygons.add(p1);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
	}
	private void level19() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		c1.set((SCREEN_W/2) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/36);
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)(SCREEN_W/2.16f * Math.sin(x) + (SCREEN_H/2)));
		x += 0.05;
		c2.set(SCREEN_W/4, SCREEN_H*2/3, SCREEN_W/54);
		c2.setX((float)(SCREEN_W/7.2f * Math.cos(y) + (SCREEN_W/5)));
		c2.setY((float)(SCREEN_W/10.8f * Math.sin(y) + (SCREEN_H*4/5)));
		y += 0.05;
		c3.set((SCREEN_W/4) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/54);
		c3.setX((float)(-SCREEN_W/7.2f * Math.cos(z) + (SCREEN_W*4/5)));
		c3.setY((float)(-SCREEN_W/10.8f * Math.sin(z) + (SCREEN_H*4/5)));
		z += 0.05;
		r1.set(SCREEN_W/21.6f, SCREEN_H*8/9, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r1.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		p1.setVertices(new float[] {0,0,SCREEN_W/4,0,SCREEN_W/4,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p1.setOrigin((SCREEN_W/4)/2, SCREEN_W/43.2f);
		p1.setPosition(SCREEN_W/2 - SCREEN_W/8, SCREEN_H*4/5f);

		p2.setVertices(new float[] {0,0,SCREEN_W/2,0,SCREEN_W/2,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p2.setOrigin((SCREEN_W/2)/2, SCREEN_W/43.2f);
		p2.setPosition(SCREEN_W/2 - SCREEN_W/4, SCREEN_H/2f);
		if (del == 1) {
			s1.setBounds(SCREEN_W/2 - SCREEN_W/8, SCREEN_H*4/5f, SCREEN_W/4, SCREEN_W/21.6f);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/2 - SCREEN_W/4, SCREEN_H/2f, SCREEN_W/2, SCREEN_W/21.6f);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);

		p1.setRotation(deg1);
		deg1 += 2;
		p2.setRotation(deg2);
		deg2 += 2;
		polygons.add(p1);
		polygons.add(p2);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		obstacles.add(r1);
	}
	private void level20() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/2, SCREEN_H*3/8, SCREEN_W/2, SCREEN_W/36);
		r2.set(SCREEN_W/5, SCREEN_H*6/7, SCREEN_W, SCREEN_W/36);
		r3.set(SCREEN_W - SCREEN_W/5.4f, SCREEN_H/3.2f, SCREEN_W/10.8f, SCREEN_W/10.8f);
		r4.set(SCREEN_W/3, SCREEN_H*7/9, SCREEN_W/7.2f, SCREEN_W/7.2f);
		r5.set(SCREEN_W/21.6f, SCREEN_H*6/7 - SCREEN_W/5, SCREEN_W/5 - SCREEN_W/21.6f, SCREEN_W/5 - SCREEN_W/21.6f);
		r6.set(SCREEN_W - SCREEN_W/7.2f, SCREEN_H*8/9, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r3.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		r4.setX((float)(SCREEN_W/5.4f * Math.cos(y) + (SCREEN_W/2 - SCREEN_W/10.8f)));
		r5.setY((float)(-SCREEN_W/2.7f * Math.sin(z) + (SCREEN_H/2 + SCREEN_W/2.16f)));
		r6.setX((float)(-SCREEN_W/2.4f * Math.cos(w) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		x += 0.1;
		y += 0.1;
		z += 0.04;
		w += 0.08; 
		p1.setVertices(new float[] {0,0,SCREEN_W + SCREEN_W/5.4f,0,SCREEN_W + SCREEN_W/5.4f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p1.setOrigin(0, SCREEN_W/43.2f);
		p1.setPosition(0, SCREEN_H*3/8);

		p2.setVertices(new float[] {0,0,SCREEN_W/4,0,SCREEN_W/4,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p2.setOrigin((SCREEN_W/4)/2, SCREEN_W/43.2f);
		p2.setPosition(SCREEN_W/2, SCREEN_H/2);
		if (del == 1) {
			s1.setBounds(0, SCREEN_H*3/8f, SCREEN_W + SCREEN_W/5.4f, SCREEN_W/21.6f);
			s1.setOrigin(0, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/2, SCREEN_H/2, SCREEN_W/4, SCREEN_W/21.6f);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);
		p1.setRotation(45);
		p2.setRotation(deg1);
		deg1 += 1;
		polygons.add(p1);
		polygons.add(p2);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);

	}
	private void level21() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W, SCREEN_W/21.6f);
		r2.set(SCREEN_W/10, SCREEN_H/2, SCREEN_W, SCREEN_W/21.6f);
		r3.set(0, (SCREEN_H * 3)/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/21.6f);
		r1.setWidth((float)(SCREEN_W/8 * Math.cos(x) + (SCREEN_W*7/8)));
		r2.setX((float)(SCREEN_W/8 * Math.cos(y) + (SCREEN_W/8)));
		r3.setWidth((float)(SCREEN_W/8 * Math.cos(z) + (SCREEN_W*7/8)));
		x += 0.06;
		y += 0.1;
		z += 0.15;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
	}
	private void level22() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/21.6f, SCREEN_H/3, SCREEN_W/2, SCREEN_W/2);
		r2.set(r1.width + SCREEN_W/10.8f, SCREEN_H/5, SCREEN_W, SCREEN_W/2);
		r3.set(SCREEN_W/2, SCREEN_H/5 + SCREEN_W/2 + SCREEN_W/2.25f, SCREEN_W/2, SCREEN_W/2);
		r4.set(SCREEN_W/10.8f, SCREEN_H/5 + SCREEN_W/2 + SCREEN_W/3.08f, SCREEN_W/2 - SCREEN_W/10.8f, SCREEN_H/3);
		c1.set((SCREEN_W/2) + SCREEN_W/4.32f, SCREEN_H/2 + SCREEN_W/10.8f, SCREEN_W/5.68f);
		c2.set((SCREEN_W/2) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/36);
		c3.set((SCREEN_W/2) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/36);
		c4.set((SCREEN_W/2) + SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/36);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);
	}
	private void level23() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r2.set(SCREEN_W/10, SCREEN_H/2, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r3.set(0, (SCREEN_H * 3)/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		r4.set(SCREEN_W/10, SCREEN_H/3.5f, SCREEN_W/2.3f, SCREEN_W/2.2f);
		r5.set(r4.x + r4.width + SCREEN_W/10.8f, SCREEN_H/3.8f, SCREEN_W - r5.x - SCREEN_W/10, SCREEN_W/2.3f);
		r6.set(0, r2.y + r2.height + SCREEN_W/21.6f, SCREEN_W - SCREEN_W/10.8f, SCREEN_H/4 - SCREEN_W/21.6f);
		r7.set(0, (SCREEN_H * 3)/4, SCREEN_W - (SCREEN_W/10), SCREEN_W/36);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
	}
	private void level24() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W*2/3 - SCREEN_W/43.2f, SCREEN_W/36);
		r2.set(SCREEN_W*2/3 + SCREEN_W/43.2f, SCREEN_H/4, SCREEN_W/2, SCREEN_W/36);
		r3.set(0, SCREEN_H/2, SCREEN_W/5 - SCREEN_W/43.2f, SCREEN_W/36);
		r4.set(SCREEN_W/5 + SCREEN_W/43.2f, SCREEN_H/2, SCREEN_W, SCREEN_W/36);
		r5.set(0, SCREEN_H*3/4, SCREEN_W*3/4 - SCREEN_W/43.2f, SCREEN_W/36);
		r6.set(SCREEN_W*3/4 + SCREEN_W/43.2f, SCREEN_H*3/4, SCREEN_W/2, SCREEN_W/36);
		r7.set(0, SCREEN_H/4 + SCREEN_W/10.8f, SCREEN_W, SCREEN_W/2 - SCREEN_W/6.17f);
		r7.setWidth((float)(SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		x += 0.05;
		c1.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H/2 + SCREEN_W/21.6f, SCREEN_W/21.6f);
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2)));
		c1.setY((float)(SCREEN_W/10.8f * Math.sin(z) + (SCREEN_H*3/4 + SCREEN_W/5.4f)));
		y += 0.05;
		z += 0.05;
		p1.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/36,0,SCREEN_W/36});
		p1.setOrigin((SCREEN_W/3)/2, SCREEN_W/72);
		p1.setPosition(SCREEN_W/8, (SCREEN_H/2 + SCREEN_W/3.85f));

		p2.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/36,0,SCREEN_W/36});
		p2.setOrigin((SCREEN_W/3)/2, SCREEN_W/72);
		p2.setPosition(SCREEN_W/2, SCREEN_H/2 + SCREEN_W/3.85f);
		if (del == 1) {
			s1.setBounds(SCREEN_W/8, SCREEN_H/2 + SCREEN_W/3.85f, SCREEN_W/3, SCREEN_W/36);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/2, SCREEN_H/2 + SCREEN_W/3.85f, SCREEN_W/3, SCREEN_W/36);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);
		p1.setRotation(deg1);
		deg1 += 2;
		p2.setRotation(deg2);
		deg2 += 2;
		polygons.add(p1);
		polygons.add(p2);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		circles.add(c1);
	}
	private void level25() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0,  wallb.height + circle.radius*2 + SCREEN_H/20, SCREEN_W, SCREEN_H/10);
		r2.set(0, r1.y + r1.height, SCREEN_W - SCREEN_W/7, SCREEN_H/10);
		r3.set(0, r2.y + r2.height, SCREEN_W, SCREEN_H/12);
		r4.set(0, r3.y + r3.height, SCREEN_W - SCREEN_W/7, SCREEN_H/10);
		r5.set(0, r4.y + r4.height, SCREEN_W, SCREEN_H/12);
		r6.set(0, r5.y + r5.height, SCREEN_W - SCREEN_W/7, SCREEN_H/10);
		r7.set(0, r6.y + r6.height, SCREEN_W, SCREEN_H/12);
		r8.set(0, r7.y + r7.height, SCREEN_W - SCREEN_W/7, SCREEN_H/10);
		r9.set(0, r8.y + r8.height, SCREEN_W, SCREEN_H/12);
		r1.setWidth((float)(SCREEN_W * Math.cos(x) + SCREEN_W/2));
		r2.setWidth((float)(SCREEN_W*3/7 * Math.cos(x) + SCREEN_W*3/7));
		r3.setWidth((float)(SCREEN_W * Math.cos(x) + SCREEN_W/2));
		r4.setWidth((float)(SCREEN_W*3/7 * Math.cos(x) + SCREEN_W*3/7));
		r5.setWidth((float)(SCREEN_W * Math.cos(x) + SCREEN_W/2));
		r6.setWidth((float)(SCREEN_W*3/7 * Math.cos(x) + SCREEN_W*3/7));
		r7.setWidth((float)(SCREEN_W * Math.cos(x) + SCREEN_W/2));
		r8.setWidth((float)(SCREEN_W*3/7 * Math.cos(x) + SCREEN_W*3/7));
		r9.setWidth((float)(SCREEN_W * Math.cos(x) + SCREEN_W/2));
		x += 0.04;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
	}
	private void level26() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W, SCREEN_H/10);
		r2.set(0, SCREEN_H/2, SCREEN_W, SCREEN_H/10);
		r3.set(0, SCREEN_H*3/4, SCREEN_W, SCREEN_H/10);
		r4.set(SCREEN_W/4 - SCREEN_W/10, 0, SCREEN_W/5, SCREEN_W/10);
		r5.set(SCREEN_W/2 - SCREEN_W/12, SCREEN_H*3/4, SCREEN_W/6, SCREEN_W/6);
		r6.set(SCREEN_W*3/4 - SCREEN_W/10, 0, SCREEN_W/5, SCREEN_H/10);
		r1.setWidth((float)(SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		r2.setWidth((float)(SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		r3.setWidth((float)(SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		r4.setHeight((float)(SCREEN_H/2 * Math.sin(x) + SCREEN_H/2));
		r5.setY((float)(SCREEN_H/4 * Math.sin(y) + SCREEN_H/2));
		r6.setHeight((float)(SCREEN_H/2 * Math.sin(x) + SCREEN_H/2));
		x += 0.04;
		y += 0.01;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);

	}
	private void level27() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/4, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_W/21.6f);
		r2.set(SCREEN_W/2 + SCREEN_W/43.2f, SCREEN_H/4, SCREEN_W/2, SCREEN_W/21.6f);
		r3.set(0, SCREEN_H*3/4, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_W/21.6f);
		r4.set(SCREEN_W/2 + SCREEN_W/43.2f, SCREEN_H*3/4, SCREEN_W/2, SCREEN_W/21.6f);
		c1.set(SCREEN_W/2, SCREEN_H/2 + SCREEN_W/43.2f, SCREEN_W/21.6f);
		c1.setRadius((float)(SCREEN_W/5 * Math.cos(x) + (SCREEN_W/4)));
		x += 0.05;
		circles.add(c1);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
	}
	private void level28() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/7, SCREEN_W, SCREEN_H/7);
		r2.set(0, r1.y + r1.height, SCREEN_W, SCREEN_H/7);
		r3.set(0, r2.y + r2.height, SCREEN_W, SCREEN_H/7);
		r4.set(0, r3.y + r3.height, SCREEN_W, SCREEN_H/7);
		r5.set(0, r4.y + r4.height, SCREEN_W, SCREEN_H/7);
		r1.setWidth((float)(SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		r2.setWidth((float)(-SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		r3.setWidth((float)(SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		r4.setWidth((float)(-SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		r5.setWidth((float)(SCREEN_W/2 * Math.cos(x) + SCREEN_W/2));
		x += 0.05;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
	}
	private void level29() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/3 + SCREEN_W/3.48f, SCREEN_H/4, SCREEN_W/2.16f, SCREEN_W/2.16f);
		r2.set(SCREEN_W/2, SCREEN_H/1.8f, SCREEN_W/2.16f, SCREEN_W/2.16f);
		r3.set(0, r2.y + (r2.height/2), SCREEN_W/1.96f, SCREEN_W/2.16f);
		c1.set(SCREEN_W/3, r2.y - SCREEN_W/4.32f, SCREEN_W/3.6f);
		c2.set(SCREEN_W*3/4 - SCREEN_W/21.6f, r3.y + r3.height - SCREEN_W/21.6f, SCREEN_W/5.4f);
		r1.setWidth((float)(SCREEN_W/4.32f * Math.cos(y) + SCREEN_W/4.32f));
		r1.setHeight((float)(SCREEN_W/4.32f * Math.cos(y) + SCREEN_W/4.32f));
		r2.setWidth((float)(SCREEN_W/4.32f * Math.cos(x) + SCREEN_W/4.32f));
		r2.setHeight((float)(SCREEN_W/4.32f * Math.cos(x) + SCREEN_W/4.32f));
		r3.setWidth((float)(SCREEN_W/4.32f * Math.cos(z) + SCREEN_W/4.32f));
		r3.setHeight((float)(SCREEN_W/4.32f * Math.cos(z) + SCREEN_W/4.32f));
		c1.setRadius((float)(SCREEN_W/7.2f * Math.cos(y) + SCREEN_W/7.2f));
		c2.setRadius((float)(SCREEN_W/10.8f * Math.cos(z) + SCREEN_W/10.8f));
		x += 0.03;
		y += 0.05;
		z += 0.05;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		circles.add(c1);
		circles.add(c2);
	}
	private void level30() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/8, SCREEN_W*3/4 - SCREEN_W/43.2f, SCREEN_W/36);
		r2.set(SCREEN_W*3/4 + SCREEN_W/43.2f, SCREEN_H/8, SCREEN_W, SCREEN_W/36);
		r3.set(0, SCREEN_H*2/8, SCREEN_W/3 - SCREEN_W/43.2f, SCREEN_W/36);
		r4.set(SCREEN_W/3 + SCREEN_W/43.2f, SCREEN_H*2/8, SCREEN_W, SCREEN_W/36);
		r5.set(0, SCREEN_H*3/8, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_W/36);
		r6.set(SCREEN_W/2 + SCREEN_W/43.2f, SCREEN_H*3/8, SCREEN_W, SCREEN_W/36);
		r7.set(0, SCREEN_H*4/8, SCREEN_W/4 - SCREEN_W/43.2f, SCREEN_W/36);
		r8.set(SCREEN_W/4 + SCREEN_W/43.2f, SCREEN_H*4/8, SCREEN_W, SCREEN_W/36);
		r9.set(0, SCREEN_H*5/8, SCREEN_W*4/5 - SCREEN_W/43.2f, SCREEN_W/36);
		r10.set(SCREEN_W*4/5 + SCREEN_W/43.2f, SCREEN_H*5/8, SCREEN_W, SCREEN_W/36);
		r11.set(0, SCREEN_H*6/8, SCREEN_W/5 - SCREEN_W/43.2f, SCREEN_W/36);
		r12.set(SCREEN_W/ 5 + SCREEN_W/43.2f, SCREEN_H*6/8, SCREEN_W, SCREEN_W/36);
		r13.set(0, SCREEN_H*7/8, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_W/36);
		r14.set(SCREEN_W/2 + SCREEN_W/43.2f,SCREEN_H*7/8, SCREEN_W, SCREEN_W/36);
		r15.set(0, circle.y + circle.radius*2 + SCREEN_W/21.6f, SCREEN_W, SCREEN_H);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
		obstacles.add(r11);
		obstacles.add(r12);
		obstacles.add(r13);
		obstacles.add(r14);
		obstacles.add(r15);
	}
	private void level31() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/18, SCREEN_H/5, SCREEN_W*2/5, SCREEN_W/3);
		r2.set(SCREEN_W - r1.width - SCREEN_W/13.5f, SCREEN_H*3/4, r1.width, r1.height);

		p1.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p1.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
		p1.setPosition(SCREEN_W/21.6f, (SCREEN_H*3)/4 + r2.height/2);

		p2.setVertices(new float[] {0,0,SCREEN_W/2f,0,SCREEN_W/2f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p2.setOrigin((SCREEN_W/2f)/2, SCREEN_W/43.2f);
		p2.setPosition(SCREEN_W/3.6f, SCREEN_H/2f);

		p3.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/36,0,SCREEN_W/36});
		p3.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/72);
		p3.setPosition(SCREEN_W/2.03f, SCREEN_H/5 + r1.height/2);
		if (del == 1) {
			s1.setBounds(SCREEN_W/21.6f, (SCREEN_H*3)/4 + r2.height/2, SCREEN_W /2.2f, SCREEN_W/36);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/3.6f, SCREEN_H/2, SCREEN_W/2, SCREEN_W/21.6f);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			s3.setBounds(SCREEN_W/2.03f, SCREEN_H/5 + r1.height/2, SCREEN_W/2.2f, SCREEN_W/36);
			s3.setOrigin(s3.getWidth()/2, s3.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);
		sprites.add(s3);

		p1.setRotation(deg1);
		deg1 += 3;
		p2.setRotation(deg2);
		deg2 += 1;
		p3.setRotation(deg3);
		deg3 += 3;

		r3.set(0, circle.y + circle.radius*2 + SCREEN_W/21.6f, SCREEN_W, SCREEN_H);

		polygons.add(p1);
		polygons.add(p2);
		polygons.add(p3);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);

	}
	private void level32() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/5, SCREEN_W, SCREEN_W/10.8f);
		r2.set(SCREEN_W, SCREEN_H*4/5, SCREEN_W, SCREEN_W/10.8f);
		r3.set(SCREEN_W/10, r1.y + r1.height + SCREEN_W/13.5f, SCREEN_W*4/5, r2.y - r1.y - r1.height*2.5f);
		r1.setWidth((float)(-SCREEN_W * Math.cos(x)));
		r2.setX((float)(SCREEN_W * Math.cos(x)));
		x += 0.05;
		r4.set(0, circle.y + circle.radius*2 + SCREEN_W/21.6f, SCREEN_W, SCREEN_H);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
	}
	private void level33() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/10, SCREEN_H/6, SCREEN_W*4/5, SCREEN_W/36);
		c1.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H*2/6, SCREEN_W/21.6f);
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c1.setY((float)(SCREEN_W/10.8f * Math.sin(y) + (SCREEN_H*2/6)));
		c2.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H*5/6, SCREEN_W/21.6f);
		c2.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c2.setY((float)(SCREEN_W/10.8f * Math.sin(y) + (SCREEN_H*5/6)));
		x += 0.05;
		y += 0.05;
		r2.set(SCREEN_W/21.6f, SCREEN_H*3/6, SCREEN_W/15.4f, SCREEN_W/15.4f);
		r3.set(SCREEN_W - SCREEN_W/7.2f, SCREEN_H*4/6, SCREEN_W/15.4f, SCREEN_W/15.4f);
	    r2.setX((float)(-SCREEN_W/2.4f * Math.cos(z) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		r3.setX((float)(SCREEN_W/2.4f * Math.cos(w) + (SCREEN_W/2 - SCREEN_W/21.6f)));
		z += 0.05;
		w += 0.05;
		r4.set(0, circle.y + circle.radius*2 + SCREEN_W/21.6f, SCREEN_W, SCREEN_H);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		circles.add(c1);
		circles.add(c2);
	}
	private void level34() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/10, SCREEN_W/3 - SCREEN_W/43.2f, SCREEN_W/5.4f);
		r2.set(SCREEN_W/3 + SCREEN_W/43.2f, SCREEN_H/10, SCREEN_W, r1.height - SCREEN_W/20);
		r3.set(SCREEN_W - SCREEN_W/7.2f, r1.y + r1.height, SCREEN_W/21.6f, SCREEN_H - r1.y - r1.height - SCREEN_W/10);
		r4.set(home.x + home.width, r1.y + r1.height + SCREEN_W/20 + SCREEN_W/21.6f, SCREEN_W - home.x - home.width - SCREEN_W/10 - r3.width - SCREEN_W/21.6f, SCREEN_H);
		r5.set(home.x + home.width - SCREEN_W/20, r1.y + r1.height, r4.width + SCREEN_W/10 + 10, SCREEN_W/21.6f);
		r6.set(SCREEN_W/10 + SCREEN_W/20 + SCREEN_W/21.6f, r1.y + r1.height, r5.x - r6.x, SCREEN_H/2 - SCREEN_W/20);
		r7.set(SCREEN_W/10, r6.y + r6.height + SCREEN_W/20, r4.x - r7.x, home.y - r7.y);
		r8.set(SCREEN_W/10, r1.y + r1.height + SCREEN_W/20, SCREEN_W/21.6f, r7.y - r8.y);
		holeb.set(circle.x - SCREEN_H*2, circle.y - SCREEN_H*2, SCREEN_H*4, SCREEN_H*4);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
	}
	private void level35() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/10, SCREEN_H/4, SCREEN_W, SCREEN_W/5.4f);
		r2.set(0, r1.y + r1.height + SCREEN_W/20, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_H/2 - r2.y);
		r3.set(r2.width + SCREEN_W/20, r1.y + r1.height, SCREEN_W, SCREEN_H/2 - r3.y);
		r4.set(r1.x + SCREEN_W/4, r1.y, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r4.setHeight((float)(SCREEN_W/4 * Math.cos(x) + SCREEN_W/4));
		x += 0.05;
		c1.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H*6/10, SCREEN_W/21.6f);
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2)));
		c2.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H*7/10, SCREEN_W/21.6f);
		c2.setX((float)(-SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2)));
		c3.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H*8/10, SCREEN_W/21.6f);
		c3.setX((float)(SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2)));
		c4.set(SCREEN_W/2 + SCREEN_W/3.6f, SCREEN_H*9/10, SCREEN_W/21.6f);
		c4.setX((float)(-SCREEN_W/2.4f * Math.cos(y) + (SCREEN_W/2)));
		y += 0.05;
		holeb.set(circle.x - SCREEN_H*2, circle.y - SCREEN_H*2, SCREEN_H*4, SCREEN_H*4);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);

	}
	private void level36() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll); 
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/8, SCREEN_W/3 - SCREEN_W/43.2f, SCREEN_H*9/10 - r1.y);
		r2.set(r1.x + r1.width + SCREEN_W/20, r1.y, SCREEN_W, r1.height - SCREEN_W/10);
		r3.set(r1.width, r1.y + r1.height - SCREEN_W/20, SCREEN_W - r1.width - SCREEN_W/10, SCREEN_W/20);
		r4.set(0, r1.y + r2.height/4, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r5.set(r2.x, r1.y + r2.height/2, SCREEN_W, SCREEN_W/21.6f);
		r6.set(0, r1.y + r2.height*3/4, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r7.set(r2.x + r2.width/4, r2.y + r2.height - SCREEN_W/21.6f, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r8.set(r3.x + r3.width - SCREEN_W/21.6f, r7.y, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r9.set(r2.x + r2.width*3/4 - SCREEN_W/10.8f, r7.y, SCREEN_W/21.6f, SCREEN_W/21.6f);
		r4.setWidth((float)(SCREEN_W/2 * Math.cos(x)));
		r5.setX((float)(r1.width * Math.cos(x) + r1.width));
		r6.setWidth((float)(SCREEN_W/2 * Math.cos(x)));
		r7.setHeight((float)(SCREEN_W/10 * Math.cos(x)));
		r8.setHeight((float)(SCREEN_W/10 * Math.cos(x)));
		x += 0.05;
		holeb.set(circle.x - SCREEN_H*2, circle.y - SCREEN_H*2, SCREEN_H*4, SCREEN_H*4);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
	}
	private void level37() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		p1.setVertices(new float[] {0,0,SCREEN_W - SCREEN_W/10.8f,0,SCREEN_W - SCREEN_W/10.8f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p1.setOrigin((SCREEN_W - SCREEN_W/10.8f)/2, SCREEN_W/43.2f);
		p1.setPosition(SCREEN_W/21.6f, SCREEN_H/2);

		p2.setVertices(new float[] {0,0,SCREEN_W - SCREEN_W/10.8f,0,SCREEN_W - SCREEN_W/10.8f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p2.setOrigin((SCREEN_W - SCREEN_W/10.8f)/2, SCREEN_W/43.2f);
		p2.setPosition(SCREEN_W/21.6f, SCREEN_H/2);

		p3.setVertices(new float[] {0,0,SCREEN_W - SCREEN_W/10.8f,0,SCREEN_W - SCREEN_W/10.8f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p3.setOrigin((SCREEN_W - SCREEN_W/10.8f)/2, SCREEN_W/43.2f);
		p3.setPosition(SCREEN_W/21.6f, SCREEN_H/2);

		p4.setVertices(new float[] {0,0,SCREEN_W - SCREEN_W/10.8f,0,SCREEN_W - SCREEN_W/10.8f,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p4.setOrigin((SCREEN_W - SCREEN_W/10.8f)/2, SCREEN_W/43.2f);
		p4.setPosition(SCREEN_W/21.6f, SCREEN_H/2);
		if (del == 1) {
			s1.setBounds(SCREEN_W/21.6f, SCREEN_H/2, SCREEN_W - SCREEN_W/10.8f, SCREEN_W/21.6f);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/21.6f, SCREEN_H/2, SCREEN_W - SCREEN_W/10.8f, SCREEN_W/21.6f);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			s3.setBounds(SCREEN_W/21.6f, SCREEN_H/2, SCREEN_W - SCREEN_W/10.8f, SCREEN_W/21.6f);
			s3.setOrigin(s3.getWidth()/2, s3.getHeight()/2);
			s4.setBounds(SCREEN_W/21.6f, SCREEN_H/2, SCREEN_W - SCREEN_W/10.8f, SCREEN_W/21.6f);
			s4.setOrigin(s3.getWidth()/2, s3.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);
		sprites.add(s3);
		sprites.add(s4);
		p1.setRotation(p1.getRotation() + 2);
		p2.setRotation(p1.getRotation() + 45);
		p3.setRotation(p1.getRotation() - 45);
		p4.setRotation(p1.getRotation() + 90);
		polygons.add(p1);
		polygons.add(p2);
		polygons.add(p3);
		polygons.add(p4);
	}
	private void level38() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		c1.set(SCREEN_W/2, SCREEN_H/2, (SCREEN_W - SCREEN_W/3.6f)/2);
		c1.setY((float)(SCREEN_H/10 * Math.sin(x) + (SCREEN_H/2)));
		c2.set(SCREEN_W/10.8f, SCREEN_H/2, SCREEN_W/21.6f);
		c2.setY((float)(SCREEN_H*9/10 * Math.sin(y) + (SCREEN_H/2)));
		c3.set(SCREEN_W - SCREEN_W/10.8f, SCREEN_H/2, SCREEN_W/21.6f);
		c3.setY((float)(-SCREEN_H*9/10 * Math.sin(y) + (SCREEN_H/2)));
		c4.set(SCREEN_W/2, SCREEN_H*8/10, SCREEN_W/21.6f);
		c4.setX((float)(SCREEN_W/4 * Math.cos(x) + (SCREEN_W/2)));
		x += 0.05;
		y += 0.02;
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);
	}
	private void level39() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/7, SCREEN_W, SCREEN_H/7);
		r2.set(0, r1.y + r1.height, SCREEN_W/2, SCREEN_H/7);
		r3.set(0, r2.y + r2.height, SCREEN_W, SCREEN_H/7);
		r4.set(0, r3.y + r3.height, SCREEN_W/2, SCREEN_H/7);
		r5.set(0, r4.y + r4.height, SCREEN_W, SCREEN_H/7);
		r6.set(SCREEN_W, SCREEN_H/7, SCREEN_W, SCREEN_H/7);
		r7.set(SCREEN_W + SCREEN_W/5.4f, r6.y + r6.height, SCREEN_W/2, SCREEN_H/7);
		r8.set(SCREEN_W, r7.y + r7.height, SCREEN_W, SCREEN_H/7);
		r9.set(SCREEN_W + SCREEN_W/5.4f, r8.y + r8.height, SCREEN_W/2, SCREEN_H/7);
		r10.set(SCREEN_W, r9.y + r9.height, SCREEN_W, SCREEN_H/7);
		r1.setWidth((float)((SCREEN_W/4) * Math.cos(x) + SCREEN_W/4));
		r2.setWidth((float)((-SCREEN_W/4) * Math.cos(y) + SCREEN_W/2));
		r3.setWidth((float)((SCREEN_W/4) * Math.cos(x) + SCREEN_W/4));
		r4.setWidth((float)((SCREEN_W/4) * Math.cos(y) + SCREEN_W/2));
		r5.setWidth((float)((SCREEN_W/4) * Math.cos(x) + SCREEN_W/4));
		r6.setX((float)(-SCREEN_W/2 * Math.cos(x) + SCREEN_W));
		r7.setX((float)(-SCREEN_W/2 * Math.cos(x) + SCREEN_W + SCREEN_W/5.4f));
		r8.setX((float)(-SCREEN_W/2 * Math.cos(x) + SCREEN_W));
		r9.setX((float)(-SCREEN_W/2 * Math.cos(x) + SCREEN_W + SCREEN_W/5.4f));
		r10.setX((float)(-SCREEN_W/2 * Math.cos(x) + SCREEN_W));
		x += 0.09;
		y += 0.005;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
	}
	private void level40() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/4 - SCREEN_W/21.6f, SCREEN_H/2 + SCREEN_W/43.2f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/2 - SCREEN_W/43.2f, 0, SCREEN_W/21.6f, SCREEN_H/4 - SCREEN_W/43.2f);
		r2.set(SCREEN_W/2 - SCREEN_W/43.2f, r1.y + r1.height + SCREEN_W/21.6f, SCREEN_W/21.6f, SCREEN_H/2 - SCREEN_W/21.6f);
		r3.set(SCREEN_W/2 - SCREEN_W/43.2f, r2.y + r2.height + SCREEN_W/21.6f, SCREEN_W/21.6f, SCREEN_H/4);
		r4.set(0, SCREEN_H/2 - SCREEN_W/43.2f, SCREEN_W*3/4 - SCREEN_W/43.2f, SCREEN_W/21.6f);
		r5.set(SCREEN_W*3/4 + SCREEN_W/43.2f, SCREEN_H/2 - SCREEN_W/43.2f, SCREEN_W/4, SCREEN_W/21.6f);

		p1.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p1.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p1.setPosition(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H/4);

		p2.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p2.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p2.setPosition(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H/4);

		p3.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p3.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p3.setPosition(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H*3/4);

		p4.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p4.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p4.setPosition(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H*3/4);

		p5.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p5.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p5.setPosition(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H*3/4);

		p6.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p6.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p6.setPosition(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H*3/4);

		p7.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p7.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p7.setPosition(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H/4);

		p8.setVertices(new float[] {0,0,SCREEN_W/3,0,SCREEN_W/3,SCREEN_W/21.6f,0,SCREEN_W/21.6f});
		p8.setOrigin((SCREEN_W/3)/2, SCREEN_W/43.2f);
		p8.setPosition(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H/4);
		if (del == 1) {
			s1.setBounds(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H/4, SCREEN_W/3, SCREEN_W/21.6f);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H/4, SCREEN_W/3, SCREEN_W/21.6f);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			s3.setBounds(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H*3/4, SCREEN_W/3, SCREEN_W/21.6f);
			s3.setOrigin(s3.getWidth()/2, s3.getHeight()/2);
			s4.setBounds(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H*3/4, SCREEN_W/3, SCREEN_W/21.6f);
			s4.setOrigin(s4.getWidth()/2, s4.getHeight()/2);
			s5.setBounds(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H*3/4, SCREEN_W/3, SCREEN_W/21.6f);
			s5.setOrigin(s5.getWidth()/2, s5.getHeight()/2);
			s6.setBounds(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H*3/4, SCREEN_W/3, SCREEN_W/21.6f);
			s6.setOrigin(s6.getWidth()/2, s6.getHeight()/2);
			s7.setBounds(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H/4, SCREEN_W/3, SCREEN_W/21.6f);
			s7.setOrigin(s7.getWidth()/2, s7.getHeight()/2);
			s8.setBounds(SCREEN_W/2 - SCREEN_W/3 - SCREEN_W/21.6f, SCREEN_H/4, SCREEN_W/3, SCREEN_W/21.6f);
			s8.setOrigin(s8.getWidth()/2, s8.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);
		sprites.add(s3);
		sprites.add(s4);
		sprites.add(s5);
		sprites.add(s6);
		sprites.add(s7);
		sprites.add(s8);
		p1.setRotation(p1.getRotation() + 2);
		p2.setRotation(p1.getRotation() + 90);
		p3.setRotation(p3.getRotation() + 2);
		p4.setRotation(p3.getRotation() + 90);
		p5.setRotation(p5.getRotation() + 2);
		p6.setRotation(p5.getRotation() + 90);
		p7.setRotation(p7.getRotation() + 1.5f);
		p8.setRotation(p7.getRotation() + 90);
		polygons.add(p1);
		polygons.add(p2);
		polygons.add(p3);
		polygons.add(p4);
		polygons.add(p5);
		polygons.add(p6);
		polygons.add(p7);
		polygons.add(p8);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
	}
	private void level41() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/5, SCREEN_W/2 - SCREEN_W/27, SCREEN_W/21.6f);
		r2.set(SCREEN_W/2 + SCREEN_W/27, SCREEN_H/5, SCREEN_W/2, SCREEN_W/21.6f);
		r3.set(0, SCREEN_H*4/5, SCREEN_W/2 - SCREEN_W/27, SCREEN_W/21.6f);
		r4.set(SCREEN_W/2 + SCREEN_W/27, SCREEN_H*4/5, SCREEN_W/2, SCREEN_W/21.6f);
		r5.set(0, r1.y + r1.height, SCREEN_W/2, r3.y - r5.y);
		r6.set(SCREEN_W/2 + SCREEN_W/43.2f, r5.y, SCREEN_W/2, r5.height);
		r5.setX((float)(SCREEN_W/3 * Math.cos(x)));
		r6.setX(r5.x + r5.width + SCREEN_W/13.5f);
		x += 0.005;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
	}
	private void level42() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W*3/4 - SCREEN_W/21.6f, SCREEN_W/21.6f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/2 - SCREEN_W/43.2f, SCREEN_W/2 - SCREEN_W/14.4f, SCREEN_W/21.6f);
		r2.set(SCREEN_W/2 + SCREEN_W/14.4f, SCREEN_H/2 - SCREEN_W/43.2f, SCREEN_W/2, SCREEN_W/21.6f);
		r3.set(SCREEN_W/2 - SCREEN_W/43.2f, 0, SCREEN_W/21.6f, SCREEN_H*3/4 - SCREEN_W/36);
		r4.set(SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_H*3/4 + SCREEN_W/36, SCREEN_W/21.6f, SCREEN_H/4);
		r5.set(0, SCREEN_H, SCREEN_W/2 - SCREEN_W/43.2f, SCREEN_H/2);
		r6.set(SCREEN_W, SCREEN_H/2 + SCREEN_W/43.2f, SCREEN_W/2, SCREEN_H/2);
		r5.setY((float)((SCREEN_H/4 - SCREEN_W/43.2f) * Math.sin(x) + SCREEN_H*3/4));
		r6.setX((float)((SCREEN_W/4 - SCREEN_W/43.2f) * Math.cos(x) + SCREEN_W*3/4));
		x += 0.02;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
	}
	private void level43() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/8.3f, SCREEN_H/6, SCREEN_W, SCREEN_W/21.6f);
		r2.set(0, SCREEN_H*2/6, SCREEN_W - SCREEN_W/8.3f, SCREEN_W/21.6f);
		r3.set(SCREEN_W/8.3f, SCREEN_H*3/6, SCREEN_W, SCREEN_W/21.6f);
		r4.set(0, SCREEN_H*4/6, SCREEN_W - SCREEN_W/8.3f, SCREEN_W/21.6f);
		r5.set(SCREEN_W/8.3f, SCREEN_H*5/6, SCREEN_W, SCREEN_W/21.6f);
		r6.set(SCREEN_W/5.4f, r1.y + r1.height, SCREEN_W/21.6f, r2.y - r6.y);
		r7.set(r6.x + r6.width + SCREEN_W/13.5f, r6.y, SCREEN_W/21.6f, r6.height);
		r8.set(SCREEN_W/5.4f, r2.y + r2.height, SCREEN_W/21.6f, r3.y - r8.y);
		r9.set(r8.x + r8.width + SCREEN_W/13.5f, r8.y, SCREEN_W/21.6f, r8.height);
		r10.set(SCREEN_W/5.4f, r3.y + r3.height, SCREEN_W/21.6f, r4.y - r10.y);
		r11.set(r10.x + r10.width + SCREEN_W/13.5f, r10.y, SCREEN_W/21.6f, r10.height);
		r12.set(SCREEN_W/5.4f, r4.y + r4.height, SCREEN_W/21.6f, r5.y - r12.y);
		r13.set(r12.x + r12.width + SCREEN_W/13.5f, r12.y, SCREEN_W/21.6f, r12.height);
		r6.setX((float)((SCREEN_W - SCREEN_W/6)/2 * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r7.setX(r6.x + r6.width + SCREEN_W/13.5f);
		r8.setX((float)(-(SCREEN_W - SCREEN_W/6)/2 * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r9.setX(r8.x + r8.width + SCREEN_W/13.5f);
		r10.setX((float)((SCREEN_W - SCREEN_W/6)/2 * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r11.setX(r10.x + r10.width + SCREEN_W/13.5f);
		r12.setX((float)(-(SCREEN_W - SCREEN_W/6)/2 * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r13.setX(r12.x + r12.width + SCREEN_W/13.5f);
		x += 0.005;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
		obstacles.add(r11);
		obstacles.add(r12);
		obstacles.add(r13);
	}
	private void level44() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/10, SCREEN_W/2 - SCREEN_W/27, SCREEN_H/9);
		r2.set(SCREEN_W/2 + SCREEN_W/27, r1.y, SCREEN_W, SCREEN_H/9);
		r3.set(0, r1.y + r1.height, SCREEN_W/2 - SCREEN_W/27, SCREEN_H/9);
		r4.set(SCREEN_W/2 + SCREEN_W/27, r3.y, SCREEN_W, SCREEN_H/9);
		r5.set(0, r3.y + r3.height, SCREEN_W/2 - SCREEN_W/27, SCREEN_H/9);
		r6.set(SCREEN_W/2 + SCREEN_W/27, r5.y, SCREEN_W, SCREEN_H/9);
		r7.set(0, r5.y + r5.height, SCREEN_W/2 - SCREEN_W/27, SCREEN_H/9);
		r8.set(SCREEN_W/2 + SCREEN_W/27, r7.y, SCREEN_W, SCREEN_H/9);
		r9.set(0, r7.y + r7.height, SCREEN_W/2 - SCREEN_W/27, SCREEN_H/9);
		r10.set(SCREEN_W/2 + SCREEN_W/27, r9.y, SCREEN_W, SCREEN_H/9);
		r11.set(0, r9.y + r9.height, SCREEN_W/2 - SCREEN_W/27, SCREEN_H/9);
		r12.set(SCREEN_W/2 + SCREEN_W/27, r11.y, SCREEN_W, SCREEN_H/9);
		r13.set(0, r11.y + r11.height, SCREEN_W/2 - SCREEN_W/27, SCREEN_H/9);
		r14.set(SCREEN_W/2 + SCREEN_W/27, r13.y, SCREEN_W, SCREEN_H/9);
		r1.setWidth((float)((SCREEN_W/2 - SCREEN_W/6) * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r2.setX(r1.x + r1.width + SCREEN_W/13.5f);
		r3.setWidth((float)(-(SCREEN_W/2 - SCREEN_W/6) * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r4.setX(r3.x + r3.width + SCREEN_W/13.5f);
		r5.setWidth((float)((SCREEN_W/2 - SCREEN_W/6) * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r6.setX(r5.x + r5.width + SCREEN_W/13.5f);
		r7.setWidth((float)(-(SCREEN_W/2 - SCREEN_W/6) * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r8.setX(r7.x + r7.width + SCREEN_W/13.5f);
		r9.setWidth((float)((SCREEN_W/2 - SCREEN_W/6) * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r10.setX(r9.x + r9.width + SCREEN_W/13.5f);
		r11.setWidth((float)(-(SCREEN_W/2 - SCREEN_W/6) * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r12.setX(r11.x + r11.width + SCREEN_W/13.5f);
		r13.setWidth((float)((SCREEN_W/2 - SCREEN_W/6) * Math.cos(x) + (SCREEN_W - SCREEN_W/6)/2));
		r14.setX(r13.x + r13.width + SCREEN_W/13.5f);
		x += 0.003;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
		obstacles.add(r11);
		obstacles.add(r12);
		obstacles.add(r13);
		obstacles.add(r14);
	}
	private void level45() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		if (del == 1) {
			r1.set(0, SCREEN_H/11, SCREEN_W*9/10, SCREEN_W/21.6f);
			r2.set(SCREEN_W/10, SCREEN_H*2/11, SCREEN_W, SCREEN_W/21.6f);
			r3.set(0, SCREEN_H*3/11, SCREEN_W*9/10, SCREEN_W/21.6f);
			r4.set(SCREEN_W/10, SCREEN_H*4/11, SCREEN_W, SCREEN_W/21.6f);
			r5.set(0, SCREEN_H*5/11, SCREEN_W*9/10, SCREEN_W/21.6f);
			r6.set(SCREEN_W/10, SCREEN_H*6/11, SCREEN_W, SCREEN_W/21.6f);
			r7.set(0, SCREEN_H*7/11, SCREEN_W*9/10, SCREEN_W/21.6f);
			r8.set(SCREEN_W/10, SCREEN_H*8/11, SCREEN_W, SCREEN_W/21.6f);
			r9.set(0, SCREEN_H*9/11, SCREEN_W*9/10, SCREEN_W/21.6f);
			r10.set(SCREEN_W/10, SCREEN_H*10/11, SCREEN_W, SCREEN_W/21.6f);
			home = new Rectangle(SCREEN_W - SCREEN_W/10.8f, r10.y + r10.height, SCREEN_W/21.6f, wallt.y - r10.y - r10.height);
			r11.set(0, 0, SCREEN_W, 0);
			del = 0;
		}
		if (timer1 > 6f) {
			r11.setHeight(r11.getHeight() + 0.8f);
		}
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
		obstacles.add(r11);
	}
	private void level46() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		if (del == 1) {
			r1.set(0, SCREEN_H/2, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r2.set(r1.width + SCREEN_W/13.5f, r1.y, SCREEN_W, SCREEN_W/21.6f);
			r3.set(0, r1.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r4.set(r3.width + SCREEN_W/13.5f, r3.y, SCREEN_W, SCREEN_W/21.6f);
			r5.set(0, r3.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r6.set(r5.width + SCREEN_W/13.5f, r5.y, SCREEN_W, SCREEN_W/21.6f);
			r7.set(0, r5.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r8.set(r7.width + SCREEN_W/13.5f, r7.y, SCREEN_W, SCREEN_W/21.6f);
			r9.set(0, r7.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r10.set(r9.width + SCREEN_W/13.5f, r9.y, SCREEN_W, SCREEN_W/21.6f);
			r11.set(0, r9.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r12.set(r11.width + SCREEN_W/13.5f, r11.y, SCREEN_W, SCREEN_W/21.6f);
			del = 0;
		}
		if (timer1 > 1f) {
			if (r1.y > -SCREEN_W/2) {
				r1.y -= 5;
				r2.y = r1.y;
			}else{
				r1.y = SCREEN_H*5/4;
				r2.y = r1.y;
				r1.width = new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10;
				r2.x = r1.width + SCREEN_W/13.5f;
			}
			if (r3.y > -SCREEN_W/2) {
				r3.y -= 5;
				r4.y = r3.y;
			}else{
				r3.y = SCREEN_H*5/4;
				r2.y = r3.y;
				r3.width = new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10;
				r4.x = r3.width + SCREEN_W/13.5f;
			}
			if (r5.y > -SCREEN_W/2) {
				r5.y -= 5;
				r6.y = r5.y;
			}else{
				r5.y = SCREEN_H*5/4;
				r6.y = r5.y;
				r5.width = new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10;
				r6.x = r5.width + SCREEN_W/13.5f;
			}
			if (r7.y > -SCREEN_W/2) {
				r7.y -= 5;
				r8.y = r7.y;
			}else{
				r7.y = SCREEN_H*5/4;
				r8.y = r7.y;
				r7.width = new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10;
				r8.x = r7.width + SCREEN_W/13.5f;
			}
			if (r9.y > -SCREEN_W/2) {
				r9.y -= 5;
				r10.y = r9.y;
			}else{
				r9.y = SCREEN_H*5/4;
				r10.y = r9.y;
				r9.width = new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10;
				r10.x = r9.width + SCREEN_W/13.5f;
			}
			if (r11.y > -SCREEN_W/2) {
				r11.y -= 5;
				r12.y = r11.y;
			}else{
				r11.y = SCREEN_H*5/4;
				r12.y = r11.y;
				r11.width = new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10;
				r12.x = r11.width + SCREEN_W/13.5f;
			}

		}
		obstacles.add(r1); 
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
		obstacles.add(r11);
		obstacles.add(r12);
		for (Rectangle r : obstacles) {
			if (Intersector.overlaps(circle, r)) {
				del = 1;
			}
		}
	}
	private void level47() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		home = new Rectangle(SCREEN_W/4 - SCREEN_W/21.6f, SCREEN_H-SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(SCREEN_W/2 - SCREEN_W/43.2f, 0, SCREEN_W/21.6f, SCREEN_H);
		r2.set(SCREEN_W/10, SCREEN_H/8, r1.x - r2.x - SCREEN_W/20, SCREEN_W/21.6f);
		r3.set(r1.x + r1.width/2, SCREEN_H*2/8, SCREEN_W/2 - SCREEN_W/10, SCREEN_W/21.6f);
		r4.set(0, SCREEN_H*3/8, r1.x - SCREEN_W/20, SCREEN_W/21.6f);
		r5.set(0, SCREEN_H*6/8, SCREEN_W/4 - SCREEN_W/43.2f, SCREEN_W/21.6f);
		r6.set(r5.width + SCREEN_W/21.6f, r5.y, SCREEN_W/4, SCREEN_W/21.6f);
		r7.set(SCREEN_W/10, SCREEN_H*7/8, SCREEN_W/10.8f, SCREEN_W/10.8f);
		r8.set(r1.x + r1.width/2 + SCREEN_W/20, r7.y, SCREEN_W/10.8f, SCREEN_W/10.8f);
		r7.setX((float)(SCREEN_W/4 * Math.cos(x) + SCREEN_W/4 - r7.width/2));
		r8.setX((float)(-SCREEN_W/4 * Math.cos(y) + SCREEN_W*3/4 - r8.width/2));
		x += 0.06;
		y += 0.03;
		r9.set(SCREEN_W/10, SCREEN_H*4/8, SCREEN_W*4/5, SCREEN_W/21.6f);
		r10.set(SCREEN_W, SCREEN_H*5/8, SCREEN_W/2, SCREEN_W/7.2f);
		r10.setX((float)(-SCREEN_W/4 * Math.cos(z) + SCREEN_W*3/4));
		z += 0.05;
		obstacles.add(r1); 
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
		if (timer1 > 1f) {
			circle2.x += Gdx.input.getAccelerometerX() * Speed * Gdx.graphics.getDeltaTime();
			circle2.y += -Gdx.input.getAccelerometerY() * Speed * Gdx.graphics.getDeltaTime();
			if (circle2.y  < SCREEN_W/21.6f + circle2.radius) {
				circle2.y = SCREEN_W/21.6f + circle2.radius;
			}
			for (Rectangle r : obstacles) {
				if (Intersector.overlaps(circle2, r)) {
					gameover.play();
					gameover.setLooping(0, false);
					circle.setPosition(SCREEN_W/3,home.height + circle.radius);
					circle2.setPosition(SCREEN_W - SCREEN_W/3,home.height + circle2.radius);
					timer1 = 0;
				}
			}
			for (Circle c : circles) {
				if (Intersector.overlaps(circle2, c)) {
					gameover.play();
					gameover.setLooping(0, false);
					circle.setPosition(SCREEN_W/3,home.height + circle.radius);
					circle2.setPosition(SCREEN_W - SCREEN_W/3,home.height + circle2.radius);
					timer1 = 0;
				}
			}
		}
	}
	private void level48() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, 0, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r2.set(0, r1.y + r1.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r3.set(0, r2.y + r2.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r4.set(0, r3.y + r3.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r5.set(0, r4.y + r4.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r6.set(0, r5.y + r5.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r7.set(SCREEN_W*3/4, SCREEN_W/10.8f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r8.set(SCREEN_W*3/4, r7.y + r7.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r9.set(SCREEN_W*3/4, r8.y + r8.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r10.set(SCREEN_W*3/4, r9.y + r9.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r11.set(SCREEN_W*3/4, r10.y + r10.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r12.set(SCREEN_W*3/4, r11.y + r11.height + SCREEN_W/13.5f, SCREEN_W/4, SCREEN_H/7 - SCREEN_W/27);
		r13.set(SCREEN_W/4, SCREEN_H/2, r7.x - r1.width, SCREEN_H/5);
		r13.setY((float)(SCREEN_H/3 * Math.cos(x) + SCREEN_H/2 - r13.height/2));
		x += 0.04; 
		home = new Rectangle(SCREEN_W/21.6f, r6.y + r6.height, SCREEN_W/21.6f, wallt.y - r6.y - r6.height);
		obstacles.add(r1); 
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8); 
		obstacles.add(r9);
		obstacles.add(r10);
		obstacles.add(r11);
		obstacles.add(r12);
		obstacles.add(r13);
	}
	private void level49() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f); 
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(0, SCREEN_H/8, SCREEN_W/3 - SCREEN_W/43.2f, SCREEN_W/5.4f);
		r2.set(r1.width + SCREEN_W/21.6f, SCREEN_H/8, SCREEN_W, r1.height - SCREEN_W/20);
		r3.set(0, r1.y + r1.height, SCREEN_W*2/3 - SCREEN_W/43.2f, SCREEN_H/3 - r3.y);
		r4.set(r3.width + SCREEN_W/20, r2.y + r2.height, SCREEN_W/2, SCREEN_H/3 - r4.y);
		r5.set(r3.width/2, r3.y + r3.height, r3.width - r5.x, SCREEN_W/5.4f);
		r6.set(SCREEN_W/10, r5.y + r5.height, SCREEN_W, SCREEN_H*2/3 - r6.y - SCREEN_W/20);
		r7.set(0, SCREEN_H*2/3, SCREEN_W/4, SCREEN_H/3 - SCREEN_W/7.2f);
		r8.set(r7.width + SCREEN_W/20, r6.y + r6.height - SCREEN_W/20, SCREEN_W/4, r7.height);
		r9.set(r8.x + r8.width + SCREEN_W/20, r6.y + r6.height + SCREEN_W/20, SCREEN_W/21.6f, r7.height);
		r10.set(r7.width, r8.y + r8.height + SCREEN_W/20, r9.x - r10.x, SCREEN_W/20);
		r11.set(r10.x + r10.width, r10.y + r10.height, SCREEN_W/21.6f, SCREEN_H - r11.y);
		r12.set(0, r7.y + r7.height, SCREEN_W/10, SCREEN_H - r7.y);
		r12.setWidth((float)((r11.x - r12.width)/2 * Math.cos(x) + (r11.x + r12.width)/2));
		x += 0.05;
		home = new Rectangle(SCREEN_W/10 + r7.width/2, SCREEN_H - SCREEN_W/10.8f, SCREEN_W/10.8f, SCREEN_W/21.6f); 
		home1 = new Rectangle(SCREEN_W*9/10, r4.y + r4.height, SCREEN_W/10, r6.y - r5.y);
		home2 = new Rectangle(r11.x + r11.width, SCREEN_H - SCREEN_W/10.8f, SCREEN_W - r11.x - r11.width, SCREEN_W/21.6f);
		c1.set((home1.x + r5.x + r5.width)/2, (r6.y + r4.y + r4.height)/2, SCREEN_W/18);
		c2.set((SCREEN_W - SCREEN_W/21.6f + r9.x + r9.width)/2, r6.y + r6.height + SCREEN_W/5, SCREEN_W/9);
		c3.set(c2.x, c2.y + c2.radius*2 + SCREEN_W/20, SCREEN_W/9);
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		obstacles.add(r1); 
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5); 
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9); 
		obstacles.add(r10);
		obstacles.add(r11);
		obstacles.add(r12);
		if (Intersector.overlaps(circle, home1)) {
			levelup.play();
			levelup.setLooping(0, false);
			circle.setPosition(SCREEN_W/5, r3.y + r3.height + SCREEN_W/20);
		}else if (Intersector.overlaps(circle, home2)) {
			levelup.play();
			levelup.setLooping(0, false);
			circle.setPosition(r11.x - SCREEN_W/20, SCREEN_H - SCREEN_W/10);
		}
	}
	private void level50() {
		obstacles.clear();
		circles.clear();
		polygons.clear();
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, -SCREEN_H*2, SCREEN_W/21.6f, SCREEN_H*10);
		walll = new Rectangle(0, -SCREEN_H*2, SCREEN_W/21.6f, SCREEN_H*10);
		obstacles.add(wallr);
		obstacles.add(walll);
		if (del == 1) {
			r1.set(0, SCREEN_H/2, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r2.set(r1.width + SCREEN_W/13.5f, r1.y, SCREEN_W, SCREEN_W/21.6f);

			c1.set(SCREEN_W/2, r2.y + r2.height + SCREEN_H/8, SCREEN_W/21.6f);

			r3.set(0, c1.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r4.set(r3.width + SCREEN_W/13.5f, r3.y, SCREEN_W, SCREEN_W/21.6f);

			c2.set(SCREEN_W/2, r4.y + r4.height + SCREEN_H/8, SCREEN_W/21.6f);

			r5.set(0, c2.y + SCREEN_H/4, SCREEN_W/7.2f, SCREEN_W/7.2f);

		    r6.set(SCREEN_W/5, r5.y + SCREEN_H/4, SCREEN_W, SCREEN_W/21.6f);

			c3.set(SCREEN_W/2, r6.y + r6.height + SCREEN_H/8, SCREEN_W/21.6f);

			r7.set(0, c3.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r8.set(r7.width + SCREEN_W/13.5f, r7.y, SCREEN_W, SCREEN_W/21.6f);

			c4.set(SCREEN_W/2, r8.y + r8.height + SCREEN_H/8, SCREEN_W/21.6f);

			r9.set(0, c4.y + SCREEN_H/4, SCREEN_W/7.2f, SCREEN_W/7.2f);

		    r10.set(0, r9.y + SCREEN_H/4, SCREEN_W*4/5, SCREEN_W/21.6f);

			c5.set(SCREEN_W/2, r10.y + r10.height + SCREEN_H/8, SCREEN_W/21.6f);

			r11.set(0, c5.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r12.set(r11.width + SCREEN_W/13.5f, r11.y, SCREEN_W, SCREEN_W/21.6f);

			r13.set(0, r12.y + SCREEN_H/4, SCREEN_W/7.2f, SCREEN_W/7.2f);

		    r14.set(0, r13.y + SCREEN_H/4, SCREEN_W/7.2f, SCREEN_W/7.2f);

			c6.set(SCREEN_W/2, r14.y + r14.height + SCREEN_H/8, SCREEN_W/21.6f);

			r15.set(0, c6.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r16.set(r15.width + SCREEN_W/13.5f, r15.y, SCREEN_W, SCREEN_W/21.6f);

			c7.set(SCREEN_W/2, r16.y + r16.height + SCREEN_H/8, SCREEN_W/21.6f);

			c8.set(SCREEN_W/2, c7.y + c7.radius + SCREEN_H/8, SCREEN_W/21.6f);

			r17.set(0, c8.y + SCREEN_H/4, new Random().nextInt(SCREEN_W*2/3) + SCREEN_W/10, SCREEN_W/21.6f);
		    r18.set(r17.width + SCREEN_W/13.5f, r17.y, SCREEN_W, SCREEN_W/21.6f);

			r19.set(0, r18.y + SCREEN_H/4, SCREEN_W/7.2f, SCREEN_W/7.2f);

			c9.set(SCREEN_W/2, r19.y + r19.height + SCREEN_H/8, SCREEN_W/21.6f);

			r20.set(0, c9.y + SCREEN_H/4, SCREEN_W/7.2f, SCREEN_W/7.2f);
			home = new Rectangle(SCREEN_W/2 - SCREEN_W/21.6f, r20.y + SCREEN_H/4, SCREEN_W/10.8f, SCREEN_W/21.6f);
			wallt = new Rectangle(0, home.y + home.height, SCREEN_W, SCREEN_H/3);
			del = 0;
		}
		r5.setX((float)(SCREEN_W/2 * Math.cos(y) + SCREEN_W/2));
		r9.setX((float)(-SCREEN_W/2 * Math.cos(y) + SCREEN_W/2));
		r13.setX((float)(SCREEN_W/2 * Math.cos(y) + SCREEN_W/2));
		r14.setX((float)(-SCREEN_W/2 * Math.cos(y) + SCREEN_W/2));
		r19.setX((float)(SCREEN_W/2 * Math.cos(y) + SCREEN_W/2));
		r20.setX((float)(-SCREEN_W/2 * Math.cos(y) + SCREEN_W/2));
		c1.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c2.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c3.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c4.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c5.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c6.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c7.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c8.setX((float)(-SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		c9.setX((float)(SCREEN_W/2.4f * Math.cos(x) + (SCREEN_W/2)));
		x += 0.04;
		y += 0.02;
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		circles.add(c4);
		circles.add(c5);
		circles.add(c6);
		circles.add(c7);
		circles.add(c8);
		circles.add(c9);
		obstacles.add(wallt);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
		obstacles.add(r8);
		obstacles.add(r9);
		obstacles.add(r10);
		obstacles.add(r11);
		obstacles.add(r12);
		obstacles.add(r13);
		obstacles.add(r14);
		obstacles.add(r15);
		obstacles.add(r16);
		obstacles.add(r17);
		obstacles.add(r18);
		obstacles.add(r19);
		obstacles.add(r20);
		camera.position.y = circle.y - circle.radius*2;
		if (timer1 > 1f) {
			circle.y += S;
		}
		for (Rectangle r : obstacles) {
			if (Intersector.overlaps(circle, r)) {
				del = 1;
			}
		}
	}
	private void level51() {
		obstacles.clear();
		circles.clear();
		polygons.clear(); 
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - SCREEN_W/21.6f, SCREEN_W, SCREEN_W/21.6f);
		obstacles.add(wallr);
		obstacles.add(walll);
		obstacles.add(wallt);
		r1.set(120, SCREEN_H/5, SCREEN_W, 100);
		r2.set(120, r1.y + r1.height, 100, wallt.y - r1.y - r1.height - 80); 
		r3.set(290, r2.y + 200, 100, wallt.y - r2.y - 200);
		r4.set(r3.x + r3.width + 70, r2.y, 200, r2.height - 80);
		r5.set(r4.x + r4.width + 70, r4.y, wallr.x - r4.x - r4.width - 150, r2.height/2);
		r6.set(r5.x, r5.y + r5.height + 80, r5.width, r2.height/2);
		r7.set(0, wallt.y + wallt.height, SCREEN_W, SCREEN_H);
		home = new Rectangle(r6.x + r6.width, wallt.y - 50, 100, 50);
		r7.setY((float)(SCREEN_H * Math.sin(x) + wallt.y + wallt.height + SCREEN_H/3)); 
		x += 0.01; 
		obstacles.add(r1);
		obstacles.add(r2); 
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
		obstacles.add(r7);
	}
	private void level52() {}
	private void level53() {}
	private void level54() {}
	private void level55() {}
	private void level56() {}
	private void level57() {}
	private void level58() {}
	private void level59() {}
	private void level60() {}


	public boolean overlaps(Polygon polygon, Circle circle) {
		float []vertices=polygon.getTransformedVertices();
		Vector2 center=new Vector2(circle.x, circle.y);
		float squareRadius=circle.radius*circle.radius;
		for (int i=0;i<vertices.length;i+=2){
			if (i==0){
				if (Intersector.intersectSegmentCircle(new Vector2(vertices[vertices.length-2], vertices[vertices.length-1]), new Vector2(vertices[i], vertices[i+1]), center, squareRadius))
					return true;
			} else {
				if (Intersector.intersectSegmentCircle(new Vector2(vertices[i-2], vertices[i-1]), new Vector2(vertices[i], vertices[i+1]), center, squareRadius))
					return true;
			}
		}
		return false;
	}


	@Override
	public void dispose()
	{
		pref.putInteger("level", level);
		pref.flush();
		ball.dispose();
		obstacle.dispose();
		thome.dispose();
		mydream.dispose();
		gameover.dispose();
		levelup.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		setCamera();
	}

	@Override
	public void pause()
	{
		pref.putInteger("level", level);
		pref.flush();
		mydream.stop();
	}

	@Override
	public void resume()
	{
	}
}

