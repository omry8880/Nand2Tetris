Nand2Tetris Project

This repository contains my full implementation of the Nand2Tetris course, completed as part of a university computer science course. The project walks through the construction of a computer system from the ground up, starting from basic logic gates and culminating in a full compiler and virtual machine.

Project Structure

The project is divided into two main parts:

Low-Level (Hardware): Folders 1–5

These folders include HDL (Hardware Description Language) files that implement the foundational hardware components of a computer, including:

    Basic logic gates (NAND, AND, OR, etc.)

    Multiplexers and demultiplexers

    Arithmetic Logic Unit (ALU)

    Memory components (Registers, RAM, ROM)

    The CPU and overall computer architecture

Each component was tested using the built-in hardware simulator provided by the course.

High-Level (Software):  Folders 6–10

These folders include Java implementations of the software tools that form the upper layers of the computer's architecture:

    Assembler – Translates Hack assembly code into binary machine code

    Virtual Machine (VM) Translator – Converts VM commands into Hack assembly

    Compiler (Jack Language) – Tokenizer, parser, and code generator for the high-level Jack programming language

The high-level tools work together to translate Jack code (a Java-like language used in the course) all the way down to binary code that runs on the hardware created in earlier stages.
