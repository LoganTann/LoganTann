.data
border: .asciz "**************************\n"
gnutxt: .asciz "*   GNU AS IS THE BEST   *\n"

.bss
.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 2 ==

    pushl $border
    call printf
    pushl $gnutxt
    call printf
    pushl $border
    call printf

    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
