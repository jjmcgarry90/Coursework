#include "myLib.h"

u16* videobuffer = (u16*) 0x6000000;

void setPixel(int r, int c, u16 color) {
	videobuffer[r * 240 + c] = color;
}

void drawRect(int r, int c, int width, int height, u16 color) {
	int h,w;
	for(h = 0; h<height; h++) 
		for(w = 0; w < width; w++)
			videobuffer[(r+h)*240 + (c+w)] = color;
}
	
void delay(int count) {
	volatile int r;
	for(r=0;r<count;r++)
		r+=1;	
}
