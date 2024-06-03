.data
filename: .asciz "myfile.txt"
printfd: .asciz "%c"

.bss
fd: .int 0
buffer: .byte 0
.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 1 ==
    
    /** open(filename, 1)  **/
    movl $0x5, %eax
    movl $filename, %ebx
    movl $0x0, %ecx
    xorl %edx, %edx
    int $0x80
    movl %eax, fd

    readAndPrintLoop:
    movl $0x3, %eax
    movl fd, %ebx
    movl $buffer, %ecx
    movl $0x1, %edx    # read(fd, buffer, 1);
    int $0x80

    cmp $1, %eax
    jne exit

    pushl buffer
    pushl $printfd
    call printf
    jmp readAndPrintLoop
    
exit:
    /** exit(0) **/
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
