.orig x3000

MAIN
    LD R6, STACK    ; Initialize stack pointer
    ; Call your sieve subroutine here.
    ; Load PRIME_FACT_NUM into R0
    ; Put a 0 into R1 here
    ; Load the ADDRESS OF the buffer into R2
    ; Call your primefactorization subroutine here
    HALT
    
PRIME_FACT_NUM .fill #36
BUFFER .BLKW 15
STACK .fill xF000

;; The sieve algorithm
;;
;;
SIEVE


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


.end
