#include <stdlib.h>
#include <stdio.h>
#include "myLib.h"
#include "text.h"
#include "hand.h"
#include "brandon.h"
#include "slap.h"


RECT craft, dodge;
int speed;
int score,newScore;
char text[41];
char inst[41];
char scorebuf[41];
char newScorebuf[41];

/* Sets up initial values and locations of in-game objects */
void initialize() {
	REG_DISPCTL = MODE3 | BG2_ENABLE;
	craft.row = 80; craft.col = 50;
	craft.width = 18; craft.height = 18;
	dodge.width = 18, dodge.height=18;
	dodge.row = rand() % (160-dodge.height);
	dodge.col = 240;
	speed = 6;
	newScore = 0;
}

/* Draws black where objects are located to clear screen */
void clear() {
	drawRect(dodge.row,dodge.col,dodge.height,dodge.width,BLACK);
	drawString(159,10,scorebuf,BLACK); 
	drawRect(craft.row,craft.col,craft.height,craft.width,BLACK);
}

/* Creates the animation in the program by changing the properties
   of the objects */
void update() {
	sprintf(scorebuf,"%d",score);
	sprintf(newScorebuf,"%d",newScore);
	dodge.col-= speed;
	if(dodge.col+30 < 0) {
		dodge.col = 240;
		dodge.row = rand()%110; 
		newScore++;
	}

	if(KEY_DOWN_NOW(BUTTON_UP))
		craft.row-=2;
	if(KEY_DOWN_NOW(BUTTON_DOWN))
		craft.row+=2;
	if(KEY_DOWN_NOW(BUTTON_LEFT))
		craft.col-=2;
	if(KEY_DOWN_NOW(BUTTON_RIGHT))
		craft.col+=2; 
		
	if(craft.col < 0)
		craft.col = 0;
	if(craft.col+craft.width > 239)
		craft.col = 239-craft.width;
	if(craft.row < 0)
		craft.row = 0;
	if(craft.row+craft.height > 159)
		craft.row = 159-craft.height;
	
	score = newScore;

			
	if(KEY_DOWN_NOW(BUTTON_SELECT))
		title();
}

/* Draws the interactive in-game objects */
void draw() {
	drawImage3(dodge.row,dodge.col,dodge.height,dodge.width,hand);
	drawString(159,10,newScorebuf,RED); 
	drawImage3(craft.row,craft.col,craft.height,craft.width,brandon);
	if(rectCollides(craft,dodge))
		slapScreen(); 
	}

/* Code to set up the title screen text */
void title()  {
	sprintf(text, "NOT THE FACE!");
	drawString(80,80,text,RED);
	
	sprintf(text, "Press Start");
	drawString(140,85,text,WHITE);
	
	sprintf(inst, "(use the arrow keys to dodge)");
	drawString(130,32,inst,WHITE);
	
	while(!KEY_DOWN_NOW(BUTTON_START)){}
	drawString(140,85,text,BLACK);
	
	sprintf(text, "NOT THE FACE!");
	drawString(80,80,text,BLACK);
	
	drawString(130,32,inst,BLACK);
	initialize();
}

/* Sets up the screen after a collision occurs */
void gameOver() {
	clear();
	sprintf(text,"GAME OVER");
	drawString(60,85,text,RED);
	sprintf(text,"You dodged %d slap(s)!",newScore);
	drawString(80,53,text,RED);
	delay(1000000);
	drawString(80,53,text,BLACK);
	sprintf(text,"GAME OVER");
	drawString(60,85,text,BLACK);
	title();
}

void slapScreen() {
	drawImage3(0,0,240,160,slap);
	delay(500000);
	drawRect(0,0,160,240,BLACK);
	gameOver();
}
	
/* Runs the game */
int main(void) {
	initialize();
	title();
	while(1) {
		waitForVblank();
		clear();
		update();
		draw();
	}
	
	return 0;
}
