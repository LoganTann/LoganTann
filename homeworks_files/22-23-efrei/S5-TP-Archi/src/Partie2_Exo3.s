.data
.bss

.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 3 ==

    xorl %eax, %eax
    xorl %ebx, %ebx
    xorl %ecx, %ecx
    xorl %edx, %edx
    # mets à 0 les registres de travail.

    # as Partie2_Exo3.s -o exo.o --32
    # gcc exo.o -g -o exo -m32 -fno-pie -no-pie
    
exit:
    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
