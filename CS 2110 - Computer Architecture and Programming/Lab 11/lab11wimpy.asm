.orig x3000

LD R0,A
LD R1,B
LD R6,STKINIT
JSR ADDR
HALT

ADDR
ADD R6,R6,-1
STR R7,R6,0
ADD R1,R1,0
BRz RETURN

ADD R0,R0,1
ADD R1,R1,-1
JSR ADDR
LD R1,B

RETURN
LDR R7,R6,0
ADD R6,R6,1
RET


A .fill 5
B .fill 2
STKINIT .fill xF000

.end