.orig x3000

MAIN
    LD R6, STACK   	     ; Initialize stack pointer
    JSR SIEVE       	     ; Call your sieve subroutine here.
    LD R0,PRIME_FACT_NUM     ; Load PRIME_FACT_NUM into R0
    AND R1,R1,0		     ; Put a 0 into R1 here
    LEA R2,BUFFER 	     ; Load the ADDRESS OF the buffer into R2
    JSR PRIMEFACT	     ; Call your primefactorization subroutine
    HALT
    
PRIME_FACT_NUM .fill #1000
BUFFER .BLKW 15
STACK .fill xF000

;; The sieve algorithm
SIEVE
ADD R6,R6,-7
STR R0,R6,0
STR R1,R6,1
STR R2,R6,2
STR R3,R6,3
STR R4,R6,4
STR R5,R6,5
STR R7,R6,6
.fill xe00b
.fill x2209
.fill x943f
.fill x1281
.fill x6400
.fill x94bf
.fill x7400
.fill x1021
.fill x1401
.fill x09fa
.fill x0e01
.fill xffd1
.fill xddd0
.fill xafdf
.fill x8fbf
.fill xed9e
.fill x8fbf
.fill xed9e
.fill xa6df
.fill xe6dd
.fill x87bf
.fill xed9e
.fill xe6de
.fill xdbdd
.fill x87bf
.fill xed9e
.fill x8fbf
.fill xed9e
.fill xe6dd
.fill xeb41
.fill xfc06
.fill xdde3
.fill xed9c
.fill xa6df
.fill xe71c
.fill x64c0
.fill xe49e
.fill xdbeb
.fill xd9eb
.fill xedbb
.fill xedbb
.fill x8fbf
.fill xeb7a
.fill xeb7a
.fill xfc05
.fill xe900
.fill xe6de
.fill xddf3
.fill xedbb
.fill x91bf
.fill xfa05
.fill xdbf9
.fill x64c0
.fill xe49e
.fill xeb7a
.fill xe91f
.fill xfc11
.fill xf1fc
.fill xf449
.fill xffc8
.fill PRIMES
LDR R0,R6,0
LDR R1,R6,1
LDR R2,R6,2
LDR R3,R6,3
LDR R4,R6,4
LDR R5,R6,5
LDR R7,R6,6
ADD R6,R6,7

RET

;; The primefactorization algorithm
;;  Preconditions:
;;      R0 contains n, the number you want to find the prime factors of (0-32767)
;;      R1 contains num, the number of items in the buffer.
;;      R2 contains buffer, the address of the next empty slot in the buffer
;;      conditions:
;;      R1 contains the number of elements that was stored in the buffer.
;;      BUFFER contains the prime factors of n
;;      No registers are trashed.
;;
PRIMEFACT
ADD R6,R6,-7		; decrement the stack
STR R7,R6,0		; add return address to stack
STR R0,R6,1		; add n to stack
STR R1,R6,2		; add num to stack
STR R2,R6,3		; add buffer address to stack
STR R3,R6,4		; add R3 to stack (will be i)
STR R4,R6,5		; R4 will be start of PRIMES
STR R5,R6,6		; R5 will hold primes[i]

ADD R0,R0,-1		; check if R0 <=1
BRnz BASE
ADD R0,R0,1		; restore n

AND R3,R3,0		; R3 = 0
ADD R3,R3,-1		; R3 = -1
LEA R4,PRIMES		; R4 = PRIMES

LOOP
ADD R3,R3,1		; increment i
ADD R5,R4,R3		; address of primes[i]
LDR R5,R5,0		; R5 = value at primes[i]
BRnz LOOP

ADD R1,R3,0		; R1 = i
LDR R0,R6,1		; reload R0
JSR UDIV

ADD R1,R1,0
BRnp LOOP

STR R3,R2,0		; *buffer = i
LDR R1,R6,2		; R1 = num
ADD R1,R1,1		; R1++
ADD R2,R2,1		; R2++
JSR PRIMEFACT


STR R0,R2,0		; *buffer = n

RETURN
LDR R7,R6,0
AND R0,R0,0
LDR R3,R6,4
LDR R4,R6,5
LDR R5,R6,6
ADD R6,R6,7
RET



BASE
ADD R0,R0,1
ADD R1,R1,0
BRz EMPTY
BR RETURN

EMPTY
STR R0,R2,0
ADD R1,R1,1
BR RETURN



;;==================================================
;; You should not modify any code beyond this point.
;;==================================================
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
        .fill #66               ; Flag for SimpLC-3 to mark as blackboxed
        add r6, r6, #-5         ; Make room for the address and saved regs
        str r7, r6, #4          ; Store return address in first usable space
        str r5, r6, #3          ; Save r5 on stack
        str r4, r6, #2          ; Save r4 on stack
        str r3, r6, #1          ; Save r3 on stack
        str r2, r6, #0          ; Save r2 on stack

        lea r5, UDIV_SHIFTS
        not r2, r1
        add r2, r2, #1
        and r3, r3, #0
        add r3, r3, #-1

        UDIV_NEXTSHIFT
                add r3, r3, #1
                add r4, r5, r3
                str r2, r4, #0
                add r2, r2, r2
                brn UDIV_NEXTSHIFT

        and r1, r1, #0 ; r1 is now quotient

        UDIV_NEXTBIT
                add r4, r5, r3
                ldr r2, r4, #0
                add r2, r2, r0
                brn UDIV_NOSUB
                        add r0, r2, #0 ; put subtracted in r0
                        add r4, r4, #-16
                        ldr r2, r4, #0
                        add r1, r1, r2
                UDIV_NOSUB
                add r3, r3, #-1
                brzp UDIV_NEXTBIT

        add r2, r1, #0
        add r1, r0, #0
        add r0, r2, #0

        ldr r2, r6, #0
        ldr r3, r6, #1
        ldr r4, r6, #2
        ldr r5, r6, #3
        ldr r7, r6, #4
        add r6, r6, #5
UDIV_end
        ret


;; end UDIV

UDIV_NUMS
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
UDIV_SHIFTS
        .blkw 16

PRIMES .BLKW 3001
.end
