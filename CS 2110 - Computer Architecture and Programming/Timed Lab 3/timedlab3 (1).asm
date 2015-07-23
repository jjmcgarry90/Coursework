;;======================================================================
;; CS2110                     Timed Lab #3                     Fall 2010
;;======================================================================
;; Filename: timedlab3.asm
;; Author:
;; Date: Wednesday, October 27, 2010
;; Assignment: Timed Lab #3 - Assembly Recursion
;; Description: 
;;======================================================================

;; The program code will start at memory address 0x3000.
.orig   x3000

MAIN    
LD R6, STACK	; Initialize your stack pointer here
LD R0, NUM	; LOAD NUM INTO R0
JSR DISPLAY	; CALL DISPLAY HERE
ST R2,ANS	; STORE THE RESULT INTO ANS
	HALT

;---------------
; Data Section:
;---------------
STACK   .fill   xF000
; Change the below line to test your code.
; We will be testing your code with our own test cases
; Be sure to TEST YOUR CODE COMPLETELY.
NUM	.fill	15
; THE ANSWER WILL GO HERE
ANS .fill 0
DIV .fill 10


;;================================================================
;; Display subroutine
;;================================================================
;; DISPLAY
;;
;; Preconditions:
;; R0 contains n, the number you want to print
;;
;; Postconditions:
;; R0 is unchanged.
;; R2 contains numchars, the number of characters that was printed
;; No registers are trashed
;;================================================================
DISPLAY
LD R1,DIV
ADD R6,R6,-3		; store stuff on 
STR R0,R6,0
STR R1,R6,1
STR R7,R6,2

ADD R0,R0,-10		; if n <10, go to base case
BRnz BASE
ADD R0,R0,10

JSR UDIV
JSR DISPLAY
ADD R0,R1,0		; R0 = n%10  (this is where my code breaks)
JSR PRINTNUM		; (easy fix if i had time)

RETURN
ADD R2,R2,1
LDR R0,R6,0
LDR R1,R6,1
LDR R7,R6,2
ADD R6,R6,3
RET

BASE
ADD R0,R0,10
JSR PRINTNUM
BR RETURN

;;================================================================
;; PrintNum subroutine
;;================================================================
;; PRINTNUM
;;
;; Preconditions:
;; R0 contains c the character you want to print (0-9)
;;
;; Postconditions:
;; R0 still contains c (its unchanged)
;; The character is printed to the LC3 Console
;; Again no other registers are trashed.
;;================================================================
PRINTNUM
ADD R6,R6,-1
STR R7,R6,0
ADD R0,R0,15
ADD R0,R0,15
ADD R0,R0,15
ADD R0,R0,3
OUT
LDR R7,R6,0
ADD R6,R6,1
ADD R0,R0,-16
ADD R0,R0,-16
ADD R0,R0,-16
RET


;;==================================================
;; You should not modify any code beyond this point.
;;==================================================
; NOTE: You may (in fact will have to) use the division subroutine below
;       inside your display subroutine.	

;; A working division/modulo subroutine for positive numbers only.
;;
;; Preconditions:
;;  R6 is the stack pointer and points to the last used stack location
;;  R0 is between 0 and 32767
;;  R1 is between 1 and 32767
;;
;; Postconditions:
;;  R0 <- R0 / R1
;;  R1 <- R0 % R1
;;
UDIV
	.fill x0042 
	.fill x1DBB 
	.fill x7F84 
	.fill x7B83 
	.fill x7982 
	.fill x7781 
	.fill x7580 
	.fill xEA2E 
	.fill x947F 
	.fill x14A1 
	.fill x56E0 
	.fill x16FF 
	.fill x16E1 
	.fill x1943 
	.fill x7500 
	.fill x1482 
	.fill x09FB 
	.fill x5260 
	.fill x1943 
	.fill x6500 
	.fill x1480 
	.fill x0804 
	.fill x10A0 
	.fill x1930 
	.fill x6500 
	.fill x1242 
	.fill x16FF 
	.fill x07F6 
	.fill x1460 
	.fill x1220 
	.fill x10A0 
	.fill x6580 
	.fill x6781 
	.fill x6982 
	.fill x6B83 
	.fill x6F84 
	.fill x1DA5 
	.fill xC1C0 
	.fill x0001 
	.fill x0002 
	.fill x0004 
	.fill x0008 
	.fill x0010 
	.fill x0020 
	.fill x0040 
	.fill x0080 
	.fill x0100
	.fill x0200
	.fill x0400 
	.fill x0800 
	.fill x1000 
	.fill x2000 
	.fill x4000 
	.fill x8000 
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
	.fill x0000
.end
