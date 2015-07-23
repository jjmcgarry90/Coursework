.orig x3000

; This is a working solution for HW6.
; If you use this for your HW 7, treat this portion as a black box: Any register
; (with the exception of R6) could potentially be overwritten. There is also no
; need to change anything in this range.

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

HALT

PRIMES	.BLKW	3001
.end
