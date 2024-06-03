#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define NB_OPERATIONS 5
int v_globale = 5;

void* calcul_thread(void* args) {
    int* threadIndex = (int*)args;
    for (int i = 0; i < NB_OPERATIONS; i++) {
        v_globale++;
        printf("[Thread %d]\ti=%d\tvglobale=%d\n", *threadIndex, i, v_globale);
    }
}

int main(int argc, char const* argv[]) {
    pthread_t idThread1, idThread2, idThread3;
    int threadIndex1 = 1, threadIndex2 = 2, threadIndex3 = 3;
    pthread_create(&idThread1, NULL, calcul_thread, (void*)&threadIndex1);
    pthread_create(&idThread2, NULL, calcul_thread, (void*)&threadIndex2);
    pthread_create(&idThread3, NULL, calcul_thread, (void*)&threadIndex3);
    pthread_join(idThread1, NULL);
    pthread_join(idThread2, NULL);
    pthread_join(idThread3, NULL);
    printf("\nTous les threads fils sont terminés\n");
    printf("\tv_globale=%d\n", v_globale);
    return 0;
}