.orig x3000

LD R0,A           ; load R0 with A
LD R1,B	  	  ; load R1 with B
LD R2,START       ; load R2 with x3100
ADD R3,R0,R1  	  ; load R3 with A + B
STR R3,R2,0	  ; store A + B at x3101
AND R4,R1,R0	  ; load R4 with A AND B
STR R4,R2,1       ; store R4 at x3101
NOT R5,R1	  
ADD R5,R5,1	  ; load R5 with the NOT(B) +1
ADD R6,R0,R5	  ; load R6 with A-B
STR R6,R2,2	  ; store A-B at x3102
NOT R6,R6
ADD R6,R6,1       ; load R6 with B-A
STR R6,R2,3       ; store B-A at x3103

HALT
START .fill x3100
A .fill 3
B .fill 10

.end
