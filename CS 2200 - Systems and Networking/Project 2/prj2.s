!************************************************************************!
!									 !
! general calling convention:						 !
!									 !
! (1) Register usage is as implied in the assembler names		 !
!									 !
! (2) Stack convention							 !
!									 !
!	  The stack grows towards higher addresses.  The stack pointer	 !
!	  ($sp) points to the next available (empty) location.		 !
!									 !
! (3) Mechanics								 !
!									 !
!	  (3a) Caller at call time:					 !
!	       o  Write any caller-saved stuff not saved at entry to	 !
!		  space on the stack that was reserved at entry time.	 !
!	       o  Do a JALR leaving the return address in $ra		 !
!									 !
!	  (3b) Callee at entry time:					 !
!	       o  Reserve all stack space that the subroutine will need	 !
!		  by adding that number of words to the stack pointer,	 !
!		  $sp.							 !
!	       o  Write any callee-saved stuff ($ra) to reserved space	 !
!		  on the stack.						 !
!	       o  Write any caller-saved stuff if it makes sense to	 !
!		  do so now.						 !
!									 !
!	  (3c) Callee at exit time:					 !
!	       o  Read back any callee-saved stuff from the stack ($ra)	 !
!	       o  Deallocate stack space by subtract the number of words !
!		  used from the stack pointer, $sp			 !
!	       o  return by executing $jalr $ra, $zero.			 !
!									 !
!	  (3d) Caller after return:					 !
!	       o  Read back any caller-saved stuff needed.		 !
!									 !
!************************************************************************!

!vector table
 vector0: .fill 0x00000000 !0
 .fill 0x00000000 !1
 .fill 0x00000000 !2
 .fill 0x00000000
 .fill 0x00000000 !4
 .fill 0x00000000
 .fill 0x00000000
 .fill 0x00000000
 .fill 0x00000000 !8
 .fill 0x00000000
 .fill 0x00000000
 .fill 0x00000000
 .fill 0x00000000
 .fill 0x00000000
 .fill 0x00000000
 .fill 0x00000000 !15
!end vector table


main:
	addi $sp, $zero, initsp ! initialize the stack pointer
	lw $sp, 0($sp)
	! Install timer interrupt handler into the vector table
        addi $a0, $zero, 1	! Store value 1 in $a0
	addi $a1, $zero, ti_inthandler   ! Store address of ti_inthandler in $a1
	sw $a1, 0($a0)		! Load address of handler to address 1
	ei 			!Don't forget to enable interrupts...

		
	addi $a0, $zero, 3	!load base for pow
	addi $a1, $zero, 3	!load power for pow
	addi $at, $zero, POW			!load address of pow
	jalr $at, $ra		!run pow
		
	halt	
		
		

POW: 
  addi $sp, $sp, 2   ! push 2 slots onto the stack
  sw $ra, -1($sp)   ! save RA to stack
  sw $a0, -2($sp)   ! save arg 0 to stack
  beq $zero, $a1, RET1 ! if the power is 0 return 1
  beq $zero, $a0, RET0 ! if the base is 0 return 0
  addi $a1, $a1, -1  ! decrement the power
  addi $at, $zero, POW    ! load the address of POW
  jalr $at, $ra   ! recursively call POW
  add $a1, $v0, $zero  ! store return value in arg 1
  lw $a0, -2($sp)   ! load the base into arg 0
  addi $at, $zero, MULT   ! load the address of MULT
  jalr $at, $ra   ! multiply arg 0 (base) and arg 1 (running product)
  lw $ra, -1($sp)   ! load RA from the stack
  addi $sp, $sp, -2  ! pop the RA and arg 0 off the stack
  jalr $ra, $zero   ! return
RET1: addi $v0, $zero, 1  ! return a value of 1
  addi $sp, $sp, -2
  jalr $ra, $zero
RET0: add $v0, $zero, $zero ! return a value of 0
  addi $sp, $sp, -2
  jalr $ra, $zero		
	
MULT: add $v0, $zero, $zero ! zero out return value
AGAIN: add $v0,$v0, $a0  ! multiply loop
  addi $a1, $a1, -1
  beq $a1, $zero, DONE ! finished multiplying
  beq $zero, $zero, AGAIN ! loop again
DONE: jalr $ra, $zero	
		
		
ti_inthandler:
	addi $sp, $sp, 1	! decr stack pointer
	sw $k0, 0($sp)		! save $k0 to stack
	ei			! enable interrupts
	addi $sp, $sp, 15	! decr stack pointer
	sw $at, 0($sp)		! store all registers on stack
	sw $v0, -1($sp)
	sw $a0, -2($sp)
	sw $a1, -3($sp)
	sw $a2, -4($sp)
	sw $a3, -5($sp)
	sw $a4, -6($sp)
	sw $s0, -7($sp)
	sw $s1, -8($sp)
	sw $s2, -9($sp)
	sw $s3, -10($sp)
	sw $k0, -11($sp)
	sw $sp, -12($sp)
	sw $fp, -13($sp)
	sw $ra, -14($sp)

	addi $a0, $zero, time	! load address of time label into $a0
	lw $a0, 0($a0)		! load 
	lw $a1, 0($a0)		! load value at 0xF00000 into $a1
	addi $a1, $a1, 1	! increment seconds by one
	addi $a4, $zero, 60	! store value 60 into temp register $a4
	beq $a1, $a4, INCMIN    ! if seconds = 60, increment minutes
	sw $a1, 0($a0)		! else just store seconds at 0xF000000

RET:	lw $at, 0($sp)		! restore all processor registers
	lw $at, -1($sp)
	lw $v0, -2($sp)
	lw $a0, -3($sp)
	lw $a1, -4($sp)
	lw $a2, -5($sp)
	lw $a3, -6($sp)
	lw $a4, -7($sp)
	lw $s0, -8($sp)
	lw $s1, -9($sp)
	lw $s3, -10($sp)	
	lw $k0, -11($sp)
	lw $sp, -12($sp)
	lw $fp, -13($sp)
	lw $ra, -14($sp)
	addi $sp, $sp, -15
	di
	lw $k0, 0($sp)		! restore k0
	addi $sp, $sp, -1
	reti

INCMIN:
	lw $a2, 1($a0)		! load minutes value into $a2
	addi $a2, $a2, 1	! increments minutes
	addi $a1, $zero, 0	! set seconds to zero
	beq $a2, $a4, INCHRS	! if minutes = 60, increment hours
	sw $a2, 1($a0)		! else just store minutes at 0xF00001
	beq $zero, $zero, RET	! return

INCHRS:	
	lw $a3, 2($a0)		! load hours into $a3
	addi $a3, $a3, 1	! increment hours
	addi $a2, $zero, 0	! reset minutes
	sw $a3, 2($a0)		! store hours at 0xF00002
	beq $zero, $zero, RET	! return
	
	
	
	
	
	
	
	
	
time: .fill 0xF00000
initsp: .fill 0xA00000
