.data
.bss
.text
    .global main
main:
    movl %esp, %ebp
    pushl %ebp
    # == début programme PARTIE 1 ==
    
    
    ## Exo 2.1
    movl $12345678, %eax
    # >> (eax) 0x00BC614E
    movl $0x12345678, %eax
    # >> (eax) 0x12345678
    
    # Exo 2.2
    xor %eax, %eax
    movl $0x12345678, %eax
    # >> (eax) $0x12345678
    movb $1, %ah
    # >> (eax) $0x12340178
    movw $1, %ax
    # >> (eax) $0x12340001
    
    # Exo 2.3   
    movl $0x12134515, %eax
    movl $0xABCDABCD, %ebx
    movl $0xAAAAAAAA, %ecx
    movl $0x00000000, %edx
    # movb => move byte (8 bits)
    movb $00, %bh
    # >> (ebx) $0xABCD00CD

    # Exo 3
    movl $0xff0000ff, %eax
    # >> (eax) 0xff0000ff
    addw $0xff00, %ax
    # >> (eax) 0xff00ffff
    addb %ah, %al
    # >> (eax) 0xff00fffe   // CF=1
    
    # == fin programme ==
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
