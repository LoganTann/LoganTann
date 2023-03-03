.data
printNb: .asciz "%d \n"

.bss
i:       .long 0

.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 5 ==

loop:
    addl $2, i      # i+=2
    pushl i
    pushl $printNb
    call printf     # printf(printnb, i)
    cmpl $20, i
    jb loop         # if (i < 20) goto :loop

    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
