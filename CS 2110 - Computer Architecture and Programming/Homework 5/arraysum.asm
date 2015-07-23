.orig x3000

LD R0,SUM             
LD R1,ARRAYLEN        
LD R2,ARRAY
LEA R3, ARRAY       
loop ADD R0,R0,R2     ; Add value of current index to SUM
ADD R3,R3,1           ; Increment address
LDR R2,R3,0           ; Load R2 with value at new address
ADD R1,R1,-1          ; Decrement array length
BRp loop              ; loop while array length>0
ST R0,SUM             ; store R0 into SUM
HALT

ARRAYLEN .fill 5 ; The length of the following array
ARRAY    .fill 50
	 .fill 23
	 .fill 40
	 .fill 21
	 .fill 10
SUM 	 .fill 0   
.end
