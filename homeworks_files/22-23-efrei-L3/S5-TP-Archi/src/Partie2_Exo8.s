.data
str: .asciz "La somme donne : %d \n"
.bss

.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 8 ==

    movl $0b1010, %eax # => 10
    movl $0b1001, %ebx # => 9
    addl %eax, %ebx    # => 19
    
    pushl %ebx
    pushl $str
    call printf
    
exit:
    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
