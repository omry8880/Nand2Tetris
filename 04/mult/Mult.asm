// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.

// first let's set R2's value to 0
@R2
M=0
(LOOP) // main multiplication loop
  @R1 // if R1 = 0 finish
  D=M
  @END
  D;JEQ

  @R0 // Add R0's value to R2
  D=M
  @R2
  M=D+M
  // Decrease R1 and return to loop
  @R1
  M=M-1
  @LOOP
  0;JMP
(END)
  @END // continue with infinite loop
  0;JMP
