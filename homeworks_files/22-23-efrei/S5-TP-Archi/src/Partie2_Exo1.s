.data
str1: .asciz "Hello Mon premier programme en assembleur \n"
str2: .asciz "La valeur de eax = %d \n"

.bss
.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 1 ==

    pushl $str1
    call printf

    movl $42, %eax
    pushl %eax
    pushl $str2
    call printf
    addl $4, %esp

    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
