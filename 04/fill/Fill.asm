// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.

(LOOP)
    //Assign to R0 the screen start address
    @SCREEN
    D=A
    @R0
    M=D
    //Check the keyboard value and if(value != 0) goto BLACK
    @KBD
    D=M 
    @BLACK 
    D;JNE 
(WHITE)
    //If current screen address is out of bounds goto ENDWHITE
    @KBD
    D=A
    @R0 
    D=D-M
    @ENDWHITE
    D;JEQ
    //Fill the current address white and load next address
    @R0
    A=M 
    M=0 
    @R0 
    M=M+1
(ENDWHITE)
    //If (keyboard value changed)  { goto LOOP } 
    //else { goto WHITE }
    @KBD
    D=M 
    @WHITE
    D;JEQ
    @LOOP
    0;JMP
(BLACK)
    //If current screen address is out of bounds goto ENDWHITE
    //Notice that keyboard address is the next address after the last screen address
    @KBD
    D=A
    @R0 
    D=D-M
    @ENDBLACK
    D;JEQ
    //Fill the current address black and load next address
    @R0
    A=M
    M=-1
    @R0
    M=M+1
(ENDBLACK)
    //If (keyboard value changed)  { goto LOOP } 
    //else { goto BLACK }
    @KBD
    D=M 
    @BLACK
    D;JNE
    @LOOP
    0;JMP