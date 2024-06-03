.data
filename: .asciz "test.txt"

.bss
fd: .long 0

.text
    .global main
main:
    /** creat(filename, 0777) **/
    movl $0x8, %eax
    movl $filename, %ebx
    movl $0777, %ecx
    int $0x80
    movl %eax, fd

    /** exit(0) **/
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
