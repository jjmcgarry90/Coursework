#include "myLib.h"

unsigned short *videoBuffer = (unsigned short *)0x6000000;

/*
Draws a rectangle at location (row,col) of size ht x wid
*/
void drawRect(int row, int col, int ht, int wid, unsigned short color)
{
	
	int r,c;
	for(r=0; r<ht; r++)
	{
		for(c=0; c<wid; c++)
		{
			if(c+col > -1 && c+col < 240)
			setPixel(r+row,c+col,color);
		}
	}
}


/* Lights the pixel at location (r,c) */
void setPixel(int r, int c, unsigned short color)
{
	videoBuffer[OFFSET(r,c,NUMCOLS)] = color;
}

/*
Creates a pause in the program by carrying out the for loop
*/
void delay(int n)
{
	int i;
	volatile int y;
	for(i=0; i<n; i++)
	{
		y = i*n;
	}
}

/*
 * Rectangular Collision Detection
 * Rect a first object
 * Rect b second object
 * returns 1 if they are colliding 0 otherwise
 */
int rectCollides(RECT a, RECT b) {
	if((a.col+a.width >= b.col && a.col <= b.col+b.width) && 
	(a.row+a.height >= b.row && a.row <= b.row+b.height))
		return 1;
	else
		return 0;
}		

/*
 * Draws an image in mode3
 * int x x coordinate 
 * int y y coordinate
 * int width Width of the image
 * int height Height of the image
 * const u16* pointer to the first element in the image
 */
void drawImage3(int row, int col, int width, int height, const u16* image){
	int r,c;
	for(r=0; r<height; r++)
	{
		for(c=0; c<width; c++)
		{
			if(c+col > -1 && c+col < 240)
				setPixel(r+row,c+col,*image);
			image++;
		}
	}
}

/*
Allows us to draw at the best time to minimize flickering
*/
void waitForVblank()
{
	while(SCANLINECOUNTER > 160);
	while(SCANLINECOUNTER < 160);
}


