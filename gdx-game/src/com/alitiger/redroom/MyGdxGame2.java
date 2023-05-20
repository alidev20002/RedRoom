package com.alitiger.redroom;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.audio.*;
import java.util.*; 

public class MyGdxGame2 implements ApplicationListener
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
	int level = 56;
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

	public MyGdxGame2(int ad) {
		this.ad = ad;
	}

	@Override
	public void create()  
	{
		
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
		
		batch.draw(thome, home.x, home.y, home.width, home.height);
		batch.draw(thome, wallb.x, wallb.y, wallb.width, wallb.height);
		
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
		
		LEVEL.draw(batch, "Level:" + level, SCREEN_W/10, SCREEN_H - (SCREEN_H/30));
		
		batch.end();

		if (Intersector.overlaps(circle, home)) {
			levelup.play();
			levelup.setLooping(0, false);
			if (level == 52) {
				circle.setPosition(SCREEN_W/2,wallb.y - circle.radius);
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
				if (level == 53) {
					circle.setPosition(SCREEN_W/2,wallb.y - circle.radius);
				}else{
					circle.setPosition(SCREEN_W/2,home.height + circle.radius);
				}
				timer1 = 0;
			}
		}

		for (Circle c : circles) {
			if (Intersector.overlaps(circle, c)) {
				gameover.play();
				gameover.setLooping(0, false);
				if (level == 53) {
					circle.setPosition(SCREEN_W/2,wallb.y - circle.radius);
				}else{
					circle.setPosition(SCREEN_W/2,home.height + circle.radius);
				}
				timer1 = 0;
			}
		}

		for (Polygon p : polygons) {
			if (overlaps(p, circle)) {
				gameover.play();
				gameover.setLooping(0, false);
				if (level == 53) {
					circle.setPosition(SCREEN_W/2,wallb.y - circle.radius);
				}else{
					circle.setPosition(SCREEN_W/2,home.height + circle.radius);
				}
				timer1 = 0;
			}
		}
		timer1 += Gdx.graphics.getDeltaTime();
		if (timer1 > 1f) {
			circle.x += -Gdx.input.getAccelerometerX() * Speed * Gdx.graphics.getDeltaTime();
			circle.y += -Gdx.input.getAccelerometerY() * Speed * Gdx.graphics.getDeltaTime();
		}
		if (level != 53) {
			if (circle.y  < SCREEN_W/21.6f + circle.radius) {
				circle.y = SCREEN_W/21.6f + circle.radius;
			}
		}else{
			if (circle.y  > SCREEN_H - 50 - circle.radius) {
				circle.y = SCREEN_H - 50 - circle.radius;
			}
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
	private void level52() {
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
		r1.set(0, SCREEN_H/6, SCREEN_W/2 - 40, 100);
		r2.set(r1.x + r1.width + 80, r1.y, SCREEN_W/2, 100);
		r3.set(50, r1.y, 150, SCREEN_H/2);
		r4.set(SCREEN_W - 200, r1.y, 150, SCREEN_H/2);
		r5.set(0, r4.y + SCREEN_H/4, SCREEN_W/2 - 40, 100);
		r6.set(r5.x + r5.width + 80, r5 .y, SCREEN_W/2, 100);
		r7.set(0, r3.y + r3.height, SCREEN_W/2 - 30, 130);
		r8.set(r7.x + r7.width + 60, r7.y, SCREEN_W/2, 130);
		r9.set(150, r8.y + r8.height + 200, SCREEN_W, wallt.y - r8.y - r8.height - 250);
		r10.set(50, r8.y + r8.height, 200, 200);
		home = new Rectangle(SCREEN_W -150, wallt.y - 50, 100, 50);
		r10.setX((float) ((SCREEN_W/2 - 100) * Math.cos(x) + SCREEN_W/2 - 100));
		x += 0.02;
		p1.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/18,0,SCREEN_W/18});
		p1.setOrigin((SCREEN_W/2.2f)/2, SCREEN_W/36);
		p1.setPosition(SCREEN_W/2 - SCREEN_W/4.4f, (r5.y + r1.y + r1.height)/2 - 20);
		
		p2.setVertices(new float[] {0,0,SCREEN_W/2.2f,0,SCREEN_W/2.2f,SCREEN_W/18,0,SCREEN_W/18});
		p2.setOrigin(SCREEN_W/4.4f, SCREEN_W/36);
		p2.setPosition(SCREEN_W/2 - SCREEN_W/4.4f, (r7.y + r5.y + r5.height)/2 - 20);
		
		if (del == 1) {
			s1.setBounds(SCREEN_W/2 - SCREEN_W/4.4f, (r5.y + r1.y + r1.height)/2 - 20, SCREEN_W/2.2f, SCREEN_W/18);
			s1.setOrigin(s1.getWidth()/2, s1.getHeight()/2);
			s2.setBounds(SCREEN_W/2 - SCREEN_W/4.4f, (r7.y + r5.y + r5.height)/2 - 20, SCREEN_W/2.2f, SCREEN_W/18);
			s2.setOrigin(s2.getWidth()/2, s2.getHeight()/2);
			del = 0;
		}
		sprites.add(s1);
		sprites.add(s2);

		p1.setRotation(deg1);
		deg1 += 2;
		p2.setRotation(deg2);
		deg2 += 1;

		polygons.add(p1);
		polygons.add(p2);
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
	private void level53() {
		obstacles.clear();
		circles.clear();
		polygons.clear(); 
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, 0, SCREEN_W, SCREEN_W/21.6f);
		wallb.set(0, SCREEN_H - 50, SCREEN_W, 50); 
		obstacles.add(wallr);
		obstacles.add(walll); 
		obstacles.add(wallt);
		if (del == 1) {
			circle.setPosition(SCREEN_W/2,wallb.y - circle.radius);
			home = new Rectangle(SCREEN_W/2 - 50, wallt.y + wallt.height, 100, 50);
			r1.set(0, SCREEN_H*6/7, SCREEN_W - 250, 100);
			r2.set(0, SCREEN_H*5/7, SCREEN_W - 250, 100);
			r3.set(0, SCREEN_H*4/7, SCREEN_W - 250, 100);
			r4.set(0, SCREEN_H*3/7, SCREEN_W - 250, 100);
			r5.set(0, SCREEN_H*2/7, SCREEN_W - 250, 100);
			r6.set(0, SCREEN_H/7, SCREEN_W - 250, 100);
			del = 0;
		}
		r1.setX((float) (75*Math.cos(x) + 125));
		r2.setX((float) (-75*Math.cos(x) + 125));
		r3.setX((float) (75*Math.cos(x) + 125));
		r4.setX((float) (-75*Math.cos(x) + 125));
		r5.setX((float) (75*Math.cos(x) + 125));
		r6.setX((float) (-75*Math.cos(x) + 125));
		x += 0.08;
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
		obstacles.add(r4);
		obstacles.add(r5);
		obstacles.add(r6);
	}
	private void level54() {
		obstacles.clear();
		circles.clear();
		polygons.clear(); 
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallb.set(0, 0, SCREEN_W, SCREEN_W/21.6f);
		wallt = new Rectangle(0, SCREEN_H - 50, SCREEN_W, 50);
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
			r1.set(120, SCREEN_H/4, SCREEN_W, 100);
			r2.set(0, SCREEN_H*3/4 - 50, SCREEN_W/2 - 40, 100);
			r3.set(r2.x + r2.width + 80, r2.y, SCREEN_W/2, 100);
			home = new Rectangle(SCREEN_W/2 - 50, wallt.y - 50, 100, 50);
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
		p1.setRotation(p1.getRotation() + 1);
		p2.setRotation(p1.getRotation() + 45);
		p3.setRotation(p1.getRotation() - 45);
		p4.setRotation(p1.getRotation() + 90);
		polygons.add(p1);
		polygons.add(p2);
		polygons.add(p3);
		polygons.add(p4);
		obstacles.add(r1);
		obstacles.add(r2);
		obstacles.add(r3);
	}
	private void level55() {
		obstacles.clear();
		circles.clear();
		polygons.clear(); 
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - 50, SCREEN_W, 50);
		obstacles.add(wallr);
		obstacles.add(walll); 
		obstacles.add(wallt);
		r1.set(120, 0, 200, SCREEN_H);
		r2.set(SCREEN_W*3/4 - 50, 0, 200, SCREEN_H);
		r3.set(SCREEN_W/4 - 50, SCREEN_H, 100, SCREEN_H);
		r4.set(SCREEN_W*2/4 - 50, SCREEN_H, 100, SCREEN_H);
		r5.set(SCREEN_W*3/4 - 50, SCREEN_H, 100, SCREEN_H);
		r6.set(120, SCREEN_H/4, SCREEN_W, 80);
		r7.set(0, SCREEN_H*3/4, SCREEN_W/2 - 50, 100);
		r8.set(r7.x + r7.width + 100, r7.y, SCREEN_W/2, 100);
		r9.set(0, SCREEN_H/2, SCREEN_W - 120, 100);
		r10.set(0, SCREEN_H, r6.x, SCREEN_H);
		r11.set(SCREEN_W - 120, SCREEN_H, 70, SCREEN_H);
		
		home = new Rectangle(SCREEN_W/2 - 50, wallt.y - 50, 100, 50);
		r1.setHeight((float) (SCREEN_H * Math.sin(x)));
		r2.setHeight((float) (SCREEN_H * Math.sin(x)));
		r3.setY((float) (SCREEN_H * Math.sin(x) + SCREEN_H));
		r4.setY((float) (SCREEN_H * Math.sin(x) + SCREEN_H));
		r5.setY((float) (SCREEN_H * Math.sin(x) + SCREEN_H));
		r10.setY((float) (SCREEN_H * Math.sin(x) + SCREEN_H));
		r11.setY((float) (SCREEN_H * Math.sin(x) + SCREEN_H));
		x += 0.01;
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
	private void level56() {
		obstacles.clear();
		circles.clear();
		polygons.clear(); 
		sprites.clear();
		wallr = new Rectangle(SCREEN_W - SCREEN_W/21.6f, 0, SCREEN_W/21.6f, SCREEN_H);
		walll = new Rectangle(0, 0, SCREEN_W/21.6f, SCREEN_H);
		wallt = new Rectangle(0, SCREEN_H - 50, SCREEN_W, 50);
		obstacles.add(wallr);
		obstacles.add(walll); 
		obstacles.add(wallt);
	}
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
		mydream.stop();
	}

	@Override
	public void resume()
	{
	}
}

