.data
tab: .int 1,5,9
     .int 4,8,7

printNb: .asciz "%d "
printNl: .asciz "\n"
.bss
i:       .long 0
j:       .long 0

.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 6 ==

    movl $tab, %eax
    
    
    loop: # (i)
        # loop (j)
            movl $3, %ecx
            imull i, %ecx  
            addl j, %ecx  # addr = (3 * i + j)
        
            movl (%eax, %ecx, 4), %ebx
            
            pushl %eax
            pushl %ebx
            pushl $printNb
            call printf     # printf(printNb, i)
            addl $8, %esp
            popl %eax
        incl j
        cmpl $3, j
        jne loop
    
    pushl %eax
    pushl $printNl
    call printf     # printf(printNl)
    addl $4, %esp
    popl %eax
    
    movl $0, j
    incl i
    cmpl $2, i
    jne loop
    
exit:
    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
