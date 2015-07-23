#ifndef MY_LIB_H
#define MY_LIB_H


typedef unsigned short u16;
#define REG_DISPCNT *((u16*) 0x4000000)
#define RGB(r,g,b) ((r) | ((g) << 5)) | ((b) << 10))

#define BG2_ENABLE (1 << 10)
#define MODE3 3

extern u16* videobuffer;


void setPixel(int r,int c,u16 color);
void drawRect(int r,int c,int width,int height,u16 color);
void delay(int count);

#endif
