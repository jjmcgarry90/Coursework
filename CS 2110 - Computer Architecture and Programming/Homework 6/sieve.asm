.orig x3000

; Fill the Array
LD R0,CURSOR		; Array Index
LEA R1, PRIMES		; Array Address
ADD R0,R0,2		; Skip 0 and 1 indices
ADD R1,R1,2	        ; Skip 0 and 1
LD R2, COUNT	        ; Counter to stop loop
LOOP1 STR R0,R1,0	; Store Value in Array
ADD R1,R1,1		; Increment Address
ADD R0,R0,1		; Increment Cursor
ADD R2,R2,-1		; Decrement Counter
BRp LOOP1		; Do this 3000 times

; The Algorithm
LEA R0,PRIMES 		; addresses of values
LD R1,CURSOR		; checks every value in array
LD R2,CURSOR		; iterator of multiples
LD R3,CURSOR            ; temporary value holder
LD R4,CURSOR		; constant zero
LD R5,COUNT2		; stops the loop

LOOP
ADD R5,R5,1		
BRz FINISH		; terminating condition
ADD R1,R1,1		; increment cursor
ADD R2,R1,0		; R2 = R1
ADD R3,R0,R1		; array index to R3
LDR R3,R3,0		; current array value to R3
BRz LOOP

LOOP2
ADD R2,R2,R1		; start at first multiple of cursor
ADD R3,R0,R2		; new address
STR R4,R3,0		; set address value to zero
ADD R3,R5,R2		; check if all multiples are done
BRn LOOP2		; if not, do again
BRnzp LOOP		; check next number in array

FINISH

HALT

CURSOR .fill 0
COUNT .fill 2999
COUNT2 .fill -3001
PRIMES .BLKW 3001

.end
