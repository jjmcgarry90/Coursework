#include <stdlib.h>
#include "myLib.h"
int r = 80,c = 120;
int deltar = 1, deltac = 1;
int shade = 0;
int main(void) {
	REG_DISPCNT = MODE3 | BG2_ENABLE;
	while(1) {
		setPixel(r,c,rand());
		r+= deltar;
		c+= deltac;
		if(r>159 || r<1){
			deltar = -deltar;
			r+= deltar;
		}
		if (c>239 || c<1){
			deltac = -deltac;
			c+= deltac;
		}
		delay(100);
	}
}
