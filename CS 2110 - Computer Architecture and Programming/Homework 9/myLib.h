typedef unsigned short u16;

typedef struct
{
	int row;
	int col;
	int width;
	int height;
	u16 color;
	
} RECT;

#define REG_DISPCTL *(unsigned short *)0x4000000
#define NUMCOLS 240
#define BG2_ENABLE (1<<10)

#define MODE3 3

#define OFFSET(row, col, numcols) ((row)*(numcols)+(col))
#define COLOR(r,g,b) ((r) | (g)<<5 | (b)<<10)
#define RED COLOR(31,0,0)
#define GREEN COLOR(0,31,0)
#define BLUE COLOR(0,0,31)
#define MAGENTA COLOR(31,0,31)
#define YELLOW COLOR(31,31,0)
#define CYAN COLOR(0,31,31)
#define WHITE COLOR(31,31,31)
#define BLACK 0

extern unsigned short *videoBuffer;

/* Prototypes */
void drawRect(int row, int col, int ht, int wid, unsigned short color);
void setPixel(int r, int c, unsigned short color);
void delay(int n);
void waitForVblank();
int rectCollides(RECT,RECT);
void gameOver();
void title();
void slapScreen();
void drawImage3(int row, int col, int width, int height, const u16* image);


#define SCANLINECOUNTER *(volatile unsigned short *)0x4000006

/* Buttons */

#define BUTTON_A      (1<<0)
#define BUTTON_B      (1<<1)
#define BUTTON_SELECT (1<<2)
#define BUTTON_START  (1<<3)
#define BUTTON_RIGHT  (1<<4)
#define BUTTON_LEFT   (1<<5)
#define BUTTON_UP     (1<<6)
#define BUTTON_DOWN   (1<<7)
#define BUTTON_R      (1<<8)
#define BUTTON_L      (1<<9)
#define BUTTON_ANY    1023

#define BUTTON_INDEX_A      0
#define BUTTON_INDEX_B      1
#define BUTTON_INDEX_SELECT 2
#define BUTTON_INDEX_START  3
#define BUTTON_INDEX_RIGHT  4
#define BUTTON_INDEX_LEFT   5
#define BUTTON_INDEX_UP     6
#define BUTTON_INDEX_DOWN   7
#define BUTTON_INDEX_R      8
#define BUTTON_INDEX_L      9

#define BUTTONS (*(volatile unsigned int *)0x04000130)

#define KEY_DOWN_NOW(key)  (~(BUTTONS) & key)



