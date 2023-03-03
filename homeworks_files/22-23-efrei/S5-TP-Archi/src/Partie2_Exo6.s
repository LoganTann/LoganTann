.data
printNb: .asciz "%3d est premier\n"

.bss
n:       .long 0
i:       .long 0
iLim:       .long 0

.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == PARTIE 2 EXO 5 ==
    movl $1, n
nbLoop:
    incl n          # n++
    cmpl $100, n
    ja exit         # if (n > 100) goto :exit

    movl n, %ebx
    sarl $1, %ebx
    movl %ebx, iLim # iLim = n/2
    movl $1, i      # i = 1
    primeCheckLoop:
        incl i      
        movl i, %ecx
        cmp iLim, %ecx #  if (i > iLim) => Est premier
        ja endPrimeCheckLoop 

        movl n, %eax
        movl i, %ebx
        xorl %edx, %edx # movl $0, %edx
        idivl %ebx
        cmp $0, %edx
        je nbLoop     # if (n % i == 0) => Pas premier on passe au n++ suivant
    jmp primeCheckLoop # else on passe au i++ suivant
    endPrimeCheckLoop:
    pushl n
    pushl $printNb
    call printf     # printf(printnb, i)
    jmp nbLoop

exit:
    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
