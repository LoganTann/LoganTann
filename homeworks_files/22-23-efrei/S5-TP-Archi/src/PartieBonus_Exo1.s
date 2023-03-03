.data
str: .asciz "L'assembleur est cool !\n"
len = . - str - 1

.text
    .global main
main:
    /** write(STDOUT, str, len) **/
    movl $0x4, %eax
    movl $0x1, %ebx
    movl $str, %ecx
    movl $len, %edx
    int $0x80

    /** exit(0) **/
    movl $0x1, %eax
    movl $0x0, %ebx
    int $0x80
