#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define NB_OPERATIONS 5
int v_globale = 5;

int main(int argc, char const* argv[]) {
    int child_pid = fork();
    int pid = getpid();
    int ppid = getppid();
    char* type = (child_pid == 0) ? "Fils" : "Père";
    printf("[%s] PID=%d, PPID=%d, v_globale=%d\n", type, pid, ppid, v_globale);
    printf("[%s]    child_pid=%d\n", type, child_pid);

    srand(pid);  // seed de random propre au process
    for (int i = 0; i < NB_OPERATIONS; i++) {
        useconds_t random_sleep = rand() % 2000;
        if (child_pid == 0) {
            v_globale += 10;
        } else {
            v_globale *= 2;
        }
        usleep(random_sleep);
        printf("[%4d] [%s]    i=%d, v_globale=%d\n", random_sleep, type, i,
               v_globale);
    }
    pid = getpid();
    printf("** Fin du processus <%d> (%s), v_globale=%d **\n", pid, type,
           v_globale);
    return 0;
}